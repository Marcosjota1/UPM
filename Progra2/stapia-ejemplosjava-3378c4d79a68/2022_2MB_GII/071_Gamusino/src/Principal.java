import mk4g.*;

public class Principal {

	public static void main(String[] args) {
		Hormiguero h = new Hormiguero(300);
		FamiliaGamusinos fg1 = new FamiliaGamusinos();
		FamiliaGamusinos fg2 = new FamiliaGamusinos();
		
		h.recibirVisita(fg1);
		System.out.println(h);
		
		h.recibirVisita(fg2);
		System.out.println(h);
	}
}
