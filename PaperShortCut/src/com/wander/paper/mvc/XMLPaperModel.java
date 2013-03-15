package com.wander.paper.mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLPaperModel extends AbstractPaperModel {

	private SAXReader reader = new SAXReader();
	private XMLWriter writer = null;
	private Document document = null;
	private OutputFormat format = OutputFormat.createPrettyPrint();

	private String filePath;
	private File file;

	private Element root;

	
	private boolean isModified;
	/**
	 * <Paper> <pi short ='' > <pc author='' title=''/> <pc author='' title=''/>
	 * </pi> <pi short='' > </pi> </Paper>
	 */
	public XMLPaperModel() {
		// TODO 以后决定怎么加载数据以及添加数据
		isModified = false;
		format.setEncoding("UTF-8");
		openAndLoad("data/paper.xml");
	}

	public void openAndLoad(String filePath) {
		this.filePath = filePath;
		file = new File(filePath);
		if (file.exists()) {
			try {
				document = reader.read(file);
				root = document.getRootElement();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			document = DocumentHelper.createDocument();
			root = document.addElement("Paper");
			doSyn();
		}
	}

	@Override
	protected PaperValueInterface doSearch(PaperKey key) {
		String xpath = "//pc[@short='" + key.getKey() + "']/pi";
		List list = document.selectNodes(xpath);
		if (list.isEmpty()) {
			return EmptyPaperValue.instance();
		}
		PaperValue paperValue = new PaperValue();
		for (Object obj : list) {
			Node node = (Node) obj;
			paperValue.addTitleAndAuthor(node.valueOf("@title"),
					node.valueOf("@author"));
		}
		return paperValue;
	}

	@Override
	protected void doAdd(PaperKey key, PaperValue value) {
		isModified = true;
		Element paperCollection = null;
		// 查找key对应的节点
		for (Iterator<Element> i = root.elementIterator("pc"); i.hasNext();) {
			Element pc = (Element) i.next();
			if (pc.attributeValue("short").equals(key.getKey())) {
				paperCollection = pc;
			}
		}
		// 不存在这样的key
		if (paperCollection == null) {
			paperCollection = root.addElement("pc");
			paperCollection.addAttribute("short", key.getKey());
		}
		// 找到|创建pc节点,然后需要在其下插入新的pi节点
		List<String> titleList = value.getTitleList();
		List<String> authorList = value.getAuthorList();
		for (int j = 0; j < titleList.size(); j++) {
			Element paperItem = paperCollection.addElement("pi");
			paperItem.addAttribute("title", titleList.get(j));
			paperItem.addAttribute("author", authorList.get(j));
		}
	}

	@Override
	protected void doSyn() {
		if(!isModified){
			return;
		}
		try {
			writer = new XMLWriter(new FileWriter(file), format);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
