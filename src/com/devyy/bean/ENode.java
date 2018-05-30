package com.devyy.bean;

public class ENode {
	private String name;// 节点名字
	private NextNode nextNode;// 指向下一个节点
	private int count;// 此链表的数目
	private boolean subjectNode = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NextNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(NextNode nextNode) {
		this.nextNode = nextNode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isSubjectNode() {
		return subjectNode;
	}

	public void setSubjectNode(boolean subjectNode) {
		this.subjectNode = subjectNode;
	}
}
