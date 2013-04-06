package com.wander.dancing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NodeStack extends Stack<StackElement>{
	
	public boolean existNode(HeaderNode head){
		StackElement top = (StackElement) this.elementData[elementCount-1];
		
		if(top.head == head){
			return true;
		}
		return false;
	}
	
	public StackElement top(){
		return (StackElement) this.elementData[elementCount-1];
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i =0; i <elementCount; i++){
			str.append(elementData[i].toString());
		}
		return str.toString();
	}
}

class StackElement{
	public HeaderNode head;
	public DataNode data;
	public Stack<DataNode> dropRow = new Stack<DataNode>();
	public Stack<DataNode> dropCol = new Stack<DataNode>();
	
	public StackElement(HeaderNode head, DataNode data){
		this.head = head;
		this.data = data;
	}
	
	public boolean hasRow(){
		return !dropRow.isEmpty();
	}
	
	public void pushDropRow(AbstractNode data){
		dropRow.push((DataNode) data);
	}
	
	public DataNode popDropRow(){
		return dropRow.pop();
	}
	
	public boolean hasCol(){
		return !dropCol.isEmpty();
	}
	
	public void pushDropCol(AbstractNode data){
		dropCol.push((DataNode) data);
	}
	
	public DataNode popDropCol(){
		return dropCol.pop();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(head+"-");
		str.append(data+";");
		return str.toString();
	}
	
}
