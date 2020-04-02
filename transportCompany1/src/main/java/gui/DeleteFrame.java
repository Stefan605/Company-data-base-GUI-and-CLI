package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.CompanyDAO;
import entity.Company;

public class DeleteFrame extends JFrame {
	
	public void visibility(boolean state) {
		this.setVisible(state);
	}
	
	DeleteFrame(){
		this.setTitle("Deleting company");
		this.setLayout(new GridLayout(0,1));
		this.setSize(400,250);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Are you sure you want to delete company \""
				+CompanyDAO.returnCompany(Gui.getId()).getName()+"\"?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(label);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		
		JButton yesButton = new JButton("Yes");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompanyDAO.deleteCompany(Gui.getId());
				Gui.companiesList = new JList(CompanyDAO.readCompanies().toArray());
				Gui.panel1.removeAll();
			    Gui.panel1.add(Gui.companiesList);
			    Gui.frame.revalidate();
			    Gui.frame.repaint();
			    visibility(false);
			    Gui.companiesList.clearSelection();
			}
			});
		panel2.add(yesButton);
		
		
		JButton noButton = new JButton("No");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(false);
				Gui.companiesList.clearSelection();
			}
			});
		panel2.add(noButton);
		
		this.add(panel1);
		this.add(panel2);
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	DeleteFrame(int a){
		this.setTitle("Deleting companies");
		this.setLayout(new GridLayout(0,1));
		this.setSize(400,250);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Are you sure you want to delete the selected companies?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(label);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		
		JButton yesButton = new JButton("Yes");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int array[] = Gui.companiesList.getSelectedIndices();
				
				for(int i = 0;i<array.length;i++) {
					Gui.companiesList.setSelectedIndex(array[i]);
					CompanyDAO.deleteCompany(Gui.getId());
				}
				
				Gui.companiesList = new JList(CompanyDAO.readCompanies().toArray());
				Gui.panel1.removeAll();
			    Gui.panel1.add(Gui.companiesList);
			    Gui.frame.revalidate();
			    Gui.frame.repaint();
			    visibility(false);
			    Gui.companiesList.clearSelection();
			}
			});
		panel2.add(yesButton);
		
		
		JButton noButton = new JButton("No");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(false);
				Gui.companiesList.clearSelection();
			}
			});
		panel2.add(noButton);
		
		this.add(panel1);
		this.add(panel2);
		this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

}
