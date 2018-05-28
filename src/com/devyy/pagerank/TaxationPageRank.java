package com.devyy.pagerank;

import com.devyy.bean.ENode;
import com.devyy.util.MatrixUtil;

public class TaxationPageRank {

	private PageRank pageRank = new PageRank();
	
	public static final double BETA = 0.8;  	
	
    //使用迭代公式vector' = beta * Matrix * vector + (1 - beta) * e / n  
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
            result = MatrixUtil.addMatrix(MatrixUtil.RealMulMatrix(MatrixUtil.multiMatrix(matrix, vector1), BETA), vector2);  
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
      
    //采用抽税法计算PageRank  
    public void countPageRankByTaxation(int number, ENode[] node){  
        System.out.println("------采用基于抽税法的PageRank算法--------------");  
        double[][] result = useFormula(number, node);  
        pageRank.printPageRank(result, node);  
    }  
}
