import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class SecScreen extends SubmittalUI{

	private JFrame frame;
	private JFrame f = new JFrame("Second");
	
	public SecScreen() {
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(660,500);
		f.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(500, 140, 631, 500);
		f.getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Specification Sections");
		lblNewLabel.setLabelFor(f);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(149)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(127, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(270, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(257))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		f.setVisible(true);
		
	}
}
