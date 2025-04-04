package test;

import org.junit.*;

import racionales.MCD;

public class TestMCD {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("---- Empezando a hacer los test ----");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("---- He terminado de hacer los test ----");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("++ Empiezo un test ++");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("++ Termino un test ++");		
	}

	@Test
	public void testUno() {
		int a = 24;
		int b = 68;
		int y = MCD.calcula(a, b);
		Assert.assertEquals(y, 4);
	}

	@Test
	public void testDos() {
		int a = 175;
		int b = 150;
		int y = MCD.calcula(a, b);
		Assert.assertEquals(y, 25);
	}
}
