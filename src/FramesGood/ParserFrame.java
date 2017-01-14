package FramesGood;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Goodies.Company;
import Goodies.Employee;

public class ParserFrame extends JFrame {
	private static final String COMPANY_XML = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\NewCompany.xml";
	private JMenuBar jmb;
	private JButton jb1;
	private JButton jb2, jb3;
	private JTextArea jta, jta2;
	private JPanel jp;

	ParserFrame() {
		jmb = new JMenuBar();
		jb1 = new JButton("XML");
		jb2 = new JButton("JOutput");
		jb3 = new JButton("Logout");
		jta = new JTextArea(200, 200);
		jta2 = new JTextArea(200, 200);
		jp = new JPanel();
		setJMenuBar(jmb);
		jmb.add(jb1);
		jmb.add(jb2);
		jmb.add(jb3);

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String t;
				try {
					jta = new JTextArea(200, 200);
					t = readFile(COMPANY_XML, StandardCharsets.UTF_8);
					jta.setText(t);
					jta.setBackground(Color.gray);
					jta.setForeground(Color.white);

					jta.setVisible(true);
					jta.setEditable(false);
					JScrollPane jScrollPane = new JScrollPane(jta);
					add(jScrollPane);
					revalidate();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JAXBContext context;
				try {
					
					jta = new JTextArea(200, 200);
					jta.setText("");
					context = JAXBContext.newInstance(Company.class);
					Unmarshaller um = context.createUnmarshaller();
					Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));

					ArrayList<Employee> list = comp2.getEmployeesList();
					FileOutputStream fos = new FileOutputStream(
							"D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\employees.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					for (Employee emp : list) {
						oos.writeObject(emp);
					}
					oos.close();

					ArrayList<Employee> employees2 = new ArrayList<Employee>();
					FileInputStream fis = new FileInputStream(
							"D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\employees.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					for (Employee emp : list) {
						// read from file
						Employee result = (Employee) ois.readObject();
						employees2.add(emp);
					}
					ois.close();
					String t = employees2.toString();
					jta.setText(t);
					jta.setBackground(Color.gray);
					jta.setForeground(Color.white);

					jta.setVisible(true);
					jta.setEditable(false);
					JScrollPane jScrollPane = new JScrollPane(jta);
					add(jScrollPane);
					revalidate();
					

				} catch (JAXBException | IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout ? ");
				if (res == JOptionPane.YES_OPTION) {
					try {
						dispose();
						new LoginFrame("").setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
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
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Parser");
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
