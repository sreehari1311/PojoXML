import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;

public class GenEmployeeXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PojoXml pojoxml = PojoXmlFactory.createPojoXml();

		Employee employee = new Employee();
		Address address = new Address();
		
 		employee.setName("Mike");
		employee.setAge(45);
		employee.setSalary(20000.45);
		address.setAddress1("Sagar");
		address.setAddress2("Nerul");
		address.setCity("Mumbai");
		address.setZip(002345);
		employee.setAddress(address);
		String xml = pojoxml.getXml(employee);
		System.out.println(xml);

	}

}
