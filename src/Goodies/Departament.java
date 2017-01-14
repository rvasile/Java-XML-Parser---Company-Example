package Goodies;


import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Departament")
@XmlType(propOrder = { "depNo", "depName", "managerName" })
public class Departament implements Serializable{
	
	private int depNo;
	private String depName;
	private String managerName;

	public Departament(int depNo, String depName, String managerName) {
		super();
		this.depNo = depNo;
		this.depName = depName;
		this.managerName = managerName;
	}

	public Departament() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlElement(name = "No")
	public int getDepNo() {
		return depNo;
	}

	public void setDepNo(int depNo) {
		this.depNo = depNo;
	}

	@XmlElement(name = "Name")
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@XmlElement(name="Manager")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}

