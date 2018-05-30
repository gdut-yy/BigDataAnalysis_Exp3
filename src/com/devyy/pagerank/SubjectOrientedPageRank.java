package com.devyy.pagerank;

import com.devyy.bean.ENode;
import com.devyy.util.MatrixUtil;

/** 
 * 面向主题，首先确定哪些主题需要构建特定的PageRank向量 
 * 假定S是一个网页的集合，其中的网页属于类别S(随机跳转集合)。es是一个向量，如果其分量对应的网页属于S，则该分量置为1，否则为0。 
 * 
 * @author ZYY
 * 
 */  
public class SubjectOrientedPageRank {
	
	private PageRank pageRank = new PageRank();
	private MatrixUtil matrixUtil =new MatrixUtil(); 
	
	public static final double BETA = 0.8;  
    // 设置主题节点  
    public ENode[] dealWithSubjectNode(String[] strArr, int count, ENode[] node){  
        while(count > 0){  
            for(int i = 0; i < node.length; i++){  
                if(node[i].getName().equals(strArr[count - 1])){  
                    node[i].setSubjectNode(true);  
                    break;  
                }  
            }  
            count--;  
        }  
        return node;  
    }  
      
    // 使用迭代公式vector' = beta * Matrix * vector + (1 - beta) * es / |S|  
    public double[][] useFormula(int number, ENode[] node, int count){  
        double[][] matrix = pageRank.createTransitionMatrixFun(number, node);  
        double value1 = 1.0 / matrix.length;  
        double value2 = 1.0 / count * (1 - BETA);  
        double[][] vector1 = new double[matrix.length][1];  
        double[][] vector2 = new double[matrix.length][1];  
        double[][] result =null;  
        int i, counted = 0;  
        for(i = 0; i < vector1.length; i++){  
            vector1[i][0] = value1;  
        }  
        for(i = 0; i < vector2.length; i++){  
            if(node[i].isSubjectNode()){  
                vector2[i][0] = value2;  
            }else{  
                vector2[i][0] = 0;  
            }  
        }  
        while(counted != vector1.length){  
            result = matrixUtil.addMatrix(matrixUtil.RealMulMatrix(matrixUtil.multiMatrix(matrix, vector1), BETA), vector2);  
            counted = 0;  
            for(i = 0; i < vector1.length; i++){  
                //当上一次与本次向量点的值差值少于3.5%，则表示到了极限值趋于稳定了。  
                if(Math.abs(result[i][0] - vector1[i][0]) <= 0.035){  
                    counted++;  
                }  
            }  
            vector1 = result;  
        }  
        return result;  
    }  
      
    // 采用面向主题的PageRank技术计算PageRank  
    public void countPageRankBySubjectOriented(int number, ENode[] node, int count, String[] strArr){  
        System.out.println("------采用面向主题的PageRank技术计算PageRank--------------");  
        node = dealWithSubjectNode(strArr, count, node);  
        double[][] result = useFormula(number, node, count);  
        pageRank.printPageRank(result, node);  
    }  

}
