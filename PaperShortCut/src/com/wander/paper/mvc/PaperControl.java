package com.wander.paper.mvc;

public class PaperControl implements PaperControlInterface{
	private AbstractPaperModel paperModel;
	private PaperView view;
	
	public PaperControl(AbstractPaperModel paperModel2){
		paperModel = paperModel2;
		view = new PaperView(paperModel2, this);
		view.init();
	}

	/* (non-Javadoc)
	 * @see com.wander.paper.mvc.PaperControlInterface#search(java.lang.String)
	 */
	@Override
	public void search(String key) {
		PaperKey paperKey = new PaperKey(key);
		paperModel.search(paperKey);
	}
	
	/* (non-Javadoc)
	 * @see com.wander.paper.mvc.PaperControlInterface#add(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String key, String title, String author){
		PaperKey paperKey = new PaperKey(key);
		PaperValue paperValue = new PaperValue();
		paperValue.addTitleAndAuthor(title, author);
		paperModel.add(paperKey, paperValue);
	}
	
	/* (non-Javadoc)
	 * @see com.wander.paper.mvc.PaperControlInterface#syn()
	 */
	@Override
	public void syn(){
		paperModel.syn();
	}
	
	
}
