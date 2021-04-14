package PDFread.PDFread;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class SubmittalUI {
	
	private JFrame frame;
	private JFrame f = new JFrame("Second");
	private static JTextArea textArea;
	///////////////////////

	private JFrame frmSubmittalXpress;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton Continue;
	protected static String filePath; 
	private JFileChooser jFilePick;
	private JFrame pop;
	protected static SubmittalUI window = new SubmittalUI();
	public PDFreader PDFtest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frmSubmittalXpress.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void openFile() {
//initialize jFilePicker		
		boolean isPdf = true;
		jFilePick = new JFileChooser();
//set a new file filter matching pdf extension
		jFilePick.resetChoosableFileFilters();
		jFilePick.setFileFilter(new FileNameExtensionFilter("*.pdf", "pdf","PDF","Pdf"));
		jFilePick.setAcceptAllFileFilterUsed(false);
		
		while(isPdf) {
		int j = jFilePick.showOpenDialog(frmSubmittalXpress);
		File takeFile = jFilePick.getSelectedFile();
		if(takeFile != null) {
		filePath = takeFile.getAbsolutePath();
		}
//if the integer shows 1, and is the same as approve option,
// then take file path. 
		 if (JFileChooser.APPROVE_OPTION == j && filePath.endsWith(".pdf")) {
			   textField.setText(filePath);	 
			  
		} else {
			JOptionPane.showMessageDialog(pop, "Invalid, not a .pdf extension", "ALERT", JOptionPane.WARNING_MESSAGE);
		}
		 isPdf = false; 
	}
		
}
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SubmittalUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSubmittalXpress = new JFrame();
		frmSubmittalXpress.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmSubmittalXpress.getContentPane().setEnabled(false);
		frmSubmittalXpress.setFont(new Font("MS PGothic", Font.BOLD, 16));
		frmSubmittalXpress.setTitle("SubmittalXPress");
		frmSubmittalXpress.setType(Type.NORMAL);
		frmSubmittalXpress.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmSubmittalXpress.getContentPane().add(panel);
		
	    btnNewButton = new JButton("Browse File...");
		btnNewButton.addActionListener(actionListener);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setAutoscrolls(true);
		textField.setEditable(false);
		
		JTextPane txtpnSubmittalxpress = new JTextPane();
		txtpnSubmittalxpress.setEditable(false);
		txtpnSubmittalxpress.setBackground(SystemColor.menu);
		txtpnSubmittalxpress.setText("               SubmittalXPress");
		txtpnSubmittalxpress.setFont(new Font("Rockwell", Font.PLAIN, 18));
	
	    Continue = new JButton("Continue");
		Continue.addActionListener(actionListener);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(242)
							.addComponent(btnNewButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(139)
							.addComponent(txtpnSubmittalxpress, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(165)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(258)
							.addComponent(Continue)))
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(txtpnSubmittalxpress, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(Continue)
					.addContainerGap(347, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmSubmittalXpress.setBounds(500, 140, 631, 500);
		frmSubmittalXpress.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	 ActionListener actionListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent a) {
	        
	        if(a.getSource() == btnNewButton) {
	        	
	        	openFile();
	        	
	        }else if(a.getSource() == Continue && filePath != null) {
	        	//window.frmSubmittalXpress.dispose();
	        	window.frmSubmittalXpress.setVisible(false);
	   
					try {
						System.out.print("Starting PDF reader \n");
				    	// LIST OF DIFFERENT SPECIFICATIONS TO TEST
				    	//"C:\\Users\\msvetlichny23\\Desktop\\2018-10-29_WFXO6300_P-946_Final_Design_Spec_Book_Vol_2_of_3 (Div 3 to 22).pdf"
				    	//PDFreader PDFtest = new PDFreader("C:\\\\Users\\\\Michael\\\\Desktop\\\\SPECS\\2018-10-29_WFXO6300_P-946_Final_Design_Spec_Book_Vol_2_of_3 (Div 3 to 22).pdf");
				    	//PDFreader PDFtest = new PDFreader("C:\\Users\\Michael\\Desktop\\SPECS\\LILIA_30_CD_Spec_V4_UPDATED.pdf");
				        PDFtest = new PDFreader(filePath);
				        PDFtest.checkSpecs();
		//		        System.out.print("Here are the specification sections we found in Division 22: \n");
//				        PDFtest.printSpecs();
				        System.out.print("================================================");
				        System.out.print("\nHere are the corresponding page numbers: \n");
				        PDFtest.printPages();
				        System.out.print("================================================");
				        System.out.print("\nHere are the sub sections in each spec section: \n");
				        PDFtest.findProductData();
				        PDFtest.printSpecInfo();
				        //PDFtest.pageFinder();
				        System.out.print("================================================");
				        System.out.print("\nProgram finished running");
				        
				        SecScreen();
				        
				        //new SecScreen();
				        //test = test.appendSpec();
				        
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
	        }
	        	
	        	}
	        };
	        
	       // public void SecScreen  {

	        	
	        	
	        	public void SecScreen() {
	        		
	        		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		f.setSize(660,500);
	        		f.getContentPane().setLayout(null);
	        		f.setLocationRelativeTo(null);
	        		
	        		JPanel panel = new JPanel();
	        		panel.setBounds(10, 10, 631, 453);
	        		f.getContentPane().add(panel);
	        		
	        		JLabel lblNewLabel = new JLabel("Specification Sections");
	        		
	        	    textArea = new JTextArea("-------------------------------------------------\n");
	        	    for (int i = 0; i < PDFtest.D22Spec.size(); i++) {
	        	    	textArea.append(PDFtest.D22Spec.elementAt(i));
	        	    	textArea.append("\n");
	        	    }
	        	    textArea.append("C-3P0\n");
	        	    textArea.append("R2D2\n");
	        	    textArea.append("^There they are!^\n");
	        	   
	        		JPanel checklistpanel = new JPanel();
	        		
	        		JLabel lblNewLabel_1 = new JLabel("Checkboxes");
	        		GroupLayout gl_panel = new GroupLayout(panel);
	        		gl_panel.setHorizontalGroup(
	        			gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
	        					.addGap(74)
	        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
	        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
	        					.addGap(250))
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGap(39)
	        					.addComponent(checklistpanel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
	        					.addContainerGap(190, Short.MAX_VALUE))
	        		);
	        		gl_panel.setVerticalGroup(
	        			gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGap(48)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(lblNewLabel)
	        						.addComponent(lblNewLabel_1))
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
	        						.addComponent(checklistpanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
	        					.addContainerGap(81, Short.MAX_VALUE))
	        		);
	        		checklistpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        		
	        		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
	        		checklistpanel.add(chckbxNewCheckBox_1);
	        		panel.setLayout(gl_panel);
	        		f.setVisible(true);
	        		
	        	}

	        public void appendSpec() {
	        	
	        	for(int i = 0; i < PDFtest.D22Spec.size(); i++) {
	        		
	        		textArea.append(PDFtest.D22Spec.elementAt(i));
	        		
	        	}
	        	
	        }

	      }     	




	
	
