package FramesGood;
import java.awt.HeadlessException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Goodies.Company;
import Goodies.Departament;
import Goodies.Employee;

public class SearchEmployeeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Employee> searchedlist = new ArrayList<Employee>();
	private static final String COMPANY_XML = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\company.xml";
	private static final String COMPANY_XML_NEW = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\NewCompany.xml";
	private JTextField idField, fnameField, lnameField, phoneField, nchField, salaryField, depIdField, depNameField,
			depManField;
	private JPanel myPanel, buttonPanel;
	private JButton jb1, jb2;
	private JTable jtable;
	String s;
	MainFrame mf;

	public SearchEmployeeFrame() throws JAXBException, IOException  {
		mf = new MainFrame();
		jtable = new JTable();
		lnameField = new JTextField(10);
		jb1 = new JButton("Search");
		jb2 = new JButton("Dismiss");
		buttonPanel = new JPanel();
		myPanel = new JPanel();

		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Last Name : "));
		myPanel.add(lnameField);

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
		
		lnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					s = lnameField.getText();

					try {
						if(returnFoundList(s).isEmpty()){
							JOptionPane.showMessageDialog(null, "No Employee Found!");
							dispose();
						}else{
							jtable.setModel(getTableModel(returnFoundList(s)));
							add(new JScrollPane(jtable));
							System.out.println(1);
						}
					} catch (HeadlessException | JAXBException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		
		

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s = lnameField.getText();
				System.out.println(s);
				try {
					System.out.println(returnFoundList(s).get(0));
				} catch (FileNotFoundException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					/*if(returnFoundList(s).isEmpty()){
						JOptionPane.showMessageDialog(null, "No Employee Found!");
						System.out.println(0);
						dispose();
					}else{
						jtable.setModel(getTableModel(returnFoundList(s)));
						
						add(new JScrollPane(jtable));
						System.out.println(1);
					}*/
				
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
		setSize(600, 400);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public ArrayList<Employee> returnFoundList(String s) throws JAXBException, FileNotFoundException{
		JAXBContext context = JAXBContext.newInstance(Company.class);
		Unmarshaller um = context.createUnmarshaller();
		Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));
		ArrayList<Employee> list = comp2.getEmployeesList();
	
		for(int i =0; i<list.size(); i++){
			if (list.get(i).getEmployeeLName().equals(s)){
				searchedlist.add(list.get(i));
			}
		}
		
		return searchedlist;
	}
	
	public javax.swing.table.DefaultTableModel getTableModel(ArrayList<Employee> list) throws JAXBException, IOException {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID", "NAME", "PHONE", "CHILDREN", "SALARY", "DEP", "MANAGER" });
		model.setRowCount(list.size());
		int row = 0;
		for (int i = 0; i < list.size(); i++) {
			model.setValueAt(list.get(i).getEmployeeId(), row, 0);
			model.setValueAt(list.get(i).getEmployeeFName() + " " + list.get(i).getEmployeeLName(), row, 1);
			model.setValueAt(list.get(i).getEmployeePhone(), row, 2);
			model.setValueAt(list.get(i).getEmployeeChildren(), row, 3);
			model.setValueAt(list.get(i).getEmployeeSalary(), row, 4);
			model.setValueAt(list.get(i).getEmployeeDep().getDepName(), row, 5);
			model.setValueAt(list.get(i).getEmployeeDep().getManagerName(), row, 6);
			row++;
		}

		return model;
	}
	
}
