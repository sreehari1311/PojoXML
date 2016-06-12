import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;


public class GenEmployeePOJO {

	public static void main(String args[]){
 		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		//spcify type of the object containes in collection class
 		pojoXml.addCollectionClass("Address",Address.class);
 		Employee employee = (Employee)pojoXml.getPojoFromFile("C:\\employee.xml",Employee.class);
	  
 		System.out.println(employee.getName());
	}
}
