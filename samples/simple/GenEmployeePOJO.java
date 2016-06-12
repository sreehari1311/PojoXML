import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;


public class GenEmployeePOJO {

	public static void main(String args[]){
 		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		
		 pojoXml.addClassAlias(Employee.class, "EmployeeInfo");
 	 	Employee employee = (Employee)pojoXml.getPojoFromFile("C:\\employee.xml",Employee.class);
	 	
	 	System.out.println(employee.getName());
	}
}
