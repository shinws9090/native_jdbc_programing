package native_jdbc_programing.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programing.dao.impl.DepartmentDaoImpl;
import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();
			
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test05SelectDepartmentByAll() {
		System.out.printf("%s()%n","tests05electDepartmentByAll");
		List<Department> list =dao.selectDepartmentByAll();
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
//		for(Department a : list) {
//			System.out.println(a);
//		}
		
	}

	@Test
	public void test04SelectDepartmentByNo() {
		System.out.printf("%s()%n","testSelectDepartmentByNo");
		Department department = new Department(2);
		Assert.assertNotNull(department);
		System.out.println(dao.selectDepartmentByNo(department));
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n","testInsertDepartment");
		Department newDepartment = new Department(5,"보전",10);
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1,res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n","testUpdateDepartment");
		Department newDepartment = new Department(5,"생기");
		int res = dao.updateDepartment(newDepartment);
		Assert.assertEquals(1,res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n","testDeleteDepartment");
		Department newDepartment = new Department(5);
		int res = dao.deleteDepartment(newDepartment);
		Assert.assertEquals(1,res);
	}
	
	@Test
	public void test06selectDepartmentByDeptno() {
		System.out.printf("%s()%n","test06selectDepartmentByDeptno");
		Department newDepartment = new Department(2);
		List<Employee> list = dao.selectDepartmentByDeptno(newDepartment);
		for(Employee a : list) {
			System.out.println(a.toString2());
			
		}
	}

}
