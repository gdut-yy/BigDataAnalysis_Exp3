package com.devyy.bean;

public class NextNode {
	private int nextLoc;// 下一个节点的数组下标
	private NextNode nextNode;// 指向下一个节点

	public int getNextLoc() {
		return nextLoc;
	}

	public void setNextLoc(int nextLoc) {
		this.nextLoc = nextLoc;
	}

	public NextNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(NextNode nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		return "NextNode [nextLoc=" + nextLoc + ", nextNode=" + nextNode + "]";
	}
}
