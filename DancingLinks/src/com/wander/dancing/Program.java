package com.wander.dancing;

public class Program {

	public static void push(AbstractNode choice){
		if (choice instanceof HeaderNode) {
			// do nothing
			return;
		}
		// 遍历节点所在行
		AbstractNode next = choice;
		do {
			// 找出这些‘同义’行
			AbstractNode col = next;
			do {
				if(col instanceof HeaderNode){
					//do nothing, in case HeadNode
					return;
				}
				// 遍历‘同义’行,找到列
				AbstractNode col2 = col;
				do{
					// 删除‘同义行’的列
					((DataNode) col2).removeFromCol();
					col2 = col.left;// 遍历行
				}
				while(col2 != col);
				
				// 删除‘同义’行
				((DataNode) col).removeFromRow();
				
				col = next.down;// 遍历列
			} while (col != next);

			next = choice.left;
		} while (next != choice);

		// 最后删除这个节点所在行
		((DataNode) choice).removeFromRow();
	}
	
	//TODO 如何保证不会过分的还原 
	public static void pull(AbstractNode choice){
		if (choice instanceof HeaderNode) {
			// do nothing
			return;
		}
		
		((DataNode)choice).restoreToRow();
		// 遍历节点所在行
		AbstractNode next = choice;
		do {
			// 找出这些‘同义’行
			AbstractNode col = next;
			do {
				if(col instanceof HeaderNode){
					//do nothing, in case HeadNode
					return;
				}
				
				((DataNode) col).restoreToRow();
				
				// 遍历‘同义’行,找到列
				AbstractNode col2 = col;
				do{
					// 删除‘同义行’的列
					((DataNode) col2).restoreToCol();
					col2 = col.left;// 遍历行
				}
				while(col2 != col);

				col = next.down;// 遍历列
			} while (col != next);

			next = choice.left;
		} while (next != choice);
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
