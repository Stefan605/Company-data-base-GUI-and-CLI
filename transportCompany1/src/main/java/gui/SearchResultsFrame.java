package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.CompanyDAO;

public class SearchResultsFrame extends JFrame {
	
	static JList searchResultList;
	static JPanel panel1;
	static JPanel panel2;
	
	public void visibility(boolean state) {
		this.setVisible(state);
	}
	
	SearchResultsFrame(String criteria){
		this.setTitle("Search Results");
		
		searchResultList = new JList(CompanyDAO.searchCompaniesByCriteria(criteria).toArray());
		JTextArea emptyResult = new JTextArea();
		emptyResult.setSize(250, 300);
		emptyResult.setAlignmentX(CENTER_ALIGNMENT);
		emptyResult.setText("Nothing to display");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(500,500);
		this.setLayout(new GridLayout(0,1));
		
		JScrollPane scrollPane = new JScrollPane();
		
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(searchResultList);
		scrollPane.setViewportView(panel1);
		if(searchResultList.getModel().getSize()==0) {
			this.add(emptyResult);
		}else {
			this.add(scrollPane);
		}
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		this.add(panel2);
		
		JButton newSearchButton = new JButton("New search");
		newSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFrame search = new SearchFrame();
				visibility(false);
			}
		});
		panel2.add(newSearchButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(false);
			}
		});
		panel2.add(closeButton);
	}

}
