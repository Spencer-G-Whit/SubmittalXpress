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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.List;

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
import javax.swing.JProgressBar;

public class SubmittalUI {
	
	private JFrame frame;
	private JFrame f = new JFrame("Second");
	private static JTextArea textArea;
	///////////////////////
	private JPanel checklistpanel;
	private JFrame frmSubmittalXpress;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton Continue;
	protected static String filePath; 
	private JFileChooser jFilePick;
	private JFrame pop;
	protected static SubmittalUI window = new SubmittalUI();
	public PDFreader PDFtest;
	private JProgressBar progressBar; 
	
	 ArrayList<JCheckBox> CheckboxList = new ArrayList<JCheckBox>();
	
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
		
	    progressBar = new JProgressBar();
		progressBar.setValue(0);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(139)
							.addComponent(txtpnSubmittalxpress, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(165)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(258)
							.addComponent(Continue))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(244)
							.addComponent(btnNewButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(218)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addComponent(txtpnSubmittalxpress, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(Continue)
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(310, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmSubmittalXpress.setBounds(500, 140, 631, 500);
		frmSubmittalXpress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	 ActionListener actionListener = new ActionListener() {
	        @Override
	        
	        public void actionPerformed(ActionEvent a) {
	        
	        	progressBar.setIndeterminate(true);
				
	        	
	        if(a.getSource() == btnNewButton) {
	        	
	        	openFile();
	        	
	        }else if(a.getSource() == Continue && filePath != null) {
	        	
	  

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
				        
				    	window.frmSubmittalXpress.setVisible(false);
				    	window.frmSubmittalXpress.dispose();
				    	
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
	        		
	        		checklistpanel = new JPanel();
	        		
	        		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		f.setSize(660,500);
	        		f.getContentPane().setLayout(null);
	        		f.setLocationRelativeTo(null);
	        		
	        		JPanel panel = new JPanel();
	        		panel.setBounds(10, 10, 631, 453);
	        		f.getContentPane().add(panel);
	        		
	        		JLabel lblNewLabel = new JLabel("Specification Sections");
	        		
	        	    textArea = new JTextArea("");
	        	    Font newFont = new Font("SansSerif", Font.PLAIN, 14);
	        	    textArea.setFont(newFont);
	        	    appendSpec();
	        	 
	        		JLabel lblNewLabel_1 = new JLabel("Select Sections");
	        		GroupLayout gl_panel = new GroupLayout(panel);
	        		gl_panel.setHorizontalGroup(
	        			gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
	        					.addGap(74)
	        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
	        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
	        					.addGap(250))
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGap(39)
	        					.addComponent(checklistpanel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
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
	        		
	        		panel.setLayout(gl_panel);
	        		f.setVisible(true);
	        		
	        	}

	        public void appendSpec() {
	        	
	        	 for (int i = 0; i < PDFtest.D22Spec.size(); i++) {
	        	    	textArea.append(PDFtest.D22Spec.elementAt(i));
	        	    	textArea.append("\n");		
	        	    	textArea.append("\n");
	        	    	JCheckBox check = new JCheckBox("Keep");
	        	    	check.addActionListener(actionListener);
	        	    	CheckboxList.add(check);
		        		checklistpanel.add(check);
	        }
	      }
	        
	        
	        ActionListener checkbox = new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	        	
	            for (JCheckBox checkBox : CheckboxList) {
	                if (checkBox.isSelected()) {
	                  
	                    
	                }
	            }
	            // TODO do something with infos
	        }
	        };
	        
	               
	        
	        
	        
};















	
	
