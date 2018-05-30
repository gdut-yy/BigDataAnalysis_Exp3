package com.devyy.pagerank;

import java.text.DecimalFormat;

import com.devyy.bean.ENode;
import com.devyy.bean.NextNode;
import com.devyy.util.MatrixUtil;

/** 
 * PageRank是一个函数，它对Web中的网页赋予一个实数值，网页的PageRank值越高，它越“重要”。 
 * 把Web想象成一个有向图，其中网页是图中节点，当满足如下两个条件时，可直接计算PageRank： 
 * （1）图是强连通图，即可以从任一节点到达其他节点 
 * （2）图不存在终止点，即不存在出链的节点。 
 * 当网页数目为n时构造的n*n的方阵乘以当前概率分布向量之后值不再改变时就到达了极限。 
 * 
 * @author ZYY
 * 
 */  
public class PageRank {
	private MatrixUtil matrixUtil =new MatrixUtil(); 
	
	// 根据邻接表构造Web转移矩阵  
    public double[][] createTransitionMatrixFun(int number, ENode[] node){  
        int i, index, count, temp;  
        double[][] transitionMatrix = new double[number][number];  
        for(i = 0; i < number; i++){  
            count = node[i].getCount();  
            temp = count;  
            index = 0;  
            NextNode nextNode = node[i].getNextNode();  
            while(temp-- != 0){  
                index = nextNode.getNextLoc();  
                transitionMatrix[index][i] = 1.0 / count;  
                nextNode = nextNode.getNextNode();  
            }  
        }  
        return transitionMatrix;  
    }  
      
    // 初始向量不断左乘矩阵  
    public double[][] countValue(double[][] matrix){  
        double value = 1.0 / matrix.length;  
        double[][] vector = new double[matrix.length][1];  
        double[][] result =null;  
        int i, count = 0;  
        for(i = 0; i < vector.length; i++){  
            vector[i][0] = value;  
        }  
        while(count != vector.length){  
            result = matrixUtil.multiMatrix(matrix, vector);  
            count = 0;  
            for(i = 0; i < vector.length; i++){  
                //当上一次与本次向量点的值差值少于3%，则表示到了极限值趋于稳定了。  
                if(Math.abs(result[i][0] - vector[i][0]) <= 0.035){  
                    count++;  
                }  
            }  
            vector = result;  
        }  
        return result;  
    }  
      
    // 打印每个节点的PageRank，并打印拥有最高PageRank的节点  
    public void printPageRank(double[][] vector, ENode[] node){  
        System.out.println("各节点的PageRank如下：");  
        DecimalFormat df = new DecimalFormat("0.00");  
        double max = 0.0;  
        int maxIndex = 0;  
        for(int i = 0; i < vector.length; i++){  
            if(vector[i][0] >= max){  
                max = vector[i][0];  
                maxIndex = i;  
            }  
            System.out.println(node[i].getName() + " : " + df.format(vector[i][0]));  
        }  
        System.out.println("拥有最高PageRank的节点为：" + node[maxIndex].getName() + "-" + df.format(max));  
    }  
      
    // 运行PageRank算法  
    public void runPageRank(int number, ENode[] node){  
        System.out.println("------运行PageRank算法--------------");  
        double[][] matrix = createTransitionMatrixFun(number, node);  
        double[][] result = countValue(matrix);  
        printPageRank(result, node);  
    }  
}
