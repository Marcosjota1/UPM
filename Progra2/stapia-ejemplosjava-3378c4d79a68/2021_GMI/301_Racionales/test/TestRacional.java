package test;

import org.junit.*;

import racionales.Racional;

public class TestRacional {
	
	Racional[] sumandos1 = {
		new Racional(4, 5),
		new Racional(-5, 6),
		new Racional(-10, 13)		
	};
	
	Racional[] sumandos2 = {
			new Racional(6, 7),
			new Racional(8, 5),
			new Racional(4, 12)		
		};
	
	Racional[] resultados = {
			new Racional(58,35),
			new Racional(23, 30),
			new Racional(4*13-10*12, 12*13)		
		};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		Racional r = new Racional(3,5);
		Assert.assertTrue("Tiene que ser 3/5", r.toString().equals("3/5"));

		r = new Racional(21,35);
		Assert.assertTrue("21/35 tiene que ser 3/5", r.toString().equals("3/5"));

		r = new Racional(21,-35);
		Assert.assertTrue("21/-35 tiene que ser -3/5", r.toString().equals("3/5"));
	}

	@Test
	public void testSumar() {
		for ( int i = 0; i < sumandos1.length; ++i ) {
			Racional suma = Racional.sumar(sumandos1[i], sumandos2[i]);
			boolean estaBien = suma.toString().equals(resultados[i].toString());
			Assert.assertTrue(estaBien);
		}
	}

}
