package com.example.demo.program.tree;

public class TreeNode {

	private final char value;
	private TreeNode left;
	private TreeNode right;
	private TreeNode parent;
	
	
	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public char getValue() {
		return value;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
		this.left.setParent(this);
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
		this.right.setParent(this);
	}

	public TreeNode(char value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}
