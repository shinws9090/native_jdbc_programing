package native_jdbc_programing.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programing.dao.impl.EmployeeDaoImpl;
import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;
import native_jdbc_programing.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	EmployeeDao dao = EmployeeDaoImpl.getInstance();
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test05SelectEmployeeByAll() {
		System.out.printf("%s()%n","test05SelectEmployeeByAll");
		List<Employee>list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectEmployeeByNo() {
		System.out.printf("%s()%n","test04SelectEmployeeByNo");
		Employee employee = new Employee(1003);
		Assert.assertNotNull(employee);
		System.out.println(dao.selectEmployeeByNo(employee));
	}

	@Test
	public void test01InsertEmployee() {
		System.out.printf("%s()%n","test01InsertEmployee");

		
		Employee employee = new Employee(8888,"신범건 ",new Title(2),new Employee(1003),5000000,new Department(2));
		int res =dao.insertEmployee(employee);
		
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(employee));
		
	}

	@Test
	public void test02UpdateEmployee() {
		System.out.printf("%s()%n","test02UpdateEmployee");
		
		Employee employee = new Employee(8888,"무명 ");
		int res = dao.updateEmployee(employee);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(employee));

	}

	@Test
	public void test03DeleteEmployee() {
		System.out.printf("%s()%n","test03DeleteEmployee");
		
		Employee employee = new Employee(8888);
		int res = dao.deleteEmployee(employee);
		Assert.assertEquals(1, res);
		
	}

	@Test
	public void test06SelectJoinEmployeeByAllSimple() {
		System.out.printf("%s()%n","test06SelectJoinEmployeeByAllSimple");
		String sql = "select * from vw_employee";
		List<Employee>list = dao.selectJoinEmployeeByAllSimple(sql);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	

}
