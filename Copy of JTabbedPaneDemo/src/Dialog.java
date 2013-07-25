import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog() {
		setBounds(100, 100, 248, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_1 = new JTextField();
			textField_1.setBounds(79, 39, 143, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			textField = new JTextField();
			textField.setBounds(79, 8, 143, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 60, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(10, 42, 60, 14);
		contentPanel.add(lblNewLabel);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(10, 70, 105, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(125, 70, 97, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnNewButton = new JButton("Help");
		btnNewButton.setBounds(10, 104, 105, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setBounds(125, 104, 97, 23);
		contentPanel.add(btnNewButton_1);
	}
}
