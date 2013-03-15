package com.wander.paper.mvc;

import com.wander.paper.observer.Subject;

public abstract class AbstractPaperModel extends Subject{
	
	protected PaperKey currentPaperKey;
	
	protected PaperValueInterface currentPaperValue = EmptyPaperValue.instance();
	
	public AbstractPaperModel(){
		
	}
	
	public String getPaperKeyString() {
		return currentPaperKey.toString();
	}

	public PaperValueInterface getPaperValue() {
		return currentPaperValue;
	}

	/**
	 * 查找key对应的value
	 * @param key
	 */
	public void search(PaperKey key) {
		currentPaperKey = key;
		PaperValueInterface value = doSearch(key);
		if (value == null) {
			currentPaperValue =  EmptyPaperValue.instance();
		} else {
			currentPaperValue = value;
		}
		
		notifyObservsers();
	}

	/**
	 * 以<key, value>的方式添加新的键值对
	 * @param key
	 * @param value
	 */
	public void add(PaperKey key, PaperValue value) {
		currentPaperKey = key;
		doAdd(key, value);
		
	}

	/**
	 * 同步XML中的内容到文件中
	 */
	public void syn() {
		doSyn();
	}

	/**
	 * 查找key对应的记录
	 * @param key
	 * @return
	 */
	protected abstract PaperValueInterface doSearch(PaperKey key);
	
	/**
	 * 向其中间结构中添加<key, value>
	 * @param key
	 * @param value
	 */
	protected abstract void doAdd(PaperKey key, PaperValue value);
	
	/**
	 * 将中间结构中的内容存放到文件中
	 */
	protected abstract void doSyn();

}
