package com.wander.dancing;

public class DataNode extends AbstractNode {

	// node value stored in content
	private NodeValue content;

	public void setValue(NodeValue value) {
		content = value;
	}

	@Override
	public void removeFromRow() {
		// from left to right
		AbstractNode iterator = this;
		do {
			iterator.up.down = iterator.down;
			iterator.down.up = iterator.up;
			// move to next node
			iterator = iterator.right;
		} while (iterator != this);
	}

	@Override
	public void restoreToRow() {
		AbstractNode iterator = this;
		do {
			iterator.up.down = iterator;
			iterator.down.up = iterator;
			// move to next node
			iterator = iterator.right;
		} while (iterator != this);
	}

	@Override
	public void removeFromCol() {
		AbstractNode next = down;
		do{
			if(next instanceof HeaderNode){
				next.removeFromCol();
				break;
			}
			next = next.down;
		}while(next != this);
	}

	@Override
	public void restoreToCol() {
		AbstractNode next = down;
		do{
			if(next instanceof HeaderNode){
				next.restoreToCol();
				break;
			}
			next = next.down;
		}while(next != this);
	}

	@Override
	public String toString() {
		return content.toString();
	}
	
	
}
