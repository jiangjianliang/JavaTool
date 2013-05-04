package com.wander.tree.bst;

import com.wander.tree.bst.TreeNode.NodeKey;

public class BinarySearchTree implements BinarySearchTreeInterface{
	
	/**
	 * 递归方式查找节点
	 * @param tree
	 * @param key
	 * @return
	 */
	public TreeNode searchKeyRec(TreeNode tree, NodeKey key){
		return null;
	}
}

class TreeNode{
	private NodeKey key;
	private NodeValue value;
	private TreeNode leftChild;
	private TreeNode rightChild;
	private TreeNode parent;
	
	/**
	 * NodeKey类
	 * @author wander
	 *
	 */
	public class NodeKey implements Comparable<NodeKey>{
		int key;
		
		public NodeKey(){
			this(0);
		}
		
		public NodeKey(int key){
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
			if(obj instanceof NodeKey){
				return key == ((NodeKey)obj).getKey();
			}
			
			return false;
		}

		@Override
		public int compareTo(NodeKey o) {
			return key  - o.getKey();
		}
		
	}
	/**
	 * NodeValue类
	 * @author wander
	 *
	 */
	public class NodeValue{
		String value;
		public NodeValue(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
	
}
