package com.devyy.pagerank;

import com.devyy.bean.ENode;
import com.devyy.util.MatrixUtil;

/** 
 * 抽税法允许每个冲浪者能够以一个较小的概率随机跳转到一个随机网页，而不一定要沿着当前网页的出链前进，从而避免了采集器陷阱或者终止点问题。 
 * vector' = beta * Matrix * vector + (1 - beta) * e / n，其中beta是抽税参数，1 - beta表示抽出的税率 
 * 
 * @author ZYY
 * 
 */  
public class TaxationPageRank {

	private PageRank pageRank = new PageRank();
	private MatrixUtil matrixUtil =new MatrixUtil(); 
	
	public static final double BETA = 0.8;  	
	
    // 使用迭代公式vector' = beta * Matrix * vector + (1 - beta) * e / n  
    public double[][] useFormula(int number, ENode[] node){  
        double[][] matrix = pageRank.createTransitionMatrixFun(number, node);  
        double value1 = 1.0 / matrix.length;  
        double value2 = 1.0 / matrix.length * (1 - BETA);  
        double[][] vector1 = new double[matrix.length][1];  
        double[][] vector2 = new double[matrix.length][1];  
        double[][] result =null;  
        int i, count = 0;  
        for(i = 0; i < vector1.length; i++){  
            vector1[i][0] = value1;  
        }  
        for(i = 0; i < vector2.length; i++){  
            vector2[i][0] = value2;  
        }  
        while(count != vector1.length){  
            result = matrixUtil.addMatrix(matrixUtil.RealMulMatrix(matrixUtil.multiMatrix(matrix, vector1), BETA), vector2);  
            count = 0;  
            for(i = 0; i < vector1.length; i++){  
                //当上一次与本次向量点的值差值少于3.5%，则表示到了极限值趋于稳定了。  
                if(Math.abs(result[i][0] - vector1[i][0]) <= 0.035){  
                    count++;  
                }  
            }  
            vector1 = result;  
        }  
        return result;  
    }  
      
    // 采用抽税法计算PageRank  
    public void countPageRankByTaxation(int number, ENode[] node){  
        System.out.println("------采用基于抽税法的PageRank算法--------------");  
        double[][] result = useFormula(number, node);  
        pageRank.printPageRank(result, node);  
    }  
}
