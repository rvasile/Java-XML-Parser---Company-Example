package FramesGood;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;


public class LoginFrame extends JFrame {
	JPanel p1;
	private JLabel l1;
	private JLabel l2;
	private JTextField t1;
	private JPasswordField pass;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel l3;
	String s1;
	Validare v = new Validare();
	
	public JTextField getT1() {
		return t1;
	}

	public void setT1(JTextField t1) {
		this.t1 = t1;
	}

	public JPasswordField getPass() {
		return pass;
	}

	public void setPass(JPasswordField pass) {
		this.pass = pass;
	}

	public LoginFrame(String s1) throws IOException {
		this.s1 = s1;
		l3 = new JLabel("Username not found!");
		l3.setForeground(Color.red);
		p1 = new JPanel();
		l1 = new JLabel("Username : ");
		l2 = new JLabel("Password : ");
		t1 = new JTextField(20);
		pass = new JPasswordField(20);

		
		b2 = new JButton("Login");
		

		p1.setLayout(null);
		getContentPane().add(p1);

		l3.setBounds(185, 15, 380, 20);
		p1.add(l3);
		l3.setVisible(false);

		l1.setBounds(50, 60, 120, 25);
		p1.add(l1);

		l2.setBounds(50, 110, 120, 25);
		p1.add(l2);

		t1.setBounds(175, 60, 150, 25); // 155 pentru ca am pe x = 30 de la l1
		// si inca 120 lungime
		p1.add(t1);

		pass.setBounds(175, 110, 150, 25); // 155 pentru ca am pe x = 30 de la
		// l1 si inca 120 lungime
		p1.add(pass);

		
		b2.setBounds(195, 180, 100, 25);
		p1.add(b2);


		t1.setText(s1);

		// Buton de login;
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(v.ValideazaUser(t1.getText(), new String(pass.getPassword())).equals("admin")){
					dispose();
					try {
						String[] options = {"Parser","GUI"}; 
						int choice = JOptionPane.showOptionDialog(null, "Parser or GUI ?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
						if(choice == 0){
							new ParserFrame().setVisible(true);
						}else if(choice == 1){
							new MainFrame().setVisible(true);
						}
					} catch (JAXBException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(v.ValideazaUser(t1.getText(), new String(pass.getPassword())).equals("user")){
					dispose();
					try {
						String[] options = {"Parser","GUI"}; 
						int choice = JOptionPane.showOptionDialog(null, "Parser or GUI ?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
						if(choice == 0){
							new ParserFrame().setVisible(true);
						}else if(choice == 1){
							new MainFrameUser().setVisible(true);
						}
					} catch (JAXBException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Username/Password wrong. Try Again!");
					t1.setText("");
					pass.setText("");
				}
			}
		});
		
		

		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(v.ValideazaUser(t1.getText(), new String(pass.getPassword())).equals("admin")){
						dispose();
						try {
							String[] options = {"Parser","GUI"}; 
							int choice = JOptionPane.showOptionDialog(null, "Parser or GUI ?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
							if(choice == 0){
								new ParserFrame().setVisible(true);
							}else if(choice == 1){
								new MainFrame().setVisible(true);
							}
						} catch (JAXBException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else if(v.ValideazaUser(t1.getText(), new String(pass.getPassword())).equals("user")){
						dispose();
						try {
							String[] options = {"Parser","GUI"}; 
							int choice = JOptionPane.showOptionDialog(null, "Parser or GUI ?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
							if(choice == 0){
								new ParserFrame().setVisible(true);
							}else if(choice == 1){
								new MainFrameUser().setVisible(true);
							}
						} catch (JAXBException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else{
						JOptionPane.showMessageDialog(null, "Username/Password wrong. Try Again!");
						t1.setText("");
						pass.setText("");
					}
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Yes","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Are you sure you want to exit?", "Company's Employee List", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			      System.exit(0);          
			    }
			  }
		});
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Login");
		setSize(500, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) throws IOException {
		new LoginFrame("").setVisible(true);
	}

}
