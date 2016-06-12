import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;


public class GenEmployeePOJO {

	public static void main(String args[]){
 		PojoXml pojoxml = PojoXmlFactory.createPojoXml();
 		//specifying alias name
 		pojoxml.addClassAlias(Employee.class, "EmployeeInfo");
		pojoxml.addFieldAlias(Employee.class, "name", "employeeName");
		pojoxml.addFieldAlias(Employee.class, "address","employeeAddress");
		pojoxml.addClassAlias(Address.class, "employeeAddess");
		pojoxml.addFieldAlias(Address.class, "address2", "townName");
		//spcify type of the object containes in collection class
		pojoxml.addCollectionClass("employeeAddess",Address.class);
 		Employee employee = (Employee)pojoxml.getPojoFromFile("C:\\employee.xml",Employee.class);
	  
 		System.out.println(employee.getName());
	}
}
