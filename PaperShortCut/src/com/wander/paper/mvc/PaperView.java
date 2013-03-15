package com.wander.paper.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.wander.paper.observer.Observer;
import com.wander.paper.observer.Subject;

public class PaperView implements Observer{
	private JFrame frame;
	private JComponent tabPane = new JTabbedPane();
	
	private JComponent inputField = new JTextField();
	private JComponent searchBtn = new JButton("search");
	private JComponent displayArea = new JTextPane();
	
	private JComponent shortField = new JTextField();
	private JComponent titleField = new JTextField();
	private JComponent authorField = new JTextField();
	private JComponent addBtn = new JButton("add");
	
	private Document doc;
	private SimpleAttributeSet authorAttrSet;
	private SimpleAttributeSet titleAttrSet;
	private SimpleAttributeSet noticeAttrSet;
	
	private AbstractPaperModel paperModel;
	private PaperControlInterface paperControl;
	
	public PaperView(AbstractPaperModel model, PaperControlInterface control){
		paperModel = model;
		paperControl = control;
		
		((Subject)paperModel).registerObserver(this);
	}
	
	public void init(){
		
		JComponent searchPane = new JPanel();
		searchPane.setLayout(new BorderLayout());
		searchPane.add(inputField, BorderLayout.CENTER);
		searchPane.add(searchBtn, BorderLayout.EAST);
		
		displayArea.setEnabled(false);
		displayArea.setBackground(Color.GRAY);
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(displayArea, BorderLayout.CENTER);
		panel1.add(searchPane, BorderLayout.NORTH);
		
		//TODO 第二个tab还可以添加
		JComponent panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4,2));
		panel2.add(new JLabel("short"));
		panel2.add(shortField);
		panel2.add(new JLabel("title"));
		panel2.add(titleField);
		panel2.add(new JLabel("author"));
		panel2.add(authorField);
		panel2.add(addBtn);
		
		tabPane.add("查找", panel1);
		tabPane.add("添加", panel2);
		
		
		frame = new JFrame("PaperShortCut Dict");
		frame.setLayout(new BorderLayout());
		frame.add(tabPane, BorderLayout.CENTER);
		
		
		addListener();
		initTextPane();
		
		frame.setTitle("By jianliang.j@gmail.com/wander");
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	/*
	 * 添加监听器
	 */
	private void addListener(){
		((AbstractButton) searchBtn).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String key = ((JTextField) inputField).getText();
				if(key != null && !key.trim().equals("")){
					paperControl.search(key.trim());
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				paperControl.syn();
				super.windowClosing(e);
				System.exit(0);
			}

		});
		
		inputField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					paperControl.search(((JTextField) inputField).getText().trim());
				}
			}

		});
		
		((AbstractButton)addBtn).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String key = ((JTextField) shortField).getText();
				String title = ((JTextField) titleField).getText();
				String author = ((JTextField) authorField).getText();
				if(isNullOrEmpty(key)){
					return;
				}
				key = key.trim();
				if(isNullOrEmpty(title)){
					return;
				}
				title = title.trim();
				if(isNullOrEmpty(author)){
					return;
				}
				author = author.trim();
				paperControl.add(key, title, author);
			}
			
		});
	}
	
	@Override
	public void update(Subject observable) {
		PaperValueInterface paperValue = ((AbstractPaperModel)observable).getPaperValue();
		clearDoc();
		//TODO 以后决定格式
		if(paperValue.size() == 0){
			insertToDoc("Sorry, NOT FOUND\n", noticeAttrSet);
		}
		else{
			List<String> titleList = ((PaperValue) paperValue).getTitleList();
			List<String> authorList = ((PaperValue) paperValue).getAuthorList();
			updateDoc(((AbstractPaperModel) paperModel).getPaperKeyString(), authorList, titleList);
		}
	}
	
	private void updateDoc(String name, List<String> authorList,
			List<String> titleList) {
		if (!name.equals("")) {
			insertToDoc(name + "\n", titleAttrSet);
		}
		for (int i = 0; i < authorList.size(); i++) {
			insertToDoc(authorList.get(i), authorAttrSet);
			insertToDoc(titleList.get(i) + "\n", titleAttrSet);

		}
	}
	/**
	 * 初始化展示面板
	 */
	private void initTextPane() {
		authorAttrSet = new SimpleAttributeSet();
		StyleConstants.setForeground(authorAttrSet, Color.GREEN);
		StyleConstants.setBold(authorAttrSet, true);
		StyleConstants.setFontSize(authorAttrSet, 14);

		titleAttrSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(titleAttrSet, 20);

		noticeAttrSet = new SimpleAttributeSet();
		StyleConstants.setForeground(noticeAttrSet, Color.RED);
		StyleConstants.setFontSize(noticeAttrSet, 30);

		doc = ((JTextComponent) displayArea).getDocument();
	}
	/**
	 * 清空展示面板
	 */
	private void clearDoc() {
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 以attrSet中定义的格式添加字符串str
	 * @param str
	 * @param attrSet
	 */
	private void insertToDoc(String str, AttributeSet attrSet) {
		try {
			doc.insertString(doc.getLength(), str, attrSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isNullOrEmpty(String str){
		if(str == null || str.equals("")){
			return true;
		}
		else{
			return false;
		}
	}
}
