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

public class SearchFrame extends JFrame{
	
	public void visibility(boolean state) {
		this.setVisible(state);
	}
	
	SearchFrame(){
		this.setTitle("Search companies");
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameBox = new JTextField(25);
		nameBox.setText("");
		JLabel addressLabel = new JLabel("Address: ");
		JTextField addressBox = new JTextField(25);
		addressBox.setText("");
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
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String text = "SELECT a FROM Company a WHERE ";
				
				if(!incomeBox.getText().isEmpty() && !incomeBox.getText().matches("-?\\d+(\\.\\d+)?")){
					ErrorFrame error = new ErrorFrame("Income can only be a number");
					error.setVisible(true);
				}else if(nameBox.getText().isBlank() 
						&& addressBox.getText().isBlank() 
						&& incomeBox.getText().isBlank()) {
					ErrorFrame error = new ErrorFrame("You cannot leave all fields empty");
					error.setVisible(true);
				}else {
					if(!nameBox.getText().isBlank()) {
						text+="name LIKE \'%"+nameBox.getText()+"%\'";
						if(!addressBox.getText().isBlank() || !incomeBox.getText().isBlank()) {
							text+=" AND ";
						}
					}
					
						if(!addressBox.getText().isBlank()) {
						text+="address LIKE \'%"+addressBox.getText()+"%\'";
						if(!incomeBox.getText().isBlank() || !incomeBox.getText().isBlank()) {
							text+=" AND ";
						}
						}
						
						if(!incomeBox.getText().isBlank()) {
							text+="income>"+incomeBox.getText();
						}	
						
						
						
						SearchResultsFrame searchResults = new SearchResultsFrame(text);
				
						visibility(false);
						nameBox.setText("");
						addressBox.setText("");
						incomeBox.setText("");
				
				}
				}
			});
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameBox.setText("");
				addressBox.setText("");
				incomeBox.setText("");
				visibility(false);
			}
		});
		this.add(searchButton);
		this.add(cancelButton);
	}
	

}
