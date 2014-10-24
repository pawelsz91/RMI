package ie.gmit;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;

import javax.crypto.*;

import java.security.Key;

public class EncryptionService implements Service {
	
	JTextField encryptionText;
	JLabel encText;
	JLabel decText;
	Key key;
	private Encryption encrypt = new Encryption();
	private static byte[] encryptedText;
	private static String decryptedText;
    private static final long serialVersionUID = 1L;
	
	public JPanel getGuiPanel() {
		//gui
		JPanel panel = new JPanel();
		JButton buttonEnc = new JButton("Encrypt");
		buttonEnc.addActionListener(new EncryptionListener());
		JButton buttonDec = new JButton("Decrypt");
		buttonDec.addActionListener(new DecryptionListener());
		encText = new JLabel("text after encryption");
		decText = new JLabel("text after decryption");
		encryptionText = new JTextField(10);
		JPanel inputPanel = new JPanel (new GridLayout(2 ,3));
		inputPanel.add(encryptionText);
		inputPanel.add(buttonEnc);
		inputPanel.add(encText);
		inputPanel.add(encText);
		inputPanel.add(buttonDec);
		inputPanel.add(decText);		
		panel.add(inputPanel);		
		return panel;
	}
	
	public class EncryptionListener implements ActionListener {	
		public void actionPerformed(ActionEvent av) {

			//using user text to encryption
			String plainText = encryptionText.getText();
			
			try {
				key = encrypt.makeKey();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				encryptedText = encrypt.encrypt(plainText, key);
				encText.setText(new String(encryptedText));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	public class DecryptionListener implements ActionListener {	
		public void actionPerformed(ActionEvent av) {

			//using encrypted text to decrypt it
			String plainText = encryptionText.getText();
					
			try {
				decryptedText = encrypt.decrypt(encryptedText, key);
				decText.setText(decryptedText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
}
