package native_jdbc_programing.ch01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathExTest { // 단위테스트 (기능(메소드) 하나하나 마다 테스트 하기)
	
	static private MathEx m;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		m= new MathEx();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		m =null;
	}

	@Before
	public void setUp() throws Exception {
		m.setA(5);
		m.setB(4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		int res = m.add();
		Assert.assertEquals(8, res);
	}

	@Test
	public void testSub() {
		int res = m.sub();
		Assert.assertEquals(2, res);
	}

}
