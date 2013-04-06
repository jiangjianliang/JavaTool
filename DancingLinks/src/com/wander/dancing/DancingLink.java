package com.wander.dancing;

import java.util.ArrayList;
import java.util.List;

public class DancingLink {
	private AbstractNode iHeader = new HeaderNode();;
	private AbstractNode[] headRow;
	private AbstractNode[] headFlatRow;
	private AbstractNode[] headDataCol;
	private AbstractNode[] headFlatCol;

	private NodeStack prevSelect = new NodeStack();

	public DancingLink() {
		// m*n Matrix
		int m = 4;
		int n = 5;
		headRow = new AbstractNode[n];
		headFlatRow = new AbstractNode[n];
		headDataCol = new AbstractNode[m];
		headFlatCol = new AbstractNode[m];

		// initial head row
		headRow[0] = new HeaderNode();
		headRow[0].insert(iHeader, iHeader);
		headFlatRow[0] = headRow[0];
		for (int i = 1; i < n; i++) {
			headRow[i] = new HeaderNode();
			headRow[i].insert(headRow[i - 1], iHeader);
			headFlatRow[i] = headRow[i];
		}
		// TODO initial the matrix, suppose we have a m*n matrix
		// in matrix, 1 represent an element
		int[][] matrix = { { 11, 12, 13, 0, 0 }, { 0, 0, 0, 0, 25 },
				{ 0, 32, 33, 0, 0 }, { 41, 0, 0, 44, 0 } };
		for (int rowIndex = 0; rowIndex < m; rowIndex++) {
			boolean isFirst = true;
			for (int colIndex = 0; colIndex < n; colIndex++) {
				if (matrix[rowIndex][colIndex] != 0) {
					AbstractNode node = new DataNode();
					((DataNode) node).setValue(new NodeValue(
							matrix[rowIndex][colIndex]));
					if (isFirst) {
						headDataCol[rowIndex] = node;
						headFlatCol[rowIndex] = node;
						isFirst = false;
					}
					node.insert(headFlatCol[rowIndex], headDataCol[rowIndex],
							headFlatRow[colIndex], headRow[colIndex]);
					headFlatCol[rowIndex] = node;
					headFlatRow[colIndex] = node;
				}
			}
		}
		/*
		for(int j = 0; j < n; j++){
			AbstractNode col = headRow[j];
			do{
				System.out.print(col+",");
				col = col.down;
			}while(col != headRow[j]);			
			System.out.println("+++++++++++++++");
		}
		
		for(int k = 0; k<m; k++){
			AbstractNode row = headDataCol[k];
			do{
				System.out.print(row+",");
				row = row.right;
			}while(row != headDataCol[k]);
		}
		*/
	}

	/**
	 * 算法运行主框架
	 */
	public void recRun() {
		System.err.println("in rec");
		if (!hasMoreCol()) {
			System.out.println("we have one solution");
			System.out.println(prevSelect);
			return;
		}
		AbstractNode headItr = iHeader.right;

		List<AbstractNode> dataList = doSelection((HeaderNode) headItr);
		if (dataList.isEmpty()) {
			System.out.println("this path is wrong");

		}
		for (AbstractNode choice : dataList) {
			doForward(headItr, choice);
			recRun();
			doBackward(headItr, choice);
		}
		return;
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
	private List<AbstractNode> doSelection(HeaderNode head) {
		List<AbstractNode> list = new ArrayList<AbstractNode>();
		if (head.down != head) {
			AbstractNode itr = head.down;
			do {
				list.add(itr);
				itr = itr.down;
			} while (itr != head);
		}
		return list;
	}

	/**
	 * 算法推进一步
	 * 
	 * @param choice
	 */
	public void doForward(AbstractNode head, AbstractNode choice) {
		if (choice instanceof HeaderNode) {
			// do nothing
			return;
		}
		StackElement top = new StackElement((HeaderNode) head,
				(DataNode) choice);
		prevSelect.push(top);
		AbstractNode rowItr = choice;
		do {
			AbstractNode colItr = rowItr.down;
			do {
				if(colItr instanceof HeaderNode){
					colItr = colItr.down;
					continue;
				}
				prevSelect.top().pushDropRow(colItr);
				colItr.removeFromRow();
				colItr = colItr.down;
			} while (colItr != rowItr);
			prevSelect.top().pushDropCol(rowItr);
			rowItr.removeFromCol();
			rowItr = rowItr.right;
		} while (rowItr != choice);

		rowItr.removeFromRow();
	}

	/**
	 * 回溯一步
	 * 
	 * @param choice
	 */
	public void doBackward(AbstractNode head, AbstractNode choice) {
		StackElement top = prevSelect.pop();
		top.data.restoreToRow();
		while (top.hasCol()) {
			top.popDropCol().restoreToCol();
		}
		while (top.hasRow()) {
			top.popDropRow().restoreToRow();
		}
	}
}
