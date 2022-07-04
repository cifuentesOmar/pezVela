import java.io.PrintStream;

public class SwarmPez {

	// DECLARACION DE VARIABLES
	
    private final int pSF = 20; // PEZ VELA
    private final int pSD = 30; // PEZ SARDINA
    private final int T = 1000;
    private final int A = 4;
    private final double epsilon = 0.001;
    private final double PD = 1 - (pSF / (pSF + pSD));
    private double lamda;

	// DECLARACION DE ENJAMBRES
    
    private java.util.List<Pez> swarmSF = null;
    private java.util.List<Pez> swarmSD = null;

	// DECLARACION DE PEZ
    
    private Pez gSF = null; // PEZ VELA
    private Pez gSD = null; // PEZ SARDINA
   
    PrintStream fichero;

    public void execute(PrintStream ficheroMain) {
    	fichero = ficheroMain;
        init();
        evolve();
    }
    
	// INICIALIZACIÓN DEL MÉTODO

    private void init() {
    	
        swarmSF = new java.util.ArrayList<>();
        swarmSD = new java.util.ArrayList<>();

        gSF = new Pez();
        gSD = new Pez();

        Pez b;
        
        for (int i = 1; i <= pSF; i++) {
            do {
                b = new Pez();
            } while (!b.isFeasible()); // VERIFICAR SI EL PEZ VELA ES FACTIBLE
            swarmSF.add(b);
        }
        
        for (int i = 1; i <= pSD; i++) {
            do {
                b = new Pez();
            } while (!b.isFeasible()); // VERIFICAR SI EL PEZ SARDINA ES FACTIBLE
            swarmSD.add(b);
        }
        
        // COPIA EN EL ENAMBRE EL MEJOR PEZ VELA   

        gSF.copy(swarmSF.get(0));
        for (int i = 1; i < pSF; i++) {
            if (swarmSF.get(i).isBetterThan(gSF)) {
                gSF.copy(swarmSF.get(i));
            }
        }
        
        // COPIA EN EL ENAMBRE LA MEJOR SARDINA

        gSD.copy(swarmSD.get(0));
        for (int i = 1; i < pSD; i++) {
            if (swarmSD.get(i).isBetterThan(gSD)) {
                gSD.copy(swarmSD.get(i));
            }
        }

        log(0);
    }
    
    // EVOLUCIÓN DEL MÉTODO

    private void evolve() {

        int t = 1;
        
        while (t <= T) {
            
            lamda = 2 * StdRandom.uniform(0,1) * PD - PD; //EQ (7)
            
            Pez p = new Pez();
            
            int iFuera = 0;
            
            while (iFuera < pSF) {
            
	            do {
	            	
	                // MOVIMIENTO DEL PEZ VELA
	            	
	                p.SFmoveFuera(A, epsilon, t, gSF, gSD, lamda); //EQ(6) 
	                p.copy(swarmSF.get(iFuera));
		
		            } while (!p.isFeasible());
		            if (swarmSF.get(iFuera).isBetterThan(gSF)) {
		                gSF.copy(swarmSF.get(iFuera));
		            }
		
	             iFuera++;
            }
            
            int alpha;
            int betha;
            
            // CALCULO DE ATAQUE
            
            double ataque;
            ataque =  A * (1 - (2 * t * epsilon)); // EQ 10
            
            Pez s = new Pez();
                        
            if (ataque < 0.5 && ataque > 0) {
            	
                do {
                	alpha = (int) (pSD * ataque); // EQ(11)
	                betha = (int) (pSD * (1-ataque));   // EQ(12)
                	
                } while(alpha >= pSD || betha >= pSD || betha < alpha);
                
                int i = 0;
                
                while (i < pSD) {
                    
                    do {
                    	
                        // MOVIMIENTO DE PEZ SARDINA DE ALFA Y BETA
                    	
                    	s.copy(swarmSD.get(StdRandom.uniform(alpha,betha)));
                        p.SDmoveAlpha(A, epsilon, t, gSF, gSD, lamda, s, ataque);
                       

                    } while (!p.isFeasible());
                    
                    // COMPROBAR SI EL PEZ P ES MEJOR QUE EL PEZ SARDINA
                    
                    if (p.isBetterThan(gSD)) {
                        gSD.copy(p); 
                    }
                    
                    swarmSD.get(i).copy(p);
                    
                    i++;
                    
                }

            } else {
            
                int j = 0;
                
                while (j < pSD) {
                    
                    do {
                        
                        // MOVIMIENTO DE PEZ SARDINA
                    	
                        p.SDmove(A, epsilon, t, gSF, gSD, lamda, ataque);
                        p.copy(swarmSD.get(j));

                    } while (!p.isFeasible());
                    
                    // COMPROBAR SI EL PEZ P ES MEJOR QUE EL PEZ SARDINA
                    
                    if (p.isBetterThan(gSD)) {
                    	gSD.copy(p);
                    }
                    
                    swarmSD.get(j).copy(p);
                    
                    j++;
                }
                
            }
            
            log(t);
            t++;
            
           }

        }
    
    private void log(int t) {
        StdOut.printf("t=%d,\t%s\n", t, gSD); // RESULTADO POR CONSOLA
        
        System.setOut(fichero);
		System.out.printf("t=%d,\t%s\n", t, gSD);
        
    }
}
