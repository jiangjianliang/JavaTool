package com.wander.dancing;

public class NodeValue {
	private int value = 0;
	public NodeValue(int value){
		this.value = value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
