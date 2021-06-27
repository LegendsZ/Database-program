import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class confirmData {

	String stored_files = "files\\stored_files\\";
	
	private JFrame frmConfirmData;
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtEmail;
	private JTextField txtExperience;
	private JTextField txtPosition;
	private JTextField txtCompany;
	private JTextField txtCPay;
	private JTextField txtEPay;
	private JTextField txtManualAutomation;
	private JTextField txtToolsKnowledge;
	private JTextField txtVisa;

	Formatter format;
	Information packet;
	GRes reference;
	
	/**
	 * Create the application.
	 */
	public confirmData(Information pack, Formatter formatRef, GRes ref) {
		initialize();
		
		format = formatRef;
		packet = pack;
		reference = ref;
		
		txtName.setText(pack.name);
		txtEmail.setText(pack.email);
		txtNumber.setText(pack.number);
		txtCompany.setText(pack.company);
		txtPosition.setText(pack.workPosition);
		txtExperience.setText(pack.experience);
		txtCPay.setText(pack.current_pay);
		txtEPay.setText(pack.expected_pay);
		txtManualAutomation.setText(pack.manual_automation);
		txtToolsKnowledge.setText(pack.tools);
		txtVisa.setText(pack.visa);
	
		frmConfirmData.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfirmData = new JFrame();
		frmConfirmData.getContentPane().setBackground(new Color(51,51,51));
		frmConfirmData.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		frmConfirmData.setTitle("Confirm Data");
		frmConfirmData.setResizable(false);
		frmConfirmData.setBounds(100, 100, 461, 590);
		frmConfirmData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConfirmData.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblName.setBounds(10, 11, 123, 33);
		frmConfirmData.getContentPane().add(lblName);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setForeground(Color.WHITE);
		lblNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNumber.setBounds(10, 97, 123, 33);
		frmConfirmData.getContentPane().add(lblNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmail.setBounds(10, 53, 123, 33);
		frmConfirmData.getContentPane().add(lblEmail);
		
		JLabel lblExperience = new JLabel("Experience:");
		lblExperience.setForeground(Color.WHITE);
		lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExperience.setBounds(10, 233, 123, 33);
		frmConfirmData.getContentPane().add(lblExperience);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPosition.setBounds(10, 189, 123, 33);
		frmConfirmData.getContentPane().add(lblPosition);
		
		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setForeground(Color.WHITE);
		lblCompany.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCompany.setBounds(10, 143, 123, 33);
		frmConfirmData.getContentPane().add(lblCompany);
		
		JLabel lblCPay = new JLabel("Current Pay:");
		lblCPay.setForeground(Color.WHITE);
		lblCPay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCPay.setBounds(10, 277, 123, 33);
		frmConfirmData.getContentPane().add(lblCPay);
		
		JLabel lblEPay = new JLabel("Expected Pay:");
		lblEPay.setForeground(Color.WHITE);
		lblEPay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEPay.setBounds(10, 321, 123, 33);
		frmConfirmData.getContentPane().add(lblEPay);
		
		JLabel lblManualAutomation = new JLabel("Manual/Auto:");
		lblManualAutomation.setForeground(Color.WHITE);
		lblManualAutomation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblManualAutomation.setBounds(10, 365, 123, 33);
		frmConfirmData.getContentPane().add(lblManualAutomation);
		
		JLabel lblTools = new JLabel("Tools:");
		lblTools.setForeground(Color.WHITE);
		lblTools.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTools.setBounds(10, 409, 123, 33);
		frmConfirmData.getContentPane().add(lblTools);
		
		JLabel lblVisa = new JLabel("Visa Status:");
		lblVisa.setForeground(Color.WHITE);
		lblVisa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblVisa.setBounds(10, 453, 123, 33);
		frmConfirmData.getContentPane().add(lblVisa);
		
		txtName = new JTextField();
		txtName.setForeground(Color.WHITE);
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtName.setBounds(143, 10, 295, 35);
		frmConfirmData.getContentPane().add(txtName);
		txtName.setBackground(new Color(105,105,105));
		txtName.setColumns(10);
		
		txtNumber = new JTextField();
		txtNumber.setForeground(Color.WHITE);
		txtNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtNumber.setColumns(10);
		txtNumber.setBounds(143, 97, 295, 35);
		txtNumber.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtNumber);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(143, 53, 295, 35);
		txtEmail.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtEmail);
		
		txtExperience = new JTextField();
		txtExperience.setForeground(Color.WHITE);
		txtExperience.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtExperience.setColumns(10);
		txtExperience.setBounds(143, 233, 295, 35);
		txtExperience.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtExperience);
		
		txtPosition = new JTextField();
		txtPosition.setForeground(Color.WHITE);
		txtPosition.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtPosition.setColumns(10);
		txtPosition.setBounds(143, 187, 295, 35);
		txtPosition.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtPosition);
		
		txtCompany = new JTextField();
		txtCompany.setForeground(Color.WHITE);
		txtCompany.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtCompany.setColumns(10);
		txtCompany.setBounds(143, 141, 295, 35);
		txtCompany.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtCompany);
		
		txtCPay = new JTextField();
		txtCPay.setForeground(Color.WHITE);
		txtCPay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtCPay.setColumns(10);
		txtCPay.setBounds(143, 277, 295, 35);
		txtCPay.setBackground(new Color(105,105,105));
		frmConfirmData.getContentPane().add(txtCPay);
		
		txtEPay = new JTextField();
		txtEPay.setText((String) null);
		txtEPay.setForeground(Color.WHITE);
		txtEPay.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtEPay.setColumns(10);
		txtEPay.setBackground(SystemColor.controlDkShadow);
		txtEPay.setBounds(143, 321, 295, 35);
		frmConfirmData.getContentPane().add(txtEPay);
		
		txtManualAutomation = new JTextField();
		txtManualAutomation.setText((String) null);
		txtManualAutomation.setForeground(Color.WHITE);
		txtManualAutomation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtManualAutomation.setColumns(10);
		txtManualAutomation.setBackground(SystemColor.controlDkShadow);
		txtManualAutomation.setBounds(143, 365, 295, 35);
		frmConfirmData.getContentPane().add(txtManualAutomation);
		
		txtToolsKnowledge = new JTextField();
		txtToolsKnowledge.setText((String) null);
		txtToolsKnowledge.setForeground(Color.WHITE);
		txtToolsKnowledge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtToolsKnowledge.setColumns(10);
		txtToolsKnowledge.setBackground(SystemColor.controlDkShadow);
		txtToolsKnowledge.setBounds(143, 409, 295, 35);
		frmConfirmData.getContentPane().add(txtToolsKnowledge);

		txtVisa = new JTextField();
		txtVisa.setText((String) null);
		txtVisa.setForeground(Color.WHITE);
		txtVisa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtVisa.setColumns(10);
		txtVisa.setBackground(SystemColor.controlDkShadow);
		txtVisa.setBounds(143, 453, 295, 35);
		frmConfirmData.getContentPane().add(txtVisa);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(105,105,105));
		btnClear.setForeground(Color.WHITE);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
				txtEmail.setText("");
				txtNumber.setText("");
				txtCompany.setText("");
				txtPosition.setText("");
				txtExperience.setText("");
				txtCPay.setText("");
				txtEPay.setText("");
				txtManualAutomation.setText("");
				txtToolsKnowledge.setText("");
				txtVisa.setText("");
			}
		});
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnClear.setBounds(10, 497, 123, 45);
		frmConfirmData.getContentPane().add(btnClear);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(new Color(105,105,105));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ystem.out.println("");
				packet.name = txtName.getText();
				packet.email = txtEmail.getText();
				packet.number = txtNumber.getText();
				packet.company = txtCompany.getText();
				packet.workPosition = txtPosition.getText();
				packet.experience = txtExperience.getText();
				packet.current_pay = txtCPay.getText();
				packet.expected_pay = txtEPay.getText();
				packet.manual_automation = txtManualAutomation.getText();
				packet.tools = txtToolsKnowledge.getText();
				packet.visa = txtVisa.getText();

				try {
					File myFile = new File(packet.location);		
					myFile.renameTo(new File("files\\stored_files\\" + myFile.getName()));
					packet.location = stored_files + myFile.getName();
					System.out.println("sss");
				}catch (Exception ex) {
					System.out.println("Something went wrong!");
					ex.printStackTrace();
				}
				
				format.addpacket(packet);
				reference.docx=true;
				reference.loadT();
				reference.saveData();
				frmConfirmData.dispose();
			}
		});
		btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnConfirm.setBounds(143, 499, 295, 45);
		frmConfirmData.getContentPane().add(btnConfirm);
	}
}
