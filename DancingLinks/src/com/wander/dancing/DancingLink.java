package com.wander.dancing;

import java.util.Stack;

public class DancingLink {
	private AbstractNode iHeader = new HeaderNode();;
	private AbstractNode[] headerRow;

	private NodeStack prevSelect = new NodeStack();

	public DancingLink() {

	}

	/**
	 * 算法运行主框架
	 */
	public void run() {
		while (hasMoreCol()) {
			AbstractNode choice = doSelection((HeaderNode) iHeader.left);
			if (choice == null) {
				// 需要回溯
				System.err.println("error in selecting nodes");

				System.exit(-1);
			}

		}
	}

	public void recRun() {
		if (!hasMoreCol()) {
			System.out.println("we have one solution");
			return;
		}
		AbstractNode iterator = iHeader.left;
		do {
			AbstractNode choice = null;
			do {
				choice = doSelection((HeaderNode) iterator);
				if (choice != null) {
					doForward(choice);
					recRun();
					doBackward(choice);
				}
			} while (choice != null);

			iterator = iterator.left;
		} while (iterator != iHeader);

	}

	/**
	 * 还有未填充的行
	 * 
	 * @return
	 */
	private boolean hasMoreCol() {
		return iHeader.left != iHeader;
	}

	/**
	 * 
	 * @param first
	 * @return
	 */
	private AbstractNode doSelection(HeaderNode head) {
		if (prevSelect.existNode((HeaderNode) head)) {
			StackElement top =prevSelect.pop();
			if(top.data.down == head){
				return null;
			}
			else{
				top.data = (DataNode) top.data.down;
				prevSelect.push(top);
			}
		} else {
			//是否应该考虑head.down指向自身的情况
			prevSelect.push(new StackElement(head, (DataNode) head.down));
		}
		return null;
	}

	/**
	 * 算法推进一步
	 * 
	 * @param choice
	 */
	public void doForward(AbstractNode choice) {
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
				if (col instanceof HeaderNode) {
					// do nothing, in case HeadNode
					return;
				}
				// 遍历‘同义’行,找到列
				AbstractNode col2 = col;
				do {
					// 删除‘同义行’的列
					((DataNode) col2).removeFromCol();
					col2 = col.left;// 遍历行
				} while (col2 != col);

				// 删除‘同义’行
				((DataNode) col).removeFromRow();

				col = next.down;// 遍历列
			} while (col != next);

			next = choice.left;
		} while (next != choice);

		// 最后删除这个节点所在行
		((DataNode) choice).removeFromRow();
	}

	/**
	 * 算法撤销一步 如何保证不会过分的还原 假设存在另一个行和当前选择的行或者其'同义'行存在公共列, 这样的列会被移除,从而不存在
	 * 
	 * @param choice
	 */
	public void doBackward(AbstractNode choice) {
		if (choice instanceof HeaderNode) {
			// do nothing
			return;
		}

		((DataNode) choice).restoreToRow();
		// 遍历节点所在行
		AbstractNode next = choice;
		do {
			// 找出这些‘同义’行
			AbstractNode col = next;
			do {
				if (col instanceof HeaderNode) {
					// do nothing, in case HeadNode
					return;
				}

				((DataNode) col).restoreToRow();

				// 遍历‘同义’行,找到列
				AbstractNode col2 = col;
				do {
					// 删除‘同义行’的列
					((DataNode) col2).restoreToCol();
					col2 = col.left;// 遍历行
				} while (col2 != col);

				col = next.down;// 遍历列
			} while (col != next);

			next = choice.left;
		} while (next != choice);

	}
}
