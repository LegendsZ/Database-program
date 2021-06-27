import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Formatter {

	GRes reference;
	
	FileInputStream fis;
	XWPFDocument docx;
	
	List<XWPFParagraph> paragraphList;
	XWPFParagraph[] arrayParas;
	
	List<XWPFTable> tables;
	
	String[] lines;
	
	List<Information> packets; //made this an array incase theres multiple .docx to read
	
	int global_id = 1;
	
	List<String> stored_rows;
	
	String stored_search;
	
	public Formatter(GRes ref) {
		reference=ref;
		packets = new ArrayList<Information>();
	}
	
	public boolean searchFile(String search) {
		System.out.print("\nSearching for File(s)..."); //Looks for file
		File tmpDir = new File(search);
		if (tmpDir.exists() == true) {
			stored_search = search;
			System.out.println("OK!");
		} else {
			System.out.println("FAILED!");
			return false;
		}
		
		try {
			fis =  new FileInputStream(search);
			docx = new XWPFDocument(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean readFile() {
		System.out.print("Reading...");  //Reads file
		paragraphList = docx.getParagraphs();
		tables = docx.getTables();

		arrayParas = new XWPFParagraph[paragraphList.size()];
		paragraphList.toArray(arrayParas);
		lines = new String[paragraphList.size()];
		for (int i = 0; i < arrayParas.length;i++) {
			lines[i] = arrayParas[i].getText().trim();
		}
		
		System.out.println("OK!");
		return true;
	}
	
	public boolean formatFile() {
		System.out.print("Formatting..."); //Formatting file
		
		int counter = 0;
		int maxcount = 175;
		
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].equals("")) {
				String temp = lines[i];
				for (int j = i; j < lines.length - 1; j++) {
					lines[j] = lines[j+1];
				}
				lines[lines.length-1] = temp;
				if (counter < maxcount) {
					counter++;
					i--;
				} 
			}
		}
		System.out.println("OK!\n");
		return true;
	}
	
	public void dissectFile() {
		String id = "";
		String name = "";
		String email = "";
		String number = "";
		String company = "";
		String workPosition = "";
		String experience = "";
		String current_pay = "";
		String expected_pay = "";
		String manual_automation = "";
		String tools = "";
		String visa = "";
		
		String path = stored_search;
		
		int line_counter = 0;
		String line = "";
		String[] numbers = {"1", "2","3","4","5","6","7","8","9","0"};
		String[] number_exception = {"-", ".",","};
		
		//start of getting information
		id = Integer.toString(global_id);
		global_id = Integer.parseInt(id) + 1;
		System.out.println("ID: " + id);

		//Start of Name Algorithm
		String[] no_name = {"@", "|", "/"};
		do {
			if (Arrays.stream(no_name).anyMatch(lines[line_counter]::contains) == true || lines[line_counter].length() < 2) {
				line_counter++;
			} else {
				name = lines[line_counter].trim();
			}
		}while (name.equals(""));
		//End of Name Algorithm
		System.out.println("Name: " + name);
		
		
		
		line_counter = 0;
		
		
		
		//Start of number algorithm
		do {
			if (line_counter >= lines.length) {
				number = "NOT FOUND";
				break;
			}
			int seq_start = -1;
			int seq_end = -1;
			boolean brake = false;
			
			 do {
				line = lines[line_counter] + " ";
				line_counter++;
			} while ((!Arrays.stream(numbers).anyMatch(line::contains) || line.length() < 10) && line_counter < lines.length - 2);
			
			for (int i = 0; i < line.length(); i++) {
				if (Character.isDigit(line.charAt(i)) || Arrays.stream(number_exception).anyMatch((line.charAt(i) + "")::contains) == true) {
					if (seq_start == -1) {
						seq_start = i;
					}
					seq_end = i + 1;
				} else {
					if (seq_start != -1 && seq_end != -1) {
						if (line.substring(seq_start,seq_end).length() >= 9) {
							number = line.substring(seq_start,seq_end);
							//System.out.println("==>" + number + "<==");
							brake = true;
							break;
						} else {
							seq_start = -1;
						}
					} else {
						seq_start = -1;
					}
				}
			}
			if (brake == true) {
				break;
			}
		} while (number.equals(""));
		System.out.println("Number: " + number);
		//End of Number algorithm
		
		
		
		line_counter = 0;
		
		
		
		//Start of email algorithm
		do {
			if (line_counter >= lines.length) {
				email = "NOT FOUND";
				break;
			}
			line = lines[line_counter];
			
			do {
				line = lines[line_counter] + " ";
				line_counter++;
			} while ((!line.contains("@") || line.length() < 2) && line_counter < lines.length - 2);
			
			int index_at = line.indexOf("@");
			int index_start = -1;
			int index_end = -1;
			
			for (int i = index_at; i >= 0; i--) {
				if ((line.charAt(i) + "").equals(" ")) {
					break;
				} else {
					index_start = i;
				}
			}
			for (int i = index_at; i < line.length();i++) {
				if ((line.charAt(i) + "").equals(" ")) {
					break;
				} else {
					index_end = i + 1;
				}
			}
			email = line.substring(index_start,index_end);
			if (email.contains("@")) {
				break;
			} else {
				email = "";
			}

		} while (email.equals(""));
		//End of email algorithm
		System.out.println("Email: " + email);
		
		
		line_counter = 0;
		
		
		
		//Start of getting yrs experience
		String[] experience_search = {"work history", "experience","years"};
		
		for (XWPFTable table : tables) {
			for (XWPFTableRow row: table.getRows()) {
				List<XWPFTableCell> cells_pre_conv = row.getTableCells();
				XWPFTableCell[] cells = new XWPFTableCell[cells_pre_conv.size()];
				cells_pre_conv.toArray(cells);
				if (Arrays.stream(experience_search).anyMatch(cells[0].getText()::contains)) {
					//int seq_start = -1;
					//int seq_end = -1;
					line = cells[1].getText();
					//W.I.P
				}
			}
		}
		if (experience.equals("")) {experience = "NOT FOUND";}
		//End of getting yrs experience
		System.out.println("Experience: " + experience);
		
		
		line_counter = 0;
		
		workPosition="NOT FOUND";
		company="NOT FOUND";
		current_pay="NOT FOUND";
		expected_pay = "NOT FOUND";
		manual_automation = "NOT FOUND";
		tools = "NOT FOUND";
		visa = "NOT FOUND";
		
		//end of getting information
		try {
			fis.close();
			docx.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Information packet = new Information(id,name,email,number,company,workPosition,experience,current_pay,expected_pay,manual_automation,tools,visa,path);
		new confirmData(packet, this, reference);
	}
	
	public boolean addpacket(Information packet) {
		boolean add_final = true;
		

		if (packets.size() == 0) {
			packets.add(packet);
			return true;
		}
		
		for (Information pac: packets) { //couldve made one huge if statement but too messy
			boolean add = false;
			
			if (!pac.name.equals(packet.name)) add = true;
			if (!pac.email.equals(packet.email)) add = true;
			if (!pac.number.equals(packet.number)) add = true;
			if (!pac.company.equals(packet.company)) add = true;
			if (!pac.workPosition.equals(packet.workPosition)) add = true;
			if (!pac.experience.equals(packet.experience)) add = true;
			if (!pac.current_pay.equals(packet.current_pay)) add = true;
			if (!pac.expected_pay.equals(packet.expected_pay)) add = true;
			if (!pac.manual_automation.equals(packet.manual_automation)) add = true;
			if (!pac.tools.equals(packet.tools)) add = true;
			if (!pac.visa.equals(packet.visa)) add = true;
			//if (!pac.location.equals(packet.location)) add = true;
			
			if (add == false) add_final = false;
		}
		
		if (add_final) {
			packets.add(packet);
			return true;
		}
		return false;
	}
	
	public boolean readExcel(String excelFile_path) {
		System.out.print("Reading Excel...");
		File excelFile = new File(excelFile_path);
		stored_rows = new ArrayList<String>();
		
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			
			while (rows.hasNext()) {
				Row row = rows.next();
				
				String compare_to = "";
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String text_cell = cell.toString().trim();

					if (!text_cell.trim().equals("")) {
						try {
							text_cell = String.valueOf((int) Double.parseDouble(text_cell));
						} catch (NumberFormatException e) {
						}
						compare_to += text_cell+"!";
					}
				} 
				compare_to=compare_to.substring(0, compare_to.length()-1);
				//System.out.println("comp: '" + compare_to + "'");
				
				stored_rows.add(compare_to);
			}
			workbook.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("OK!");
		return true;
	}
	
	public boolean writeExcel(String[][] toPrint) {
		LocalDateTime now = LocalDateTime.now();  
		
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        
        for (int p = 0; p < toPrint.length; p++) {
        	Row row = sheet.createRow(p);
        	for (int i = 0; i < toPrint[p].length-1; i++) {
        		Cell cell = row.createCell(i);
        		cell.setCellValue((String)toPrint[p][i+1]);
        	}
        }
        
        try (FileOutputStream outputStream = new FileOutputStream("files\\excel_export\\Exported Record " + now.getYear() + " " + now.getMonth() + " " + now.getDayOfMonth() + ".xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e1) {
        	System.out.println("File not found!");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        try {
			workbook.close();
		} catch (IOException e) {
			System.out.println("Workbook couldn't be closed!");
		}
        System.out.println("Success!");
		return true;
	}
	
	public boolean readPdf(String pdf_path, String file_name) {
		//System.out.println(file_name);
		Information packet = new Information("",file_name,"","","","","","","","","","",pdf_path);
		new confirmData(packet, this, reference);
		
		return true;
	}
}