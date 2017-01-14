package Goodies;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(namespace = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
		
        // XmLElementWrapper generates a wrapper element around XML representation
		@XmlElementWrapper(name="Employees")
       
        // XmlElement sets the name of the entities
        @XmlElement(name = "Employee")
        private ArrayList<Employee> employeesList;
        private String companyName;
        private String companyLocation;

        public void setEmployeesList(ArrayList<Employee> employeesList) {
                this.employeesList = employeesList;
        }
        

        public ArrayList<Employee> getEmployeesList() {
                return employeesList;
        }
        
        public String getCompanyName() {
                return companyName;
        }

        public void setCompanyName(String name) {
                this.companyName = name;
        }

        public String getCompanyLocation() {
                return companyLocation;
        }

        public void setCompanyLocation(String location) {
                this.companyLocation = location;
        }
        
        public String toString() {
            String results = "\n +";
            for(Employee d : employeesList) {
                results += d.toString(); //if you implement toString() for Dog then it will be added here
            }
            return results;
          }
        
        
}