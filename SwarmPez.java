
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
					
					var lambda = (2 * StdRandom.uniform(0,1) * PD) - PD;
					
					p.move(A, PD , epsilon, t, g, lambda);
					
					double ap = A *(1-(2 * t * epsilon)); // EQ 10
					
//					StdOut.printf("ap=%.2f", ap);
					
					if(Math.abs(ap) < 0.5){						
						
						 int alfa = (int) (250 * ap); // ns es 250?
						 int beta = (int) (1 * ap); // Es una dimensión di
						 
//						 StdOut.printf("alhpa=%d\n", alfa);
						 
						 
						 var aleatorio = StdRandom.uniform(0,250);
						 
//						 StdOut.printf("ran=%d\n", aleatorio);
						 
//						 var list1 = StdRandom.uniform(250 , alfa); // set de sardinas es igual a ?
						 
//						 list1 = StdRandom.uniform(0, 1)*(g.x[1] - list1 + ap);
						 
					}else {
						
//						 list1 = StdRandom.uniform(0, 1)*(g.x[1] - list1 + ap);
						
					}
					
					
					
					
				} while (!p.isFeasible());
				if (p.isBetterThanPBest()) // CUAL SE OCUPA ISBETTERTHAN O isBetterThanPBest 
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
