package com.example.demo.program.tree;

/**
 * 遍历树
 * 
 * @author riverplant
 *
 */
public class TreeTravel {
	/**
	 * 前序遍历: 根->左->右
	 * 
	 * @param node
	 */
	public void preOrder(TreeNode node) {
		if (node == null)
			return;
		System.out.println(node.getValue());// 根节点
		preOrder(node.getLeft());// 左叶
		preOrder(node.getRight());// 右叶
	}

	/**
	 * 中序遍历: 左->根->右
	 * 
	 * @param node
	 */
	public void inOrder(TreeNode node) {
		if (node == null)
			return;
		inOrder(node.getLeft());// 左叶
		System.out.println(node);// 根节点
		inOrder(node.getRight());// 右叶
	}

	/**
	 * 后序遍历: 左->右->根节点
	 * 
	 * @param node
	 */
	public void postOrder(TreeNode node) {
		if (node == null)
			return;
		postOrder(node.getLeft());// 左叶

		postOrder(node.getRight());// 右叶
		System.out.println(node);// 根节点
	}

	/**
	 * 知道前序:ABDEGCF 中序:DBGEACF 求后序: 通过前序[0]可以知道A为root 通过index =
	 * 中序indexOf(root)可以得到左叶长度为index,为BDGE
	 */
	public TreeNode createTree(String prePot, String inOrder) {
		if (prePot == null)
			return null;
		TreeNode root = new TreeNode(prePot.charAt(0));
		int index = inOrder.indexOf(root.getValue());
		root.setLeft(createTree(prePot.substring(1, index + 1), inOrder.substring(0, index)));
		root.setRight(createTree(prePot.substring(index + 1), inOrder.substring(1 + index)));
		return root;
	}
	
	/**
	 * 知道前序:ABDEGCF 中序:DBGEACF 求后序: 通过前序[0]可以知道A为root 通过index =
	 * 中序indexOf(root)可以得到左叶长度为index,为BDGE
	 */
	public String createPostOrder(String prePot, String inOrder) {
		if (prePot == null)
			return "";
		TreeNode root = new TreeNode(prePot.charAt(0));
		int index = inOrder.indexOf(root.getValue());
		return createPostOrder(prePot.substring(1, index + 1), inOrder.substring(0, index))+
				createPostOrder(prePot.substring(index + 1), inOrder.substring(1 + index))+
		       root;
	}
	
	/**
	 * 寻找中序遍历时的下一结点:
	 * 中序遍历：左支数 -> 树根 ->右支数--->遍历左支数->遍历自己->遍历右支数
	 * 1.该点N的左支数已经遍历完成，不需要考虑
	 * 2.如果右子树！=null 返回右支数第一个结点
	 * 3.如果没有右子树，如果父节点P!=null  
	 *                             N是P的左子树，返回p
	 *                             N是P的右子树，一直往父节点走，直到是某个结点的左子树   
	 */
	
	public TreeNode searchNextNode(TreeNode N){
		if(N == null) return null;
		if(N.getRight() != null)return getFirst(N.getRight());//左->自己->右，如有自己有右，下一个节点就是右
		
		if(N.getParent().getLeft()==N) {
			return N.getParent();
		}else {
			while(N.getParent()!=null && N.getParent().getLeft()!=N) {
				N = N.getParent();
			}
			return N.getParent();
		}	
	}

 public TreeNode getFirst(TreeNode node) {
	 if(node == null) return null;
	 TreeNode curr = node;
	 while(curr.getLeft() != null) {
		 curr = curr.getLeft();
	 }
	 return curr;
 }
	
	
}
