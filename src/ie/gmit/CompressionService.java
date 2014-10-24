package ie.gmit;

import javax.swing.*;

import java.awt.event .*;
import java.awt .*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CompressionService implements Service {

	JLabel outputLabel;
	JTextField fileToCompress;
	JTextField fileToDecompress;
	private Compression compress = new Compression();
	private static final long serialVersionUID = 1L;
	
	public JPanel getGuiPanel() {
		//just some GUI stuff
		JPanel panel = new JPanel();
		JButton buttonCompress = new JButton("Compress");
		buttonCompress.addActionListener(new CompressListener());
		JButton buttonDecompress = new JButton("Decompress");
		buttonDecompress.addActionListener(new DecompressListener());
		outputLabel = new JLabel("status label");
		fileToCompress = new JTextField(15);
		fileToDecompress = new JTextField(15);
		JPanel inputPanel = new JPanel (new GridLayout(2 ,3));
		inputPanel.add(new JLabel("File name to compress(with extension)"));
		inputPanel.add(fileToCompress);
		inputPanel.add(buttonCompress);
		inputPanel.add(new JLabel("File name to decompress(with extension)"));
		inputPanel.add(fileToDecompress);
		inputPanel.add(buttonDecompress);
		panel.add(inputPanel);
		panel.add(outputLabel);
		outputLabel.setText("Files for compression/decompression have to be"+"\n"+"placed on desktop!!!");
		return panel;		
	}
	
	public class CompressListener implements ActionListener{
		public void actionPerformed(ActionEvent av) {			
			//setting proper names for files
			String fileName = fileToCompress.getText();
			String filePath = "";
			filePath = compress.getCurrentUserDesktopPath();
			filePath = filePath + "\\" + fileName;
	        String gzipFile = compress.getCurrentUserDesktopPath() + "\\" + "GZIP_"+ fileName + ".gz";
	        
	        try {
				compress.compressGzipFile(filePath, gzipFile);
				outputLabel.setText("Compression successful. File name: " + "GZIP_"+ fileName + ".gz");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
			
		}
	}
	
	public class DecompressListener implements ActionListener{
		public void actionPerformed(ActionEvent av) {			
			//setting proper names for files
			String fileName = fileToCompress.getText();
			
			String gzipFile = compress.getCurrentUserDesktopPath() + "\\" + "GZIP_how_to.txt.gz"; //
	        String newFile = compress.getCurrentUserDesktopPath() + "\\" + "new_" + fileName;
	        
	        try {
				compress.decompressGzipFile(gzipFile, newFile);
				outputLabel.setText("Decompression successful. File name: " + "new_" + fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
