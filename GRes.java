import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class GRes {

	String column[]={"ID","NAME","EMAIL","NUMBER","COMPANY","POSITION","EXPERIENCE","CURRENT PAY", "EXPECTED PAY", "Manual/Automation","Tools Knowledge","Visa Status"};
	String data[][];
	
	String locations[];
	
	String[][] data_copy;
	
	private JFrame frmSqacanadaResume;
	private JTextField txtSearch;
	private JButton btnExport;
	private JButton btnImport;
	private JButton btnDocx;
	private JButton btnPrint;
	private JScrollPane scrollPane;
	private JTable table;

	DefaultTableModel mod;
	String docx_file = "files\\docx_reader\\akkshai resume.docx";
	String excel_file = "files\\excel_reader\\previous records.xlsx";
	String data_file = "files\\database\\data_file.txt";
	String pdf_file = "files\\pdf_reader\\";
	
	Formatter format = new Formatter(this);
	String[][] pacs;
	
	GRes myself;
	
	boolean docx = false;
	boolean excel = false;
	private JButton btnSave;
	private JPopupMenu popupMenu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GRes window = new GRes();
					window.frmSqacanadaResume.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GRes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		myself = this;
		frmSqacanadaResume = new JFrame();
		frmSqacanadaResume.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save before leaving?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					 saveData();
				}
			}
		});
		frmSqacanadaResume.setTitle("SQACanada | Resume Sorter");
		frmSqacanadaResume.setAlwaysOnTop(false);
		frmSqacanadaResume.getContentPane().setBackground(new Color(51,51,51));
		frmSqacanadaResume.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		frmSqacanadaResume.setBounds(100, 100, 1121, 619);
		frmSqacanadaResume.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSqacanadaResume.getContentPane().setLayout(new MigLayout("", "[grow][][][][][][][][][][][][][][][][][]", "[][][][grow][][][grow]"));
		
		JLabel lblTitle = new JLabel("SQACanada Resume Sorter");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
		frmSqacanadaResume.getContentPane().add(lblTitle, "cell 0 0,alignx left,aligny top");
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveData();
			}
		});
		btnSave.setToolTipText("Print records");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSave.setBackground(SystemColor.controlDkShadow);
		frmSqacanadaResume.getContentPane().add(btnSave, "cell 4 0,alignx right,aligny top");
		
		btnPrint = new JButton("Print");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setToolTipText("Print records");
		btnPrint.setBackground(new Color(105,105,105));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frmSqacanadaResume.getContentPane().add(btnPrint, "cell 7 0,alignx right,aligny top");
		
		btnDocx = new JButton("Add");
		btnDocx.setToolTipText("Add manually");
		btnDocx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				Information pa = new Information("","","","","","","","","","","","","");
				new confirmData(pa,format,myself);
			}
		});
		btnDocx.setBackground(new Color(105,105,105));
		btnDocx.setForeground(Color.WHITE);
		btnDocx.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frmSqacanadaResume.getContentPane().add(btnDocx, "cell 10 0,alignx right,aligny top");
		
		btnImport = new JButton("Import");
		btnImport.setToolTipText("Import data");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				new file_reader_gui(myself);
			}
		});
		btnImport.setBackground(new Color(105,105,105));
		btnImport.setForeground(Color.WHITE);
		btnImport.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frmSqacanadaResume.getContentPane().add(btnImport, "cell 13 0,alignx right,aligny top");
		
		btnExport = new JButton("Export");
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[][] toGive = new String[data.length][12];
				for (int i = 0; i < toGive.length; i++) {
					for (int p=0; p < data[0].length; p++) {
						toGive[i][p] = data[i][p];
					}
				}
				format.writeExcel(toGive);
			}
		});
		btnExport.setToolTipText("Export records");
		btnExport.setBackground(new Color(105,105,105));
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frmSqacanadaResume.getContentPane().add(btnExport, "cell 16 0 2 1,alignx right,aligny top");
		
		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, 
                            "The search function can be used by typing (without the brackets)\n\nid=(int)\nname=(String)\nemail=(String)\nnumber=(int)\ncompany=(String)\nposition=(String)\nexperience=(min):(max)\ncpay=(min):(max)\nepay=(min):(max)\nma='m'/'a'\ntk=(String)\nvisa=(String)\n\nNOTE: Put '/' in between the filters ex: 'id=3/name=john/experience=3:-1'\nPut '-1' if a min or max is not defined!", 
                            "Search Function", 
                            JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					updateData();
					String search_line = txtSearch.getText().toLowerCase().trim();

					if (search_line.equals("")) {
						mod = (DefaultTableModel) table.getModel();
						mod.setDataVector(data,column);
						return;
					}
					ArrayList<String> data_temp = new ArrayList<String>();		
					String[] searches = search_line.split("/");
					
					for (int i = 0; i < data.length; i++) {
						boolean okay = true;
						for (int j = 0; j < data[0].length;j++) {
							switch (j) {
							case 0:
								if (search_line.contains("id=")) { //checks if id matches
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("id=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].equals(temp_value[1]))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 1:
								if (search_line.contains("name=")) { //checks if name matches
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("name=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 2:
								if (search_line.contains("email=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("email=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 3:
								if (search_line.contains("number=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("number=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 4:
								if (search_line.contains("company=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("company=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 5:
								if (search_line.contains("position=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("position=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 6:
								if (search_line.contains("experience=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("experience=")) {
											String[] temp_value = searches[q].split("=",2);
											String[] min_max = temp_value[1].split(":");
											
											if (min_max[0].equals("-1")) {min_max[0] = Integer.toString(0);}
											if (min_max[1].equals("-1")) {min_max[1] = Integer.toString(Integer.MAX_VALUE);}

											int min = Integer.parseInt(min_max[0]);
											int max = Integer.parseInt(min_max[1]);
											int num = 0;

											try {
												num = Integer.parseInt(retNum(data[i][j]));
											} catch (NumberFormatException ex) {
												okay = false;
												break;
											}					
											if (!(min <= num && num <= max)) {
												okay = false;
											}
										}
									}
								}
								break;
							case 7:
								if (search_line.contains("cpay=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("cpay=")) {
											String[] temp_value = searches[q].split("=",2);
											String[] min_max = temp_value[1].split(":");
											
											if (min_max[0].equals("-1")) {min_max[0] = Integer.toString(0);}
											if (min_max[1].equals("-1")) {min_max[1] = Integer.toString(Integer.MAX_VALUE);}

											int min = Integer.parseInt(min_max[0]);
											int max = Integer.parseInt(min_max[1]);
											int num = 0;
											
											try {
												//System.out.println(retNum(data[i][j]));
												num = Integer.parseInt(retNum(data[i][j]));
											} catch (NumberFormatException ex) {
												okay = false;
												break;
											}					
											if (!(min <= num && num <= max)) {
												okay = false;
											}
										}
									}
								}
								break;
							case 8:
								if (search_line.contains("epay=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("epay=")) {
											String[] temp_value = searches[q].split("=",2);
											String[] min_max = temp_value[1].split(":");
											
											if (min_max[0].equals("-1")) {min_max[0] = Integer.toString(0);}
											if (min_max[1].equals("-1")) {min_max[1] = Integer.toString(Integer.MAX_VALUE);}

											int min = Integer.parseInt(min_max[0]);
											int max = Integer.parseInt(min_max[1]);
											int num = 0;
											
											try {
												//System.out.println(retNum(data[i][j]));
												num = Integer.parseInt(retNum(data[i][j]));
											} catch (NumberFormatException ex) {
												okay = false;
												break;
											}					
											if (!(min <= num && num <= max)) {
												okay = false;
											}
										}
									}
								}
								break;
							case 9:
								if (search_line.contains("ma=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("ma=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 10:
								if (search_line.contains("tk=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("tk=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							case 11:
								if (search_line.contains("visa=")) {
									for (int q = 0; q < searches.length; q++) {
										if (searches[q].contains("visa=")) {
											String[] temp_value = searches[q].split("=",2);
											if (!(data[i][j].toLowerCase().contains(temp_value[1].toLowerCase()))) {
												okay = false;
											} 
										}
									}
								}
								break;
							}
						}
						if (okay == true) {
							String add_temp = "";
							for (int p = 0; p < data[0].length; p++) {
								add_temp += data[i][p] + ":";
							}
							add_temp = add_temp.substring(0, add_temp.length()-1); //removes the last ':'
							data_temp.add(add_temp);
						}
					}
					data = new String[data_temp.size()][12];
					
					for (int i =0; i < data_temp.size(); i++) {
						String line = data_temp.get(i);					
						String[] temp_ = line.split(":");
						
						for (int h = 0; h < temp_.length; h++) {
							data[i][h] = temp_[h];
						}
					}
					
					mod = (DefaultTableModel) table.getModel();
					mod.setDataVector(data,column);	
				}
			}
		});
		txtSearch.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search (Double Click On Me For Help)")) {
					txtSearch.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search (Double Click On Me For Help)");
				}
			}
		});
		txtSearch.setText("Search (Double Click On Me For Help)");
		txtSearch.setToolTipText("Search");
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setBackground(new Color(105,105,105));
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		frmSqacanadaResume.getContentPane().add(txtSearch, "cell 0 2 18 1,growx,aligny center");
		txtSearch.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setBackground(new Color(105,105,105));
		frmSqacanadaResume.getContentPane().add(scrollPane, "cell 0 3 18 4,grow");
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
					e.consume();
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
		            
		            int val_id = 0;
		            try {
		            	val_id = Integer.parseInt((String)table.getValueAt(row, 0));
		            } catch (Exception e2) {
		            	System.out.println("Nothing selected");
		            	return;
		            }
		            
		            String location = locations[val_id-1]; //using locations instead so it'll work even if there is a filter
		            File file = new File(location);
		            
		            if (file.exists()) {
		            	try {
							Desktop.getDesktop().open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
							System.out.println("Couldn't open the file!");
						}
		            }
			    }

			}
		});
		table.setFillsViewportHeight(true);
		table.setRowHeight(20);
		table.setForeground(Color.WHITE);
		table.setGridColor(Color.WHITE);
		
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		table.getTableHeader().setBackground(new Color(51,51,51));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table.setBackground(new Color(105,105,105));
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		
		JMenuItem openRecord = new JMenuItem("Open Record");
		openRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();        
	            int val_id = 0;
	            try {
	            	val_id = Integer.parseInt((String)table.getValueAt(row, 0));
	            } catch (Exception e2) {
	            	System.out.println("Nothing selected");
	            	return;
	            }
	            String location = locations[val_id-1]; //using locations instead so it'll work even if there is a filter
	            File file = new File(location);
	            if (file.exists()) {
	            	try {
						Desktop.getDesktop().open(file);
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Couldn't open the file!");
					}
	            }
			}
		});
		
		JMenuItem deleteRecord = new JMenuItem("Delete Record");
		deleteRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();        
	            int val_id = 0;
	            try {
	            	val_id = Integer.parseInt((String)table.getValueAt(row, 0));
	            } catch (Exception e2) {
	            	System.out.println("Nothing selected");
	            	return;
	            }
	            String location = locations[val_id-1]; //using locations instead so it'll work even if there is a filter
	            if (location.endsWith(".docx") || location.endsWith(".pdf")) {
	            	File file = new File(location);
		            if (file.exists()) {
						file.delete();
		            } else {
		            	System.out.println("File not found!");
		            }
	            }
	            mod.removeRow(row);
			}
		});
		
		popupMenu.add(openRecord);
		popupMenu.add(deleteRecord);
		popupMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JMenuItem menu = (JMenuItem) e.getSource();
		        if (menu == openRecord) {

		        } else if (menu == deleteRecord) {
		            
		        }
			}
		});

		addPopup(table, popupMenu);
		loadT();
	}

	
	public void loadT() {	
		try {
			int potential_add = 0;
			int size_add = 0; //checks how many non repeating data rows there are
			int count = 0;
			int count_docx = 0;
			
			if (docx == true) {
				if (!(format.packets == null)) {
					
					pacs = new String[format.packets.size()][13];
					
					for (int i = 0; i < pacs.length; i++) {
						for (int p = 1; p < pacs[0].length;p++) {
							switch (p) {
							case 0:
								pacs[i][p] = Integer.toString(count_docx+1);
								break;
							case 1:
								pacs[i][p] = format.packets.get(i).name;
								break;
							case 2:
								pacs[i][p] = format.packets.get(i).email;
								break;
							case 3:
								pacs[i][p] = format.packets.get(i).number;
								break;
							case 4:
								pacs[i][p] = format.packets.get(i).company;
								break;
							case 5:
								pacs[i][p] = format.packets.get(i).workPosition;
								break;
							case 6:
								pacs[i][p] = format.packets.get(i).experience;
								break;
							case 7:
								pacs[i][p] = format.packets.get(i).current_pay;
								break;
							case 8:
								pacs[i][p] = format.packets.get(i).expected_pay;
								break;
							case 9:
								pacs[i][p] = format.packets.get(i).manual_automation;
								break;
							case 10:
								pacs[i][p] = format.packets.get(i).tools;
								break;
							case 11:
								pacs[i][p] = format.packets.get(i).visa;
								break;
							case 12:
								pacs[i][p] = format.packets.get(i).location;
								break;
							}
						}
						potential_add++;
						//count++;
						count_docx++;
					}
					
					/*pacs[1] = format.packets.get(0).name;
					pacs[2] = format.packets.get(0).number;
					pacs[3] = format.packets.get(0).email;
					pacs[4] = format.packets.get(0).experience;
					pacs[5] = format.packets.get(0).workPosition;
					pacs[6] = format.packets.get(0).work;
					pacs[7] = format.packets.get(0).wage;
					potential_add++;*/
				}
			}

			File text_data = new File(data_file);
			Scanner scan = new Scanner(text_data);
			List<String> data_initial = new ArrayList<String>();
			
			while (scan.hasNextLine()) {
				data_initial.add(scan.nextLine());
			}
			scan.close();
			
			if (excel == true) { //checks how much space is required for data array
				if (!(format.stored_rows == null)) {
					for (String s: format.stored_rows) {
						boolean checkExist = false;
						for (String c: data_initial) {
							int x = 0;
							for (int i = 0; i < c.length(); i++) {
								if (c.charAt(i) == "!".charAt(0)) {
									x = i;
								}
							}
							String subby = c.substring(0, x);
							if (subby.equals(s)) {
								checkExist = true;
							}
						}
						if (checkExist == false) {
							size_add++;
						}
					}
				}
			}
			data = new String[data_initial.size() + size_add + potential_add][12];
			
			locations = new String[data.length];
			
			for (String a: data_initial) { //adds all data in .txt	
				a = Integer.toString(count+1) + "!" + a;
				
				String[] temp = a.split("!");
				for(int i = 0; i < data[0].length;i++) {
					
					data[count][i] = temp[i];
				}
				locations[count] = temp[temp.length-1];
				count++;
			}	
			
			if (excel == true) {
				if (!(format.stored_rows == null)) {
					for (String s: format.stored_rows) { //adds additional data from excel file
						
						//get the line up till the location
						boolean checkExist = false;
						for (String c: data_initial) { //this for loop is to get a specific substring of the string we're checking.
							int x = 0;
							for (int i = 0; i < c.length(); i++) {
								if (c.charAt(i) == "!".charAt(0)) {
									x = i;
								}
							}
							String subby = c.substring(0, x);
							if (subby.equals(s)) {
								checkExist = true;
							}
						}
						
						if (checkExist == false) {
							s = Integer.toString(count+1) + "!" + s;
							String[] temp = s.split("!");		
							for(int i = 0; i < data[0].length;i++) {
								data[count][i] = temp[i];
							}
							locations[count] = excel_file;  //temp[temp.length-1];  THIS IS FOR WHEN YOU FIX THE STORED ROWS (ADD LOCATION TO THE END)
							count++;
						}
					}
				}
			}
			
			if (docx == true) {
				//pacs[0] = Integer.toString(count+1);
				
				for (int i = 0; i < pacs.length; i++) {
					pacs[i][0] = Integer.toString(count+1);
					for (int p =0; p < pacs[0].length - 1;p++) {
						
						data[count][p] = pacs[i][p];
						
					}
					locations[count] = pacs[i][pacs[0].length-1];
					
					//System.out.println(locations[count]);
					
					count++;
				}
				
				/*for (int i = 0; i < pacs.length;i++) { //adds data found in .docx (resume)
					data[count][i] = pacs[i]; //make new array with each element being name/email etc
				}*/
			}
			data_copy = new String[data.length][data[0].length];
			updateCopy(); //stores a copy of data into data_copy (used for search function)
			
		}catch (FileNotFoundException e) {
			System.out.println("File cannot be found!");
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("\n\n\n\n\n" + e.toString());
		}
		mod = (DefaultTableModel) table.getModel();
		mod.setDataVector(data,column);
	}
	
	public String retNum(String input) {
		if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
			return input.substring(0);
		} else if (input.length()==1 && !Character.isDigit(input.charAt(0))) {
			return "0";
		}
		
		String number = "";
		int start = -1;
		int end = -1;
		boolean isNum = false;
		String[] skips = {","};
		
		for (int i = 0; i < input.length(); i++) { 
			if (Character.isDigit(input.charAt(i)) || Arrays.stream(skips).anyMatch((input.charAt(i) + "")::equals)) {
				isNum = true;
				if (start == -1) start = i;
			} else {
				if (end == -1 && start != -1) end = i;
			}
		}
		if (isNum == true) {
			number = input.substring(start, end);
			number = number.replaceAll(",", "");
		}
		return number;
	}
	
	public void updateCopy() {
		for (int i = 0; i < data.length;i++) { //stores a copy of data into data_copy (used for search function)
			for (int j = 0; j < data[0].length;j++) {
				data_copy[i][j] = data[i][j];
			}
		}
	}
	
	public void updateData() {
		data = new String[data_copy.length][data_copy[0].length];
		for (int i = 0; i < data.length;i++) { //stores a copy of data into data_copy (used for search function)
			for (int j = 0; j < data_copy[0].length;j++) {
				data[i][j] = data_copy[i][j];
			}
		}
	}
	
	public boolean findFiles(boolean docx_, boolean excel_, boolean pdf_) {
		File docxRead = new File("files\\docx_reader\\");
		File[] listOfDocx = docxRead.listFiles();
		
		File excelRead = new File("files\\excel_reader\\");
		File[] listOfExcel = excelRead.listFiles();
		
		File pdfRead = new File("files\\pdf_reader\\");
		File[] listOfPdf = pdfRead.listFiles();
		
		try {
			if (docx_ == true) {
				for (File file : listOfDocx) {
				    if (file.isFile()) {		      
				        docx_file = "files\\docx_reader\\" + file.getName();
				        if (!format.searchFile(docx_file)) {
							return false;
						}
						if (!format.readFile()) {
							return false;
						}
						if (!format.formatFile()) {
							return false;
						}
						format.dissectFile();
				    }
				}
			}
			if (excel_ == true) { //every excel file wont be able to be read bc the data array is recreated everytime loadT() is called (gotta fix that)
				for (File file : listOfExcel) {
					if (!file.isFile()) {
						return false;
					}
					excel_file = "files\\excel_reader\\" + file.getName();
					if (!format.readExcel(excel_file)) {
						return false;
					}
					excel=true;
					loadT();
				}
			}	
			if (pdf_ == true) {
				for (File file : listOfPdf) {
					if (!file.isFile()) {
						return false;
					}
					pdf_file = "files\\pdf_reader\\" + file.getName();
					if (!format.readPdf(pdf_file, file.getName())) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean saveData() {
		String[] data_lines = new String[data.length];
		
		for (int i = 0; i < data_lines.length;i++) {
			for (int p = 1; p < data[0].length; p++) {
				System.out.println("i:p= " + i + ":" + p);
				try {
					data_lines[i] += mod.getValueAt(i, p) + "!";
				} catch (ArrayIndexOutOfBoundsException ex) {
					//ex.printStackTrace();
				}
				
			}
			if (!(data_lines[i] == null)) {
				data_lines[i] = data_lines[i].substring(4, data_lines[i].length()-1);
			}
		}
		
		try {
			PrintWriter writer = new PrintWriter(data_file); //these 3 lines are to remove all the txt from the file
			writer.print("");
			writer.close();

			Writer wr = new FileWriter(data_file);
			for(int i = 0; i < data_lines.length;i++) {
				if (data_lines[i] == null) {
					continue;
				}
				wr.write(data_lines[i] + "\n");
			}
			wr.flush();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
