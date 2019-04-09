package com.example.demo.program.tree;

public class TreeCreator {
	/**
	 * 创建树
	 * @return
	 */
	public TreeNode createSimpleTree() {
       TreeNode root = new TreeNode('A');
       root.setLeft(new TreeNode('B'));
       root.getLeft().setLeft(new TreeNode('D'));
       root.getLeft().setRight(new TreeNode('E'));
       root.getLeft().getRight().setLeft(new TreeNode('G'));
       root.setRight(new TreeNode('C'));
       root.getRight().setRight(new TreeNode('F'));
       return root;
	}
}
