package PDFread.PDFread;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class SecScreen extends SubmittalUI {

	private JFrame frame;
	private JFrame f = new JFrame("Second");
	private static JTextArea textArea;
	
	public SecScreen() {
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(660,500);
		f.getContentPane().setLayout(null);
		f.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 631, 453);
		f.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Specification Sections");
		
	    textArea = new JTextArea("-------------------------------------------------");
	   
	    
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

private void appendSpec() {
	
	for(int i = 0; i < PDFtest.D22Spec.size(); i++) {
		
		textArea.append(PDFtest.D22Spec.elementAt(i));
		
	}
	
}

};