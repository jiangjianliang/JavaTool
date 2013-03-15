package com.wander.chapter1;

import java.awt.event.*;
import javax.swing.*;

public class SubMenu extends JFrame {

	public SubMenu() {

		setTitle("Submenu");

		JMenuBar menubar = new JMenuBar();
		ImageIcon iconNew = new ImageIcon("new.png");
		ImageIcon iconOpen = new ImageIcon("open.png");
		ImageIcon iconSave = new ImageIcon("save.png");
		ImageIcon iconClose = new ImageIcon("exit.png");

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenu imp = new JMenu("Import");
		imp.setMnemonic(KeyEvent.VK_M);

		JMenuItem newsf = new JMenuItem("Import newsfeed list...");
		JMenuItem bookm = new JMenuItem("Import bookmarks...");
		JMenuItem mail = new JMenuItem("Import mail...");

		imp.add(newsf);
		imp.add(bookm);
		imp.add(mail);

		JMenuItem fileNew = new JMenuItem("New", iconNew);
		fileNew.setMnemonic(KeyEvent.VK_N);

		JMenuItem fileOpen = new JMenuItem("Open", iconOpen);
		fileNew.setMnemonic(KeyEvent.VK_O);

		JMenuItem fileSave = new JMenuItem("Save", iconSave);
		fileSave.setMnemonic(KeyEvent.VK_S);

		JMenuItem fileClose = new JMenuItem("Close", iconClose);
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit application");
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));

		fileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.addSeparator();
		file.add(imp);
		file.addSeparator();
		file.add(fileClose);

		menubar.add(file);

		setJMenuBar(menubar);

		setSize(360, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SubMenu();
	}

}
