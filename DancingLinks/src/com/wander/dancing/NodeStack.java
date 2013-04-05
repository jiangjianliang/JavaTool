package com.wander.dancing;

import java.util.Stack;

public class NodeStack extends Stack<StackElement>{
	
	public boolean existNode(HeaderNode head){
		StackElement top = (StackElement) this.elementData[elementCount-1];
		
		if(top.head == head){
			return true;
		}
		return false;
	}
}

class StackElement{
	public HeaderNode head;
	public DataNode data;
	public StackElement(HeaderNode head, DataNode data){
		this.head = head;
		this.data = data;
	}
}
