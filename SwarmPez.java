
public class SwarmPez {
	
	private final int ps = 25;
	
	private java.util.List<Pez> swarm = null;
	private Pez g = null;
	
	public void execute() {
		init();
		evolve();
	}
	
	private void init() {
		swarm = new java.util.ArrayList<Pez>();
		
//		Se inicializa el primer Pez
		
		g = new Pez();
		
		Pez b;
		
		
		
//		Se inicializa cada pez y se busca que sea factible
		
		for(int i = 1; i <= ps; i++) {
			do {
				b = new Pez();	
			} while(!b.isFeasible());
			swarm.add(b);
		}
		
		StdOut.printf("Lleno el enjambre");
		
		
		g.copy(swarm.get(0));
		
//		Se verifica que sea el mejor pez
		
		for (int i = 1; i < ps; i++) {
			if (swarm.get(i).isBetterThan(g)) {
				g.copy(swarm.get(i));
			}
		}
		log(0);
	}
	
	private void evolve() {
	
	}
	
//	Mostrar en pantalla 
	
	private void log(int t) {
		StdOut.printf("t=%d,\t%s\n", t, g);
	}
	

}
