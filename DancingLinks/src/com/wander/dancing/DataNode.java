package com.wander.dancing;

public class DataNode extends AbstractNode {
	
	//node value stored in content
	private NodeValue content;
	
	public void setValue(NodeValue value){
		content = value;
	}
	
	//remove a whole row of nodes
	public void removeFromRow(){
		//from left to right
		AbstractNode iterator = this;
		iterator.up.down = iterator.down;
		iterator.down.up = iterator.up;
		//move to next node
		iterator = iterator.right;
		while(iterator != this){
			iterator.up.down = iterator.down;
			iterator.down.up = iterator.up;
			//move to next node
			iterator = iterator.right;
		}
	}
	
	//restore a whole row of nodes
	public void restoreToRow(){
		AbstractNode iterator = this;
		iterator.up.down = iterator;
		iterator.down.up = iterator;
		//move to next node
		iterator = iterator.right;
		while(iterator != this){
			iterator.up.down = iterator;
			iterator.down.up = iterator;
			//move to next node
			iterator = iterator.right;
		}
	}
	
	//remove a whole column of nodes
	public void removeFromCol(){
		AbstractNode iterator = this;
		iterator.right.left = iterator.left;
		iterator.left.right = iterator.right;
		//move to next node
		iterator = iterator.down;
		while(iterator != this){
			/*
			if(!(iterator instanceof HeaderNode)){
			}
			*/
			iterator.right.left = iterator.left;
			iterator.left.right = iterator.right;
			//move to next node
			iterator = iterator.down;
		}
	}
	
	//restore a whole column of nodes
	public void restoreFromCol(){
		AbstractNode iterator = this;
		iterator.right.left = iterator;
		iterator.left.right = iterator;
		//move to next node
		iterator = iterator.down;
		while(iterator != this){
			/*
			if(!(iterator instanceof HeaderNode)){
			}
			*/
			iterator.right.left = iterator;
			iterator.left.right = iterator;
			//move to next node
			iterator = iterator.down;
		}
	}
}
