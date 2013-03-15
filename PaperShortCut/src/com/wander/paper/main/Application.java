package com.wander.paper.main;

import com.wander.paper.mvc.PaperControl;
import com.wander.paper.mvc.PaperControlInterface;
import com.wander.paper.mvc.AbstractPaperModel;
import com.wander.paper.mvc.XMLPaperModel;

public class Application {
	//singleton
	private static Application instance = new Application();
	
	private Application(){
		AbstractPaperModel paperModel = new XMLPaperModel();
		PaperControlInterface paperControl = new PaperControl(paperModel);
	}
	
	/**
	 * singleton
	 * @return
	 */
	public static Application instance(){
		return instance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application app = Application.instance();
	}

}
