package com.wander.paper.mvc;

import java.util.ArrayList;
import java.util.List;

public class PaperValue implements PaperValueInterface{
	private List<String> titleList = new ArrayList<String>();
	private List<String> authorList = new ArrayList<String>();
	
	public PaperValue(){
		
	}
	@Override
	public int size() {
		return titleList.size();
	}
	
	public void addTitleAndAuthor(String title, String author){
		titleList.add(title);
		authorList.add(author);
	}
	
	public void addAll(PaperValue value){
		titleList.addAll(value.getTitleList());
		authorList.addAll(value.getAuthorList());
	}
	
	public List<String> getTitleList() {
		return titleList;
	}

	public List<String> getAuthorList() {
		return authorList;
	}
}

class EmptyPaperValue implements PaperValueInterface{
	
	//singleton
	private static EmptyPaperValue instance = new EmptyPaperValue();
	
	private EmptyPaperValue(){
		
	}
	
	public static EmptyPaperValue instance(){
		return instance;
	}

	@Override
	public int size() {
		return 0;
	}
	
}

