
public class SwarmPez {
	
	private final int ps = 25;
	private final int T = 1000;
	private final int A = 4;
	private final double epsilon = 0.01;
	
	
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
				
//				Aca va el move 
				
				b = new Pez();	
			} while(!b.isFeasible());
			swarm.add(b);
		}
		
//		StdOut.printf("Lleno el enjambre");
		
		
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
		
		int t = 1;
		while (t <= T) {
			Pez p = new Pez();
			for (int i = 0; i < ps; i++) {
				do {
					p.copy(swarm.get(i));
					
					var PD = 1-(ps /(ps+250));
					
					p.move(A, PD , epsilon, t);
				} while (!p.isFeasible());
				if (p.isBetterThan())
					p.updatePBest();
				swarm.get(i).copy(p);
			}
			for (int i = 0; i < ps; i++)
				if (swarm.get(i).isBetterThan(g))
					g.copy(swarm.get(i));

			log(t);
			t++;
		}
	
	}
	
//	Mostrar en pantalla 
	
	private void log(int t) {
		StdOut.printf("t=%d,\t%s\n", t, g);
	}
	

}
