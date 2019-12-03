import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Label;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

public class Interface {

	private JFrame frmDigforans;
	private Analyzer analyzer;
	ArrayList<String> population = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	private FileWriter fw;
	private long startTime;
	private long endTime;
	File file;
	private Date date = new Date();
	private JTextField minTimeQuest1;
	private JTextField maxTimeQuest1;
	public JTextArea communicationPanel;
	private JTextField minTimeQuest2;
	private JTextField maxTimeQuest2;
	private String outFileString = "";
	private String popFileString = "";
	private String roomFileString = "";
	private JTextField minTimeQuest5;
	private JTextField maxTimeQuest5;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmDigforans.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDigforans = new JFrame();
		frmDigforans.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmDigforans.setForeground(Color.WHITE);
		frmDigforans.setBackground(Color.WHITE);
		frmDigforans.setTitle("DigForAns");
		
		frmDigforans.setBounds(100, 100, 1293, 1100);
		frmDigforans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		analyzer = new Analyzer();
       
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.LIGHT_GRAY);
		layeredPane.setForeground(Color.BLACK);
		frmDigforans.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		
		File outputFolder = new File("./OutputFiles");
		File populationFolder = new File("./PopulationFiles");
		File roomsFolder = new File("./RoomsFiles");
		File[] listOfOutput = outputFolder.listFiles();
		File[] listOfPopulation = populationFolder.listFiles();
		File[] listOfRooms = roomsFolder.listFiles();
		
		JLabel lblChosingFileTo = new JLabel("Chosing Output and Population files");
		lblChosingFileTo.setForeground(Color.BLACK);
		lblChosingFileTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChosingFileTo.setBackground(new Color(240, 240, 240));
		lblChosingFileTo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChosingFileTo.setBounds(10, 23, 211, 22);
		layeredPane.add(lblChosingFileTo);
		
		JLabel lblNewLabel = new JLabel("Is it possible that two entities have met in a certain interval of time ?");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 105, 395, 22);
		layeredPane.add(lblNewLabel);
		
		JLabel lblChooseEntities = new JLabel("Choose Entities");
		lblChooseEntities.setForeground(Color.BLACK);
		lblChooseEntities.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChooseEntities.setBounds(495, 85, 95, 14);
		layeredPane.add(lblChooseEntities);
		
		JLabel lblSelectTheTime = new JLabel("Select the time interval");
		lblSelectTheTime.setForeground(Color.BLACK);
		lblSelectTheTime.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectTheTime.setBounds(733, 85, 128, 14);
		layeredPane.add(lblSelectTheTime);
		
		JLabel lblWhereWasA = new JLabel("Where was a certain entity in a certain interval of time?");
		lblWhereWasA.setForeground(Color.BLACK);
		lblWhereWasA.setFont(new Font("Arial", Font.PLAIN, 12));
		lblWhereWasA.setBounds(10, 155, 301, 22);
		layeredPane.add(lblWhereWasA);
		
		JLabel label = new JLabel("Select the time interval");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(530, 135, 128, 14);
		layeredPane.add(label);
		
		JLabel Question3Label = new JLabel("Where has been a certain agent during the simulatiuon?");
		Question3Label.setForeground(Color.BLACK);
		Question3Label.setFont(new Font("Arial", Font.PLAIN, 12));
		Question3Label.setBounds(12, 255, 299, 17);
		layeredPane.add(Question3Label);
		
		JLabel Question4Label = new JLabel("Who has met a certain entity? Where and when?");
		Question4Label.setForeground(Color.BLACK);
		Question4Label.setFont(new Font("Arial", Font.PLAIN, 12));
		Question4Label.setBounds(10, 305, 281, 22);
		layeredPane.add(Question4Label);
		
		JLabel label_1 = new JLabel("Select the time interval");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(530, 185, 128, 14);
		layeredPane.add(label_1);
		
		JLabel labelQuest5 = new JLabel("Who has been in this room in a certain interval of time?");
		labelQuest5.setFont(new Font("Arial", Font.PLAIN, 12));
		labelQuest5.setForeground(Color.BLACK);
		labelQuest5.setBounds(10, 205, 301, 14);
		layeredPane.add(labelQuest5);
		
		JLabel labelQuest6 = new JLabel("Who has been in this room during the simulation?");
		labelQuest6.setForeground(Color.BLACK);
		labelQuest6.setFont(new Font("Arial", Font.PLAIN, 12));
		labelQuest6.setBounds(10, 355, 281, 14);
		layeredPane.add(labelQuest6);
		
		
		JComboBox<String> outFile = new JComboBox<String>();
		outFile.setBackground(Color.WHITE);
		outFile.setFont(new Font("Arial", Font.PLAIN, 12));
		outFile.setBounds(227, 23, 128, 22);
		layeredPane.add(outFile);
		
		JComboBox<String> popFile = new JComboBox<String>();
		popFile.setFont(new Font("Arial", Font.PLAIN, 12));
		popFile.setBackground(Color.WHITE);
		popFile.setBounds(376, 23, 128, 22);
		layeredPane.add(popFile);
		
		JComboBox<String> roomFiles = new JComboBox<String>();
		roomFiles.setForeground(Color.BLACK);
		roomFiles.setFont(new Font("Arial", Font.PLAIN, 12));
		roomFiles.setBackground(Color.WHITE);
		roomFiles.setBounds(530, 23, 128, 22);
		layeredPane.add(roomFiles);
		
		for (int i = 0; i < listOfOutput.length; i++) {
			  if (listOfOutput[i].isFile()) 
			    outFile.addItem(listOfOutput[i].getName());
			}
		for(int i = 0; i< listOfPopulation.length; i++) {
			if(listOfPopulation[i].isFile())
			  	popFile.addItem(listOfPopulation[i].getName());
		}
		for(int i = 0; i < listOfRooms.length; i++) {
			if(listOfRooms[i].isFile())
			    roomFiles.addItem(listOfRooms[i].getName());
		}
		
		JComboBox<String> peopleList1Quest1 = new JComboBox<String>();
		peopleList1Quest1.setMaximumRowCount(100);
		peopleList1Quest1.setFont(new Font("Arial", Font.PLAIN, 12));
		peopleList1Quest1.setBackground(Color.WHITE);
		peopleList1Quest1.setBounds(416, 105, 111, 22);
		layeredPane.add(peopleList1Quest1);
		
		JComboBox<String> peopleList2Quest1 = new JComboBox<String>();
		peopleList2Quest1.setMaximumRowCount(100);
		peopleList2Quest1.setFont(new Font("Arial", Font.PLAIN, 12));
		peopleList2Quest1.setBackground(Color.WHITE);
		peopleList2Quest1.setBounds(549, 105, 111, 22);
		layeredPane.add(peopleList2Quest1);
		
		JComboBox<String> peopleListQuest2 = new JComboBox<String>();
		peopleListQuest2.setMaximumRowCount(100);
		peopleListQuest2.setForeground(Color.BLACK);
		peopleListQuest2.setBackground(Color.WHITE);
		peopleListQuest2.setFont(new Font("Arial", Font.PLAIN, 12));
		peopleListQuest2.setBounds(346, 155, 111, 22);
		layeredPane.add(peopleListQuest2);
		
		JComboBox<String> peopleListQuest3 = new JComboBox<String>();
		peopleListQuest3.setFont(new Font("Arial", Font.PLAIN, 12));
		peopleListQuest3.setForeground(Color.BLACK);
		peopleListQuest3.setBackground(Color.WHITE);
		peopleListQuest3.setBounds(346, 255, 111, 22);
		layeredPane.add(peopleListQuest3);
		
		JComboBox<String> peopleListQuest4 = new JComboBox<String>();
		peopleListQuest4.setForeground(Color.BLACK);
		peopleListQuest4.setFont(new Font("Arial", Font.PLAIN, 12));
		peopleListQuest4.setBackground(Color.WHITE);
		peopleListQuest4.setBounds(346, 305, 111, 22);
		layeredPane.add(peopleListQuest4);
		
		JComboBox<String> roomListQuest5 = new JComboBox<String>();
		roomListQuest5.setForeground(Color.BLACK);
		roomListQuest5.setBackground(Color.WHITE);
		roomListQuest5.setFont(new Font("Arial", Font.PLAIN, 12));
		roomListQuest5.setBounds(346, 205, 111, 22);
		layeredPane.add(roomListQuest5);
		
		JComboBox<String> roomListQuest6 = new JComboBox<String>();
		roomListQuest6.setFont(new Font("Arial", Font.PLAIN, 12));
		roomListQuest6.setForeground(Color.BLACK);
		roomListQuest6.setBackground(Color.WHITE);
		roomListQuest6.setBounds(346, 355, 111, 22);
		layeredPane.add(roomListQuest6);
		
		
		minTimeQuest1 = new JTextField();
		minTimeQuest1.setFont(new Font("Arial", Font.PLAIN, 12));
		minTimeQuest1.setBounds(704, 105, 86, 22);
		layeredPane.add(minTimeQuest1);
		minTimeQuest1.setColumns(10);
		
		maxTimeQuest1 = new JTextField();
		maxTimeQuest1.setFont(new Font("Arial", Font.PLAIN, 12));
		maxTimeQuest1.setColumns(10);
		maxTimeQuest1.setBounds(800, 105, 86, 22);
		layeredPane.add(maxTimeQuest1);
		
		minTimeQuest2 = new JTextField();
		minTimeQuest2.setFont(new Font("Arial", Font.PLAIN, 12));
		minTimeQuest2.setColumns(10);
		minTimeQuest2.setBounds(495, 155, 86, 22);
		layeredPane.add(minTimeQuest2);
		
		maxTimeQuest2 = new JTextField();
		maxTimeQuest2.setFont(new Font("Arial", Font.PLAIN, 12));
		maxTimeQuest2.setColumns(10);
		maxTimeQuest2.setBounds(591, 155, 86, 22);
		layeredPane.add(maxTimeQuest2);
		
		minTimeQuest5 = new JTextField();
		minTimeQuest5.setFont(new Font("Arial", Font.PLAIN, 11));
		minTimeQuest5.setBounds(495, 205, 86, 22);
		layeredPane.add(minTimeQuest5);
		minTimeQuest5.setColumns(10);
		
		maxTimeQuest5 = new JTextField();
		maxTimeQuest5.setBackground(Color.WHITE);
		maxTimeQuest5.setFont(new Font("Arial", Font.PLAIN, 12));
		maxTimeQuest5.setText("");
		maxTimeQuest5.setBounds(591, 205, 86, 22);
		layeredPane.add(maxTimeQuest5);
		maxTimeQuest5.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(576, 275, 675, 331);
		layeredPane.add(scrollPane);
		
		communicationPanel = new JTextArea();// This panel will show all the output on the screen
		scrollPane.setViewportView(communicationPanel);
		communicationPanel.setFont(new Font("Arial", Font.PLAIN, 12));
		communicationPanel.setBackground(Color.WHITE);
		communicationPanel.setLineWrap(true);
		
		JButton btnChoose = new JButton("Choose");//This Button allow the user to choose the three selected output and population files
		btnChoose.setForeground(Color.BLACK);
		btnChoose.setFont(new Font("Arial", Font.PLAIN, 12));
		btnChoose.setBackground(Color.WHITE);
		btnChoose.addActionListener(new ActionListener() {//Here is the action done on the button click
			public void actionPerformed(ActionEvent e1) {
//In this part all the ComboBox are filled with the right data and the output file of this application is created. There will be printed all the text that shown in the communication panel.
					communicationPanel.setText("");
					popFileString = popFile.getSelectedItem().toString();
					outFileString = outFile.getSelectedItem().toString();
					roomFileString = roomFiles.getSelectedItem().toString();
					try {
						population = analyzer.GetFileContent("./PopulationFiles/" + popFileString);
						rooms = analyzer.GetFileContent("./RoomsFiles/" + roomFileString);
					} catch (IOException e) {
						System.out.println(popFileString + " does not exist anymore");
						e.printStackTrace();
					}
					peopleList1Quest1.removeAllItems();
					peopleList2Quest1.removeAllItems();
					peopleListQuest2.removeAllItems();
					peopleListQuest3.removeAllItems();
					peopleListQuest4.removeAllItems();
					roomListQuest5.removeAllItems();
					roomListQuest6.removeAllItems();
					for(int i = 0; i < population.size(); i++) {
						peopleList1Quest1.addItem(population.get(i).toString());
						peopleList2Quest1.addItem(population.get(i).toString());
						peopleListQuest2.addItem(population.get(i).toString());
						peopleListQuest3.addItem(population.get(i).toString());
						peopleListQuest4.addItem(population.get(i).toString());
					}
					for(int i = 0; i< rooms.size(); i++) {
						roomListQuest5.addItem(rooms.get(i).toString());
						roomListQuest6.addItem(rooms.get(i).toString());
					}
					try {
						analyzer.CreateFile(outFileString, popFileString, roomFileString);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "One of the files you are looking for, does not exist", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						JOptionPane.showMessageDialog(null, "The encoding of the file is not supported", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						e.printStackTrace();
					}
			}
		});
		
		btnChoose.setBounds(677, 23, 89, 23);
		layeredPane.add(btnChoose);
		
		//BUTTON FOR FIRST QUESTION
		JButton buttonQuestion1 = new JButton("Ask");
		buttonQuestion1.setForeground(Color.BLACK);
		buttonQuestion1.setBackground(Color.WHITE);
		buttonQuestion1.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTime =  System.currentTimeMillis();
				if(!minTimeQuest1.getText().isEmpty() && !maxTimeQuest1.getText().isEmpty()) {
					try {
						communicationPanel.setText("");
						if(analyzer.Meeting(outFileString, peopleList1Quest1.getSelectedItem().toString(), peopleList2Quest1.getSelectedItem().toString(), Float.parseFloat(minTimeQuest1.getText()), Float.parseFloat(maxTimeQuest1.getText()))) {
							communicationPanel.append("\nWhere "+ peopleList1Quest1.getSelectedItem().toString() + " and " + peopleList2Quest1.getSelectedItem().toString() + " have met in the interval "+ minTimeQuest1.getText() + "/" + maxTimeQuest1.getText() +"? \r\n"  );
							communicationPanel.append(analyzer.answer);
						}
						else
							JOptionPane.showMessageDialog(null, analyzer.answer, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, outFileString + " does not exist anymore", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Insert numbers, not symbols or letters", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				else
					JOptionPane.showMessageDialog(null, "Inserisci dute estremi temporali", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
				WriteOnFile(analyzer.currentFile);
				endTime = System.currentTimeMillis();
				System.out.println(endTime - startTime);
			}
		});
		buttonQuestion1.setBounds(908, 104, 62, 23);
		layeredPane.add(buttonQuestion1);
		
		//BUTTON FOR SECOND QUESTION			
		JButton buttonQuestion2 = new JButton("Ask");//creation of the button for asking the question to DigForReason
		buttonQuestion2.setForeground(Color.BLACK);
		buttonQuestion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				communicationPanel.setText("");
				if(!minTimeQuest2.getText().isEmpty() && !maxTimeQuest2.getText().isEmpty()) {
					try {
						//if the function return true it will print the answer
						if(analyzer.movementsInInterval(outFileString, peopleListQuest2.getSelectedItem().toString(), Float.parseFloat(minTimeQuest2.getText().replace(",", ".")), Float.parseFloat(maxTimeQuest2.getText().replace(",", ".")))) {
							communicationPanel.append("\nWhere has been " + peopleListQuest2.getSelectedItem().toString() + " between " + minTimeQuest2.getText() + " and " + maxTimeQuest2.getText() + " ?\n");
							communicationPanel.append(analyzer.answer);
							} 
						else {//if the function does not  find correspondences
							// Pop up a warning message with the error inserted in the answer as text
							JOptionPane.showMessageDialog(null, analyzer.answer, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Insert numbers, not words", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "An error have been encountered", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
					WriteOnFile(analyzer.currentFile);
				}
				else
					JOptionPane.showMessageDialog(null, "Inserisci due estremi temporali", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);		
			}
		});
		buttonQuestion2.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion2.setBackground(Color.WHITE);
		buttonQuestion2.setBounds(704, 155, 62, 23);
		layeredPane.add(buttonQuestion2);
		
		//BUTTON FOR THIRD QUESTION
		JButton buttonQuestion3 = new JButton("Ask");
		buttonQuestion3.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion3.setForeground(Color.BLACK);
		buttonQuestion3.setBackground(Color.WHITE);
		buttonQuestion3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTime =  System.currentTimeMillis();
				communicationPanel.setText("");
				try {
					if(analyzer.GeneralCall(outFileString, peopleListQuest3.getSelectedItem().toString(), "movements")) {
						communicationPanel.append("\nWhere has been " + peopleListQuest3.getSelectedItem().toString() + " ?\n");
						communicationPanel.append(analyzer.answer);
					}
					else
						JOptionPane.showMessageDialog(null, analyzer.answer, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error have been encountered", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
				
				WriteOnFile(analyzer.currentFile);

				endTime = System.currentTimeMillis();
				System.out.println(endTime - startTime);
			}
		});
		buttonQuestion3.setBounds(495, 255, 62, 23);
		layeredPane.add(buttonQuestion3);
		
		//BUTTON FOR FOURTH QUESTION
		JButton buttonQuestion4 = new JButton("Ask");
		buttonQuestion4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTime =  System.currentTimeMillis();
				communicationPanel.setText("");
				communicationPanel.append("\nWho have been met by " + peopleListQuest4.getSelectedItem().toString() + "\n");
				try {
					communicationPanel.append(analyzer.AllMeetings(outFileString, peopleListQuest4.getSelectedItem().toString(), population));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Some data types are wrong", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error have been encountered", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
				
				WriteOnFile(analyzer.currentFile);
				
				endTime = System.currentTimeMillis();
				System.out.println(endTime - startTime);
			}
		});
		buttonQuestion4.setForeground(Color.BLACK);
		buttonQuestion4.setBackground(Color.WHITE);
		buttonQuestion4.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion4.setBounds(495, 305, 62, 23);
		layeredPane.add(buttonQuestion4);
		
		//BUTTON FOR FIFTH QUESTION
		JButton buttonQuestion5 = new JButton("Ask");
		buttonQuestion5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTime =  System.currentTimeMillis();
				communicationPanel.setText("");
				try {
					if(analyzer.PresenceInRoomInterval(outFileString, roomListQuest5.getSelectedItem().toString(), Float.parseFloat(minTimeQuest5.getText()), Float.parseFloat(maxTimeQuest5.getText()))) {
						communicationPanel.append("\nWho has been in " + roomListQuest5.getSelectedItem().toString() + " between " + minTimeQuest5.getText() + " and " + maxTimeQuest5.getText() + "\n");
						communicationPanel.append(analyzer.answer);
					}
					else
						JOptionPane.showMessageDialog(null, analyzer.answer, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Some data types are wrong", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error have been encountered", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
				WriteOnFile(analyzer.currentFile);
				endTime = System.currentTimeMillis();
				System.out.println(endTime - startTime);
			}
		});
		buttonQuestion5.setForeground(Color.BLACK);
		buttonQuestion5.setBackground(Color.WHITE);
		buttonQuestion5.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion5.setBounds(704, 205, 62, 23);
		layeredPane.add(buttonQuestion5);
		
		 // BUTTON FOR SIXTH QUESTION
		JButton buttonQuestion6 = new JButton("Ask");
		buttonQuestion6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTime =  System.currentTimeMillis();
				communicationPanel.setText("");
				try {
					if(analyzer.GeneralCall(outFileString, roomListQuest6.getSelectedItem().toString(), "presence")) {
						communicationPanel.append("\nWho has been in " + roomListQuest6.getSelectedItem().toString() + "\n");
						communicationPanel.append(analyzer.answer);
					}
					else
						JOptionPane.showMessageDialog(null, analyzer.answer, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Some data types are wrong", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error have been encountered", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
				WriteOnFile(analyzer.currentFile);
				endTime = System.currentTimeMillis();
				System.out.println(endTime - startTime);
			}
		});
		buttonQuestion6.setForeground(Color.BLACK);
		buttonQuestion6.setBackground(Color.WHITE);
		buttonQuestion6.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonQuestion6.setBounds(495, 352, 62, 23);
		layeredPane.add(buttonQuestion6);
		
		
	}
	
	public void WriteOnFile(File file) {
		try {
			fw = new FileWriter(file, true);
			fw.write("\n" + communicationPanel.getText());
			fw.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Encountered problems in getting the file", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
	}
}
