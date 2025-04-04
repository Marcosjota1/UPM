package aed.urgencias;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
	@Test
	public void testAdmitir() throws PacienteExisteException {
		Urgencias u = new UrgenciasAED();
		u.admitirPaciente("111", 5, 1);
		Paciente p = u.atenderPaciente(10);
		// Check expected DNI ("111") equals observed DNI (p.getDNI())
		assertEquals("111", p.getDNI());
	}
	public void test01() throws PacienteExisteException
	{
	Urgencias u = new UrgenciasAED();
	u.admitirPaciente("111", 5, 1);
	u.admitirPaciente("222", 5, 2);
	Paciente p1 = u.atenderPaciente(10); //Debe devolver P1
	assertEquals("111", p1.getDNI());
	}
	@Test
	public void Test02() throws PacienteExisteException
	{
		
	Urgencias u = new UrgenciasAED();
	u.admitirPaciente("111", 5, 1);
	u.admitirPaciente("222", 5, 2);
	Paciente p1 = u.atenderPaciente(10); //1º llamada devuelve P1
	Paciente p2 = u.atenderPaciente(10); //2º llamada devuelve P2
	assertEquals("111",p1.getDNI());
	assertEquals("222",p2.getDNI());
	}
	@Test
	public void Test03() throws PacienteExisteException
	{
	Urgencias u = new UrgenciasAED();
	u.admitirPaciente("111", 5, 1);
	u.admitirPaciente("222", 1, 5);
	Paciente p2 = u.atenderPaciente(10); //devuelve P2, prioridad menor
	assertEquals("222",p2.getDNI());
	}
	@Test
	public void Test04() throws PacienteExisteException, PacienteNoExisteException
	{
	Urgencias u = new UrgenciasAED();
	u.admitirPaciente("111", 5, 1);
	u.admitirPaciente("222", 5, 2);
	u.salirPaciente("111",0); //No usa hora para nada, RECORDATORIO mirar luego
	Paciente p2 = u.atenderPaciente(10);
	assertEquals("222",p2.getDNI());
	}
	@Test
	public void Test05() throws PacienteExisteException, PacienteNoExisteException
	{
	Urgencias u = new UrgenciasAED();
	u.admitirPaciente("111", 5, 3);
	u.admitirPaciente("222", 5, 1); //Revisar test5 *RECORDATORIO
	u.cambiarPrioridad("222", 1, 0);
	Paciente p2 =u.atenderPaciente(10);
	assertEquals("222",p2.getDNI());
	}
}