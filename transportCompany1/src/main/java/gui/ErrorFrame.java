package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ErrorFrame extends JFrame {
	
	JLabel errorLabel = new JLabel();
	JButton errorFrameOKButton = new JButton("OK");
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	public void visibility(boolean state) {
		this.setVisible(state);
	}
	
	ErrorFrame(String errorMessage){
		this.setTitle("ERROR");
		this.setLayout(new GridLayout(0,1));
		this.setSize(250,125);
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setText(errorMessage);
		panel1.add(errorLabel);
		
		errorFrameOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(false);
				errorLabel.setText("");
			}
		});
		panel2.add(errorFrameOKButton);
		this.add(panel1);
		this.add(panel2);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
