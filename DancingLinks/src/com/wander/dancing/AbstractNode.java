package com.wander.dancing;

public abstract class AbstractNode {

	protected AbstractNode left;
	protected AbstractNode right;
	protected AbstractNode up;
	protected AbstractNode down;

	public AbstractNode(){
		left = this;
		right = this;
		up = this;
		down = this;
	}
	
	public void insert(AbstractNode leftNode, AbstractNode rightNode){
		insert(leftNode, rightNode, this, this);
	}
	/**
	 * insert node in the specific place:
	 * 					'up node'
	 * 'left node'		'** node'		'right node'
	 * 					'down node'
	 * @param leftNode
	 * @param rightNode
	 * @param upNode
	 * @param downNode
	 */
	public void insert(AbstractNode leftNode, AbstractNode rightNode, AbstractNode upNode, AbstractNode downNode){
		leftNode.right = this;
		rightNode.left= this;
		upNode.down = this;
		downNode.up = this;
		left = leftNode;
		right = rightNode;
		up = upNode;
		down = downNode;
		/*
		System.out.println("********************************");
		System.out.println("	-"+upNode+"-	");
		System.out.println(leftNode +"-"+this+"-" +rightNode);
		System.out.println("	-"+downNode+"-	");		
		*/
	}
	
	/*
	 * remove a whole row of nodes
	 */
	public abstract void removeFromRow();

	/*
	 * restore a whole row of nodes
	 */
	public abstract void restoreToRow();

	/**
	 * remove a whole column of nodes
	 */
	public abstract void removeFromCol();

	/*
	 * restore a whole column of nodes
	 */
	public abstract void restoreToCol();
}