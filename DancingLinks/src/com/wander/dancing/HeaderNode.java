package com.wander.dancing;

public class HeaderNode extends AbstractNode {
	private static int count = 0;
	private String value = null;
	
	public HeaderNode() {
		super();
		value = "H"+count;
		count++;
	}

	@Override
	public void removeFromRow() {
	}

	@Override
	public void restoreToRow() {
	}

	@Override
	public void removeFromCol() {
		AbstractNode iterator = this;
		iterator.right.left = iterator.left;
		iterator.left.right = iterator.right;
	}

	@Override
	public void restoreToCol() {
		AbstractNode iterator = this;
		iterator.right.left = iterator;
		iterator.left.right = iterator;
	}

	@Override
	public String toString() {
		return value;
	}
	
	
}
