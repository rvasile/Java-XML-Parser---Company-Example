package Goodies;

import Goodies.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.xml.bind.*;

public class MarshallUnMXML {
	private static final String COMPANY_XML = "D:\\07_Eclipse_Workspace\\NewGoodiesStoreXML\\NewCompany.xml";
	ArrayList<Employee> employeeList = new ArrayList<Employee>();
	public  ArrayList<Employee> XMLDocProcess() throws JAXBException, IOException {

		// create books
		Departament it = new Departament();
		it.setDepNo(123);
		it.setDepName("IT");
		it.setManagerName("Razvan Moc");

		Departament finance = new Departament();
		finance.setDepNo(111);
		finance.setDepName("Finance");
		finance.setManagerName("Robert Ned");

		Departament business = new Departament();
		business.setDepNo(121);
		business.setDepName("Business");
		business.setManagerName("Nicolae Florin");

		Employee emp1 = new Employee();
		emp1.setEmployeeId("123RD");
		emp1.setEmployeeFName("Razvan");
		emp1.setEmployeeLName("Vasile");
		emp1.setEmployeeSalary(2600.00);
		emp1.setEmployeeChildren(0);
		emp1.setEmployeePhone("0453234");
		emp1.setEmployeeDep(it);
		employeeList.add(emp1);

		Employee emp2 = new Employee();
		emp2.setEmployeeId("12RF");
		emp2.setEmployeeFName("Alex");
		emp2.setEmployeeLName("Vasile");
		emp2.setEmployeeChildren(1);
		emp2.setEmployeeSalary(2900.00);
		emp2.setEmployeePhone("011234");
		emp2.setEmployeeDep(finance);
		employeeList.add(emp2);

		Employee emp3 = new Employee();
		emp3.setEmployeeId("123RD");
		emp3.setEmployeeFName("Robert");
		emp3.setEmployeeLName("Radulescu");
		emp3.setEmployeeChildren(3);
		emp1.setEmployeeSalary(3700.00);
		emp3.setEmployeePhone("0723452912");
		emp3.setEmployeeDep(business);
		employeeList.add(emp3);
		
		Employee emp4 = new Employee();
		emp4.setEmployeeId("123RDFG");
		emp4.setEmployeeFName("Ionut");
		emp4.setEmployeeLName("Matache");
		emp4.setEmployeeChildren(2);
		emp4.setEmployeeSalary(3200.00);
		emp4.setEmployeePhone("004213521");
		emp4.setEmployeeDep(business);
		employeeList.add(emp4);
		
		Employee emp5 = new Employee();
		emp5.setEmployeeId("211ZZB");
		emp5.setEmployeeFName("Mircea");
		emp5.setEmployeeLName("Simion");
		emp5.setEmployeeChildren(1);
		emp5.setEmployeeSalary(4520.00);
		emp5.setEmployeePhone("0728523401");
		emp5.setEmployeeDep(it);
		employeeList.add(emp5);
		
		Employee emp6 = new Employee();
		emp6.setEmployeeId("2165ZB");
		emp6.setEmployeeFName("Catalin");
		emp6.setEmployeeLName("Neculce");
		emp6.setEmployeeChildren(2);
		emp6.setEmployeeSalary(1520.00);
		emp6.setEmployeePhone("0723412920");
		emp6.setEmployeeDep(finance);
		employeeList.add(emp6);

		// create bookstore, assigning book
		Company company = new Company();
		company.setCompanyName("IBM");
		company.setCompanyLocation("Munich");
		company.setEmployeesList(employeeList);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(Company.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(company, System.out);

		// Write to File
		m.marshal(company, new File(COMPANY_XML));

		// get variables from our xml file, created before
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = context.createUnmarshaller();
		Company comp2 = (Company) um.unmarshal(new FileReader(COMPANY_XML));
		ArrayList<Employee> list = comp2.getEmployeesList();
		for (Employee emp : list) {
			System.out.println("\n");
			System.out.println("-------------------------");
			System.out.println("Employee Information : ");
			System.out.println("-------------------------");
			System.out.println("ID : " + emp.getEmployeeId());
			System.out.println("Name : " + emp.getEmployeeFName() + " " + emp.getEmployeeLName());
			System.out.println("Phone Number : " + emp.getEmployeePhone());
			System.out.println("Number of Children : " + emp.getEmployeeChildren());
			System.out.println("Salary : " + emp.getEmployeeSalary());
			System.out.println("\t -------------------------");
			System.out.println("\t Department Information : ");
			System.out.println("\t -------------------------");
			System.out.println("\t     Department No. : " + emp.getEmployeeDep().getDepNo());
			System.out.println("\t     Department Name : " + emp.getEmployeeDep().getDepName());
			System.out.println("\t     Department Manager : " + emp.getEmployeeDep().getManagerName());
			System.out.println("\n\n");
		}
		
		
		return list;
		
	}
	
	/*public Employee addEmployee(ArrayList<Employee> emplist){
		Scanner reader = new Scanner(System.in);
		String id,fname,lname,phone,depname,depmanager;
		double salary;
		int nochildren,depid;
		
	}*/

}
