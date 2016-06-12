import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;


public class GenEmployeePOJO {

	public static void main(String args[]){
 		PojoXml pojoxml = PojoXmlFactory.createPojoXml();
 	 	Employee employee = (Employee)pojoxml.getPojoFromFile("C:\\employee.xml",Employee.class);
	  
 		System.out.println(employee.getName());
	}
}
