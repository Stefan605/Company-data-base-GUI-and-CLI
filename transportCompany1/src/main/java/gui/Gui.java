package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import entity.Company;
import dao.CompanyDAO;

public class Gui implements Runnable {
	static JList companiesList = new JList(CompanyDAO.readCompanies().toArray());
	static JPanel panel1;
	static JPanel panel2;
	static JFrame frame;
	
	public static int getId() {
		return Integer.valueOf(companiesList.getSelectedValue().toString().substring(
				companiesList.getSelectedValue().toString().indexOf('=')+1,
				companiesList.getSelectedValue().toString().indexOf(',')));
	}
	
	@Override
	public void run() {
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(0,1));
		frame.setTitle("Transport Company Database");
		
		JScrollPane scrollPane = new JScrollPane();
		
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(companiesList);
		scrollPane.setViewportView(panel1);
		frame.add(scrollPane);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		frame.add(panel2);
		
		
		
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				CreateFrame create = new CreateFrame();
			}
		});
		panel2.add(createButton);
		
		
		
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(companiesList.isSelectionEmpty()) {
					ErrorFrame error = new ErrorFrame("Select a company to update");
				}else {
					UpdateFrame update = new UpdateFrame();
				}
			}
		});
		panel2.add(updateButton);
		
		
		
		JButton copyButton = new JButton("Copy");
		copyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(companiesList.isSelectionEmpty()) {
					ErrorFrame error = new ErrorFrame("Select a company to copy");
				}else {
					CompanyDAO.copyCompany(getId());
					companiesList = new JList(CompanyDAO.readCompanies().toArray());
					panel1.removeAll();
				    panel1.add(Gui.companiesList);
				    frame.revalidate();
				    frame.repaint();
				    Gui.companiesList.clearSelection();
				}
				
			}
			
		});
		panel2.add(copyButton);
		
		
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(companiesList.isSelectionEmpty()) {
					ErrorFrame error = new ErrorFrame("Select a company to delete");
				}else if(companiesList.getSelectedValuesList().size()==1){
					DeleteFrame delete = new DeleteFrame();
				}else {
					DeleteFrame delete = new DeleteFrame(1);
				}
			}
		});
		panel2.add(deleteButton);
		
		
		
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFrame search = new SearchFrame();
			}
		});
		panel2.add(searchButton);
		
		
		
		
		JButton exitButton = new JButton("Close");
		exitButton.addActionListener(new ActionListener() {
			JFrame confirmExit = new JFrame();
			JPanel confirmPanel1 = new JPanel();
			JPanel confirmPanel2 = new JPanel();
			JLabel confirmLabel = new JLabel("Exit?");
			JButton confirmYes = new JButton("Yes");
			JButton confirmNo = new JButton("No");
			public void actionPerformed(ActionEvent e) {
				confirmExit.setLayout(new GridLayout(0,1));
				confirmExit.setSize(300,150);
				confirmExit.setTitle("Exiting Database");
				confirmPanel1.setLayout(new BorderLayout());
				confirmPanel2.setLayout(new FlowLayout());
				confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				confirmYes.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				
				confirmNo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						confirmExit.setVisible(false);	
					}
				});
				confirmExit.add(confirmPanel1);
				confirmExit.add(confirmPanel2);
				confirmPanel1.add(confirmLabel);
				confirmPanel2.add(confirmYes);
				confirmPanel2.add(confirmNo);
				
				
				confirmExit.setLocationRelativeTo(null);
				confirmExit.setVisible(true);
			}
		});
		panel2.add(exitButton);
		
		
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
	}

}
