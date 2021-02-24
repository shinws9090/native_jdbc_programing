package native_jdbc_programing.service;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	private static  TransactionService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TransactionServiceTest를 실행하기  전에 수행
		service = new TransactionService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//TransactionServiceTest를 실행한   후에 수행
		service = null;
	}

	@After
	public void tearDown() throws Exception {
		//각각의 메소드가 끝날대마다 호출
		System.out.println();
	}

	@Test
	public void test01TransAddTitlAndDepartment_FailTitle() {
		System.out.println("testTransAddTitlAndDepartment_FailTitle ()");
		
		Title title = new Title(1, "인턴");
		Department dept	= new Department(5, "비상", 10);
		String res =service.transAddTitlAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	public void test02TransAddTitlAndDepartment_FailDept() {
		System.out.println("testTransAddTitlAndDepartment_FailDept ()");
		
		Title title = new Title(6, "인턴");
		Department dept	= new Department(1, "비상", 10);
		String res =service.transAddTitlAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	public void test03TransAddTitlAndDepartment_FailBoth() {
		System.out.println("testTransAddTitlAndDepartment_FailBoth ()");
		
		Title title = new Title(1, "인턴");
		Department dept	= new Department(1, "비상", 10);
		String res =service.transAddTitlAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	public void test04TransAddTitlAndDepartment_Success() {
		System.out.println("testTransAddTitlAndDepartment_Success ()");
		
		Title title = new Title(6, "인턴");
		Department dept	= new Department(5, "비상", 10);
		String res =service.transAddTitlAndDepartment(title, dept);
		Assert.assertEquals("commit", res);
	}
//---------------------------------------------------------------------------
	@Test
	public void test05TransRemoveTitleAndDepartment_FailTitle() {
		System.out.println("testTransRemoveTitleAndDepartment_FailTitle ()");
		
		Title title = new Title(0);
		Department dept	= new Department(5);
		int res =service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);
	}
	@Test
	public void test06TransRemoveTitleAndDepartment_FailDept() {
		System.out.println("testTransRemoveTitleAndDepartment_FailDept ()");
		
		Title title = new Title(6);
		Department dept	= new Department(0);
		int res =service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);
	}
	@Test
	public void test07TransRemoveTitleAndDepartment_FailBoth() {
		System.out.println("testTransRemoveTitleAndDepartment_FailBoth ()");
		
		Title title = new Title(0);
		Department dept	= new Department(0);
		int res =service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(0, res);
	}
	@Test
	public void test08TransRemoveTitleAndDepartment_Success() {
		System.out.println("test08TransRemoveTitleAndDepartment_Success ()");
		
		Title title = new Title(6);
		Department dept	= new Department(5);
		int res =service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(2, res);
	}

}
