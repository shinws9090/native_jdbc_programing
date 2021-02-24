package native_jdbc_programing.dao;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programing.dao.impl.TitleDaoImpl;
import native_jdbc_programing.dto.Title;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 수행 순서 변경 설정 설정 후 >> 테스트메소드 이금 순서별로 변경하면됨
public class TitleDaoTest {
	private static TitleDao dao =TitleDaoImpl.getInstance(); 
	
	@After
	public void tearDown() throws Exception { //4
		System.out.println();
	}
	
	@Test
	public void test04SelectTitleByAll() {
		System.out.printf("%s()%n","testSelectTitleByAll");
		List<Title> list =dao.selectTitleByAll();
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println); // list메소드사용
//		for(Title a : list) {
//			System.out.println(a);
//		}
		
	}

	@Test
	public void test05SelectTitleByNo() {
		System.out.printf("%s()%n","testSelectTitleByNo");
		Title title = new Title(5);
		Title searchtitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchtitle);
		System.out.println(searchtitle);
	}

	@Test
	public void test01InsertTitle() {
		System.out.printf("%s()%n","testInsertTitle");
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test02UpdateTitle() {
		System.out.printf("%s()%n","testUpdateTitle");
		Title upTitle = new Title(6,"계약직");
		int res = dao.updateTitle(upTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(upTitle));
	}

	@Test
	public void test03DeleteTitle() {
		System.out.printf("%s()%n","testDeleteTitle");
		int res = dao.deleteTitle(6);
		Assert.assertEquals(1, res);
		dao.selectTitleByAll().stream().forEach(System.out::println);
	}

}
