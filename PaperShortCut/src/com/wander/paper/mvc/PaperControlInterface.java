package com.wander.paper.mvc;

public interface PaperControlInterface {

	public abstract void search(String key);

	public abstract void add(String key, String title, String author);

	public abstract void syn();

}