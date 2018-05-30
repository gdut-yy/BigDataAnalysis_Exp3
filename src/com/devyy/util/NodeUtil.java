package com.devyy.util;

import java.util.Scanner;

import com.devyy.bean.ENode;
import com.devyy.bean.NextNode;

/** 
 * 构造节点，根据实验要求最简单的方式应该是构造二维数组来存放节点，即邻接矩阵的方式来存放有向图，但此类尝试使用邻接表的方式来存放该有向图降低空间复杂度。 
 * 邻接矩阵的方式来存放有向图较简单，故此类中不实现。 
 * 
 * @author ZYY
 * 
 */  
public class NodeUtil {
	// 生成节点及节点之间的关系
	public ENode[] createNode(int number) {
		int i, j, count;
		String key, value;
		boolean flag = true;
		ENode[] node = new ENode[number];
		System.out.println("请分别输入" + number + "个节点的节点名字：");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		for (i = 0; i < number; i++) {
			ENode childNode = new ENode();
			childNode.setName(sc.next());
			childNode.setNextNode(null);
			node[i] = childNode;
		}
		System.out.println("请输入节点之间的联系(eg.网页A链向网页B，则输入\"A B\",输入\"0 0 \"结束)");
		// 如果采用二维数组存放联系，若有向图由10000个网站节点构成，则需要10000 * 10000的空间，内存浪费严重，因此直接使用邻接矩阵来存放联系
		while (flag) {
			key = sc.next();
			value = sc.next();
			if ("0".equals(key) && "0".equals(value)) {
				flag = false;
			} else {
				// 相比二维数组，时间复杂度高了一个数量级，但存储所需空间少了，用时间复杂度来换取空间复杂度
				for (i = 0; i < number; i++) {
					if (node[i].getName().equals(key)) {
						for (j = 0; j < number; j++) {
							// 使用头插法
							if (node[j].getName().equals(value)) {
								NextNode nextNode = new NextNode();
								NextNode backwardNode = node[i].getNextNode();
								nextNode.setNextLoc(j);
								nextNode.setNextNode(backwardNode);
								node[i].setNextNode(nextNode);
								break;
							}
						}
						break;
					}
				}
			}
		}
		// 本身邻接表无须加count属性，但计算count是为了后面的PageRank算法的便捷
		for (i = 0; i < number; i++) {
			count = 0;
			NextNode nextNode = node[i].getNextNode();
			while (nextNode != null) {
				count++;
				nextNode = nextNode.getNextNode();
			}
			node[i].setCount(count);
//			System.out.println(node[i].toString());
		}
		return node;
	}
}
