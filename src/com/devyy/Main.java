package com.devyy;

import java.util.Scanner;

import com.devyy.bean.ENode;
import com.devyy.pagerank.PageRank;
import com.devyy.pagerank.SubjectOrientedPageRank;
import com.devyy.pagerank.TaxationPageRank;
import com.devyy.util.NodeUtil;

public class Main {
	public static void main(String[] args) {
		PageRank pageRank = new PageRank();
		TaxationPageRank taxationPageRank = new TaxationPageRank();
		SubjectOrientedPageRank subjectOrientedPageRank = new SubjectOrientedPageRank();
		NodeUtil nodeUtil = new NodeUtil();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入节点的数目：");
		int number = sc.nextInt();
		ENode[] node = nodeUtil.createNode(number);
		pageRank.runPageRank(number, node);
		taxationPageRank.countPageRankByTaxation(number, node);
		System.out.println("请输入要设为主题的节点:(输入'0#'结束)");
		String input = sc.next();
		String[] strArr = new String[number];
		int i = 0, count = 0;
		while (!"0#".equals(input)) {
			strArr[i] = input;
			count++;
			input = sc.next();
			i++;
		}
		subjectOrientedPageRank.countPageRankBySubjectOriented(number, node, count, strArr);
		sc.close();
	}
}
