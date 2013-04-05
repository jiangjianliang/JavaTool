package com.wander.dancing;

public abstract class AbstractNode {

	protected AbstractNode left;
	protected AbstractNode right;
	protected AbstractNode up;
	protected AbstractNode down;

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
	public void removeFromCol() {
		AbstractNode iterator = this;
		do {
			iterator.right.left = iterator.left;
			iterator.left.right = iterator.right;
			// move to next node
			iterator = iterator.down;
		} while (iterator != this);
	}

	/*
	 * restore a whole column of nodes
	 */
	public void restoreToCol() {

		AbstractNode iterator = this;
		do {
			iterator.right.left = iterator;
			iterator.left.right = iterator;
			// move to next node
			iterator = iterator.down;
		} while (iterator != this);
	}
}