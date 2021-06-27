import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class file_reader_gui {
	
	GRes reference;

	private JFrame frmReadFiles;

	/**
	 * Create the application.
	 */
	public file_reader_gui(GRes ref) {
		initialize();
		
		reference = ref;
		frmReadFiles.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReadFiles = new JFrame();
		frmReadFiles.setResizable(false);
		frmReadFiles.setTitle("Read Files");
		frmReadFiles.getContentPane().setBackground(new Color(51,51,51));
		frmReadFiles.getContentPane().setLayout(null);
		
		JButton btnDocx = new JButton("Read Docx");
		btnDocx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reference.findFiles(true,false, false);
			}
		});
		btnDocx.setForeground(Color.WHITE);
		btnDocx.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnDocx.setBackground(SystemColor.controlDkShadow);
		btnDocx.setBounds(10, 11, 282, 45);
		frmReadFiles.getContentPane().add(btnDocx);
		
		JButton btnExcel = new JButton("Read Excel");
		btnExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reference.findFiles(false,true, false);
			}
		});
		btnExcel.setForeground(Color.WHITE);
		btnExcel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnExcel.setBackground(SystemColor.controlDkShadow);
		btnExcel.setBounds(10, 67, 282, 45);
		frmReadFiles.getContentPane().add(btnExcel);
		
		JButton btnPdf = new JButton("Read Pdf");
		btnPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reference.findFiles(false,false, true);
			}
		});
		btnPdf.setForeground(Color.WHITE);
		btnPdf.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnPdf.setBackground(SystemColor.controlDkShadow);
		btnPdf.setBounds(10, 123, 282, 45);
		frmReadFiles.getContentPane().add(btnPdf);
		frmReadFiles.setBounds(100, 100, 317, 217);
		frmReadFiles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
