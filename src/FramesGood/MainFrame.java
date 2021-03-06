package FramesGood;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Goodies.Company;
import Goodies.Departament;
import Goodies.Employee;
import Goodies.MarshallUnMXML;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String COMPANY_XML = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\NewCompany.xml";
	private JMenuBar jmb;
	private JMenu jm1,jm2;
	private JMenu jm3;
	private JMenu jm6;
	private JButton jb1;
	private JButton jb2,jb3;
	private JMenuItem jmi1,jmi2;
	private JMenuItem jmi3;
	private JMenuItem jmi14;

	MarshallUnMXML msx = new MarshallUnMXML();
	Company cmp = new Company();
	Employee emp = new Employee();
	Departament dep = new Departament();

	MainFrame() throws JAXBException, IOException {
		new JPanel();
		new JPanel();
		jmb = new JMenuBar();
		jm1 = new JMenu("Add");
		jm2 = new JMenu("Search");
		jm3 = new JMenu("Delete");
		jb2 = new JButton("Refresh");
		jb1 = new JButton("Logout");
		jb3 = new JButton("Parser");
		jmi1 = new JMenuItem("Employee");
		jmi2 = new JMenuItem("Employee");
		jmi3 = new JMenuItem("Employee");
		jm6 = new JMenu("Rules");
		jmi14 = new JMenuItem("Rules Result");
		JTable table = new JTable();
		setJMenuBar(jmb);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jb2);
		jmb.add(jb3);
		jmb.add(jb1);

		jm1.add(jmi1);

		jm2.add(jmi2);
		jm3.add(jmi3);

		jm6.add(jmi14);
		
		jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SearchEmployeeFrame().setVisible(true);
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ParserFrame().setVisible(true);
			}
		});
		
		jb1.addActionListener(new ActionListener() {

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
		
		jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeFrame().setAlwaysOnTop(true);
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					JAXBContext context = JAXBContext.newInstance(Company.class);
					Unmarshaller um;
					um = context.createUnmarshaller();
					Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));
					ArrayList<Employee> list = comp2.getEmployeesList();
					DefaultTableModel tablModel = (DefaultTableModel) table.getModel();
					
					int i=tablModel.getRowCount();
					
					for(int j=i-1; j>=0;j--){
						tablModel.removeRow(j);
					}
					
					for(Employee emp2 : list){
						String row[] = new String[7];
						row[0]= String.valueOf(emp2.getEmployeeId());
						row[1]= emp2.getEmployeeFName()+" "+emp2.getEmployeeLName();
						row[2]= emp2.getEmployeePhone();
						row[3]= String.valueOf(emp2.getEmployeeChildren());
						row[4]= String.valueOf(emp2.getEmployeeSalary());
						row[5]= String.valueOf(emp2.getEmployeeDep().getDepName());
						row[6]= String.valueOf(emp2.getEmployeeDep().getManagerName());
						tablModel.addRow(row);
					}
					
					table.setModel(tablModel);
					
				} catch (JAXBException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (@SuppressWarnings("hiding") IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		
		

		
		table.setModel(getTableModel(msx.XMLDocProcess()));
		add(new JScrollPane(table));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Company");
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
	
	public JPanel returnAddEmployeePanel(){
		
			JTextField idField = new JTextField(10);
	      JTextField fnameField = new JTextField(10);
	      JTextField lnameField = new JTextField(10);
	      JTextField phoneField = new JTextField(10);
	      JTextField nchField = new JTextField(10);
	      JTextField salaryField = new JTextField(10);
	      JTextField depIdField = new JTextField(10);
	      JTextField depNameField = new JTextField(10);
	      JTextField depManField = new JTextField(10);

	      JPanel myPanel = new JPanel();
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
	      
	      return myPanel;
	      
	}

}