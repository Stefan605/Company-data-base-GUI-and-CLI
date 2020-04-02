package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import dao.CompanyDAO;

public class UpdateFrame extends JFrame {
	
	public void visibility(boolean state) {
		this.setVisible(state);
	}
	
	UpdateFrame(){
		this.setTitle("Updating company \""+CompanyDAO.returnCompany(Gui.getId()).getName()+"\".");
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameBox = new JTextField(25);
		nameBox.setText(CompanyDAO.returnCompany(Gui.getId()).getName());
		JLabel addressLabel = new JLabel("Address: ");
		JTextField addressBox = new JTextField(25);
		addressBox.setText(CompanyDAO.returnCompany(Gui.getId()).getAddress());
		JLabel incomeLabel = new JLabel("Income: ");
		JTextField incomeBox = new JTextField(25);
		incomeBox.setText("");
		
		this.setLayout(new GridLayout(5,2));
		this.setSize(400,150);
		this.add(nameLabel);
		this.add(nameBox);
		this.add(addressLabel);
		this.add(addressBox);
		this.add(incomeLabel);
		this.add(incomeBox);
		
		JButton updateFrameButton = new JButton("Update");
		updateFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(nameBox.getText().isBlank() 
						|| addressBox.getText().isBlank() 
						|| incomeBox.getText().isBlank()) {
					ErrorFrame error = new ErrorFrame("Please fill all fields");
					error.setVisible(true);
				}else if(!incomeBox.getText().matches("-?\\d+(\\.\\d+)?")){
					ErrorFrame error = new ErrorFrame("Income can only be a number");
					error.setVisible(true);
					}else{
					CompanyDAO.updateCompany2(Gui.getId(), 
								nameBox.getText(), 
								Integer.valueOf(incomeBox.getText()), 
								addressBox.getText());
					
					Gui.companiesList = new JList(CompanyDAO.readCompanies().toArray());
					Gui.panel1.removeAll();
					Gui.panel1.add(Gui.companiesList);
					Gui.frame.revalidate();
					Gui.frame.repaint();
					Gui.companiesList.clearSelection();
					visibility(false);
					nameBox.setText("");
					addressBox.setText("");
					incomeBox.setText("");
				}
			}
		});
		
		JButton updateFrameCancelButton = new JButton("Cancel");
		updateFrameCancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameBox.setText("");
				addressBox.setText("");
				incomeBox.setText("");
				visibility(false);
			}
		});
		this.add(updateFrameButton);
		this.add(updateFrameCancelButton);
		
	}

}
