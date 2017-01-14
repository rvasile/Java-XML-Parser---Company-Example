package FramesGood;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Goodies.Company;
import Goodies.Departament;
import Goodies.Employee;

public class AddEmployeeFrame extends JFrame {
	private static final String COMPANY_XML = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\company.xml";
	private static final String COMPANY_XML_NEW = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\NewCompany.xml";
	private JTextField idField, fnameField, lnameField, phoneField, nchField, salaryField, depIdField, depNameField,
			depManField;
	private JPanel myPanel, buttonPanel;
	private JButton jb1, jb2;
	private Company company;

	public AddEmployeeFrame() {
		idField = new JTextField(10);
		fnameField = new JTextField(10);
		lnameField = new JTextField(10);
		phoneField = new JTextField(10);
		nchField = new JTextField(10);
		salaryField = new JTextField(10);
		depIdField = new JTextField(10);
		depNameField = new JTextField(10);
		depManField = new JTextField(10);
		jb1 = new JButton("Add");
		jb2 = new JButton("Dismiss");
		buttonPanel = new JPanel();
		myPanel = new JPanel();
		myPanel.add(new JLabel("ID :"));
		myPanel.add(idField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("First Name : "));
		myPanel.add(fnameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Last Name : "));
		myPanel.add(lnameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Phone : "));
		myPanel.add(phoneField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("No. Children : "));
		myPanel.add(nchField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Salary : "));
		myPanel.add(salaryField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Dep ID : "));
		myPanel.add(depIdField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Dep Name : "));
		myPanel.add(depNameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Dep Manager : "));
		myPanel.add(depManField);

		buttonPanel.add(jb1);
		buttonPanel.add(Box.createHorizontalStrut(15));
		buttonPanel.add(jb2);

		add(myPanel);
		myPanel.add(buttonPanel);

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		depManField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Employee emp = new Employee();
					emp.setEmployeeId(idField.getText());
					emp.setEmployeeFName(fnameField.getText());
					emp.setEmployeeLName(lnameField.getText());
					emp.setEmployeePhone(phoneField.getText());
					emp.setEmployeeSalary(Double.parseDouble(salaryField.getText()));
					emp.setEmployeeChildren(Integer.parseInt(nchField.getText()));
					Departament dep = new Departament();
					dep.setDepNo(Integer.parseInt(depIdField.getText()));
					dep.setDepName(depNameField.getText());
					dep.setManagerName(depManField.getText());
					emp.setEmployeeDep(dep);

					Company company = new Company();
					company.setCompanyName("IBM");
					company.setCompanyLocation("Munich");

					JAXBContext context;
					try {
						context = JAXBContext.newInstance(Company.class);
						Unmarshaller um = context.createUnmarshaller();
						Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));
						ArrayList<Employee> list = comp2.getEmployeesList();
						list.add(emp);
						System.out.println(list.size());
						Marshaller m = context.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
						// Write to System.out
						m.marshal(comp2, System.out);
						// Write to File
						m.marshal(comp2, new File(COMPANY_XML_NEW));
						dispose();
					} catch (JAXBException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		
		

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Employee emp = new Employee();
				emp.setEmployeeId(idField.getText());
				emp.setEmployeeFName(fnameField.getText());
				emp.setEmployeeLName(lnameField.getText());
				emp.setEmployeePhone(phoneField.getText());
				emp.setEmployeeSalary(Double.parseDouble(salaryField.getText()));
				emp.setEmployeeChildren(Integer.parseInt(nchField.getText()));
				Departament dep = new Departament();
				dep.setDepNo(Integer.parseInt(depIdField.getText()));
				dep.setDepName(depNameField.getText());
				dep.setManagerName(depManField.getText());
				emp.setEmployeeDep(dep);
				

				Company company = new Company();
				company.setCompanyName("IBM");
				company.setCompanyLocation("Munich");

				JAXBContext context;
				try {
					context = JAXBContext.newInstance(Company.class);
					Unmarshaller um = context.createUnmarshaller();
					Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));
					ArrayList<Employee> list = comp2.getEmployeesList();
					list.add(emp);
					System.out.println(list.size());
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					// Write to System.out
					m.marshal(comp2, System.out);
					// Write to File
					m.marshal(comp2, new File(COMPANY_XML_NEW));
					dispose();
				} catch (JAXBException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// emp.setEmployeeDep();

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
		
		
		setTitle("Add Employee");
		setSize(1900, 110);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/*
	 * public ArrayList<Employee> unMarshallerList(){
	 * 
	 * }
	 */
}
