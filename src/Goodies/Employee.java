package Goodies;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlType(propOrder = { "employeeId", "employeeFName", "employeeLName", "employeeDep", "employeePhone",
		"employeeChildren", "employeeSalary" })
public class Employee implements Serializable{

	protected String employeeId;
	protected String employeeFName;
	protected String employeeLName;
	protected String employeePhone;
	protected int employeeChildren;
	protected double employeeSalary;
	protected Departament employeeDep;

	public Employee(String employeeId, String employeeFName, String employeeLName, String employeePhone,
			int employeeChildren, double employeeSalary, Departament employeeDep) {
		super();
		this.employeeId = employeeId;
		this.employeeFName = employeeFName;
		this.employeeLName = employeeLName;
		this.employeePhone = employeePhone;
		this.employeeChildren = employeeChildren;
		this.employeeSalary = employeeSalary;
		this.employeeDep = employeeDep;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlElement(name = "ID")
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@XmlElement(name = "FirstName")
	public String getEmployeeFName() {
		return employeeFName;
	}

	public void setEmployeeFName(String employeeFName) {
		this.employeeFName = employeeFName;
	}

	@XmlElement(name = "LastName")
	public String getEmployeeLName() {
		return employeeLName;
	}

	public void setEmployeeLName(String employeeLName) {
		this.employeeLName = employeeLName;
	}

	@XmlElement(name = "Phone")
	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	@XmlElement(name = "Children")
	public int getEmployeeChildren() {
		return employeeChildren;
	}

	public void setEmployeeChildren(int employeeChildren) {
		this.employeeChildren = employeeChildren;
	}

	@XmlElement(name = "Salary")
	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	@XmlElement(name = "Department")
	public Departament getEmployeeDep() {
		return employeeDep;
	}

	public void setEmployeeDep(Departament employeeDep) {
		this.employeeDep = employeeDep;
	}

	@Override
	public String toString() {
		return "\n"+
		"-------------------------"+"\n"+
		"Employee Information : "+"\n"+
		"-------------------------"+"\n"+
		"ID : " + getEmployeeId()+"\n"+
		"Name : " + getEmployeeFName() + " " + getEmployeeLName()+"\n"+
		"Phone Number : " + getEmployeePhone()+"\n"+
		"Number of Children : " + getEmployeeChildren()+"\n"+
		"Salary : " + getEmployeeSalary()+"\n"+
		"\t -------------------------"+"\n"+
		"\t Department Information : "+"\n"+
		"\t -------------------------"+"\n"+
		"\t     Department No. : " + getEmployeeDep().getDepNo()+"\n"+
		"\t     Department Name : " + getEmployeeDep().getDepName()+"\n"+
		"\t     Department Manager : " + getEmployeeDep().getManagerName()+"\n"+
		"\n\n";
	}

	
}
