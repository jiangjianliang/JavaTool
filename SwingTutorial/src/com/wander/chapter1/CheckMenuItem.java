package com.wander.chapter1;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class CheckMenuItem extends JFrame {

	private JLabel statusbar;
	public CheckMenuItem(){
		setTitle("CheckBoxMenuItem");

        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show StatuBar");
        sbar.setState(true);

        sbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (statusbar.isVisible()) {
                    statusbar.setVisible(false);
                } else {
                    statusbar.setVisible(true);
                }
            }

        });

        view.add(sbar);

        menubar.add(file);
        menubar.add(view);

        setJMenuBar(menubar);

        statusbar = new JLabel(" Statusbar");
        statusbar.setBorder(BorderFactory.createEtchedBorder(
		EtchedBorder.RAISED));
        add(statusbar, BorderLayout.SOUTH);


        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
new CheckMenuItem();
	}

}
