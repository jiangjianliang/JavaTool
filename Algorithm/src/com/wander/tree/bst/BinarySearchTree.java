package com.wander.tree.bst;

public class BinarySearchTree implements BinarySearchTreeInterface {
	private TreeNode root;

	
	public TreeNode searchKey(NodeKey key){
		return null;
	}
	
	/**
	 * 递归查找key对应的节点
	 * 
	 * @param key
	 * @return
	 */
	public TreeNode searchKeyRec(NodeKey key) {
		return searchKeyRecHelper(root, key);
	}

	/**
	 * 在tree下递归查找key对应的节点
	 * 
	 * @param tree
	 * @param key
	 * @return
	 */
	private TreeNode searchKeyRecHelper(TreeNode tree, NodeKey key) {
		if (tree == null || tree.isKey(key)) {
			return tree;
		}
		if (tree.compareTo(key) > 0) {
			return searchKeyRecHelper(tree.getLeftChild(), key);
		} else
			return searchKeyRecHelper(tree.getRightChild(), key);
	}
	
	/**
	 * 找出 tree下KEY最大的节点
	 * @param tree
	 * @return
	 */
	public TreeNode maxTreeNode(TreeNode tree){
		if(isEmpty(tree)){
			return null;
		}
		TreeNode iter = tree;
		while(iter.getRightChild() != null){
			iter = iter.getRightChild();
		}
		return iter;
	}
	/**
	 * 找出tree下KEY最小的节点
	 * @param tree
	 * @return
	 */
	public TreeNode minTreeNode(TreeNode tree){
		if(isEmpty(tree)){
			return null;
		}
		TreeNode iter = tree;
		while(iter.getLeftChild() != null){
			iter = iter.getRightChild();
		}
		return iter;
	}
	/**
	 * 找出tree的后继
	 * @param node
	 * @return
	 */
	public TreeNode succNode(TreeNode node){
		if(isEmpty(node)){
			return null;
		}
		//case 1，右子树下的最小节点
		if(node.getRightChild() != null){
			return minTreeNode(node.getRightChild());
		}
		//case 2，作为左子女的最低祖先节点
		TreeNode parent = node.getParent();
		TreeNode child = node;
		while(parent != null && parent.isRightChild(child)){
			child = parent;
			parent = parent.getParent();
		}
		return parent;
	}
	/**
	 * 找出tree的前驱
	 * @param node
	 * @return
	 */
	public TreeNode predecNode(TreeNode node){
		if(isEmpty(node)){
			return null;
		}
		//case 1，左子树的最大节点
		if(node.getLeftChild() != null){
			return maxTreeNode(node.getLeftChild());
		}
		//case 2，最为右子女的最低祖先节点
		TreeNode parent = node.getParent();
		TreeNode child = node;
		while(parent != null && parent.isLeftChild(child)){
			child = parent;
			parent = parent.getParent();
		}
		return parent;
	}
	/**
	 * 插入节点node
	 * @param node
	 */
	public void insert(TreeNode node){
		
	}
	/**
	 * 删除key对应的节点
	 * @param key
	 */
	public void remove(NodeKey key){
		
	}
	
	
	private boolean isEmpty(TreeNode tree){
		return tree == null;
	}
	
}

/**
 * 树的节点类，分别包含 key value leftChild rightChild parent
 * 
 * @author wander
 * 
 */
class TreeNode implements Comparable<TreeNode> {
	private NodeKey key;
	private NodeValue value;
	private TreeNode leftChild;
	private TreeNode rightChild;
	private TreeNode parent;

	public boolean isRightChild(TreeNode node){
		return rightChild == node;
	}
	
	public boolean isLeftChild(TreeNode node){
		return leftChild == node;
	}
	
	/**
	 * 是否为key对应的节点
	 * 
	 * @param key
	 * @return
	 */
	public boolean isKey(NodeKey key) {
		return this.key.equals(key);
	}

	@Override
	public int compareTo(TreeNode o) {
		return compareTo(o.getKey());
	}

	/**
	 * 比较KEY的大小
	 * 
	 * @param key
	 * @return
	 */
	public int compareTo(NodeKey key) {
		return this.key.compareTo(key);
	}

	public NodeKey getKey() {
		return key;
	}

	public void setKey(NodeKey key) {
		this.key = key;
	}

	public NodeValue getValue() {
		return value;
	}

	public void setValue(NodeValue value) {
		this.value = value;
	}

	public TreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}

/**
 * NodeKey类
 * 
 * @author wander
 * 
 */
class NodeKey implements Comparable<NodeKey> {
	int key;

	public NodeKey() {
		this(0);
	}

	public NodeKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NodeKey) {
			return key == ((NodeKey) obj).getKey();
		}

		return false;
	}

	@Override
	public int compareTo(NodeKey o) {
		return key - o.getKey();
	}

}

/**
 * NodeValue类
 * 
 * @author wander
 * 
 */
class NodeValue {
	String value;

	public NodeValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
