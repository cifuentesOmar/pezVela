import java.io.PrintStream;

public class SwarmPez {

    private final int pSF = 20; //20
    private final int pSD = 30; //30
    private final int T = 1000;
    private final int A = 4;
    private final double epsilon = 0.001;
    private final double PD = 1 - (pSF / (pSF + pSD));
    private double lamda;

    private java.util.List<Pez> swarmSF = null;
    private java.util.List<Pez> swarmSD = null;

    private Pez gSF = null;
    private Pez gSD = null;
    PrintStream fichero;

    public void execute(PrintStream ficheroMain) {
    	fichero = ficheroMain;
        init();
        evolve();
    }

    private void init() {
        swarmSF = new java.util.ArrayList<>();
        swarmSD = new java.util.ArrayList<>();

        gSF = new Pez();
        gSD = new Pez();

        Pez b;
        for (int i = 1; i <= pSF; i++) {
            do {
                b = new Pez();
            } while (!b.isFeasible()); //si no es factible se mantiene iterando
            swarmSF.add(b);
        }
        for (int i = 1; i <= pSD; i++) {
            do {
                b = new Pez();
            } while (!b.isFeasible()); //si no es factible se mantiene iterando
            swarmSD.add(b);
        }

        gSF.copy(swarmSF.get(0));
        for (int i = 1; i < pSF; i++) {
            if (swarmSF.get(i).isBetterThan(gSF)) {
                gSF.copy(swarmSF.get(i));
            }
        }

        gSD.copy(swarmSD.get(0));
        for (int i = 1; i < pSD; i++) {
            if (swarmSD.get(i).isBetterThan(gSD)) {
                gSD.copy(swarmSD.get(i));
            }
        }

        log(0);
    }

    private void evolve() {

        int t = 1;
        
        while (t <= T) {
            
            lamda = 2 * StdRandom.uniform(0,1) * PD - PD; //EQ (7)
            Pez p = new Pez();
        
            //actualización de pez espada
            
            
            int iFuera = 0;
            while (iFuera < pSF) {
            
            do {
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
            double ataque = StdRandom.uniform();
            Pez s = new Pez();
            
            
            if (ataque < 0.5) {
            	
                
                do {
                	alpha = (int) (pSD * ataque); //EQ(11)
	                betha = (int) (pSD * (1-ataque));   //EQ(12) 250 dimension
                	
                }while(alpha >= pSD || betha >= pSD || betha < alpha);
                
                int i = 0;
                
                while (i < pSD) { //puede ser for igual ya tengo la restricción
                    
                    do {
                    	
                    	s.copy(swarmSD.get(StdRandom.uniform(alpha,betha)));
                    	
                        p.SDmoveAlpha(A, epsilon, t, gSF, gSD, lamda, s, ataque);
                       

                    } while (!p.isFeasible());
                    
                    if (p.isBetterThan(gSD)) { //LO QUE ESTA EN SARDINA ES MEJOR QUE LO QUE ESTA EN PEZ VELA//Equation (10)???????????
                    	
                        gSD.copy(p); //COPIAR LO DE SARDINA A PEZ VELA (EL MEJOR) //Equation (10)???????????
                        
                    }
                    
                    swarmSD.get(i).copy(p);
                    
                    i++;
                    
                }

            } else {
            
                int j = 0;
                while (j < pSD) {
                    
                    do {
                        
                        p.SDmove(A, epsilon, t, gSF, gSD, lamda, ataque);
                        
                        p.copy(swarmSD.get(j));

                    } while (!p.isFeasible());
                    
                    if (p.isBetterThan(gSD)) {
                    	gSD.copy(p);
                    	StdOut.printf("gSD else %s", gSD);
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
        StdOut.printf("t=%d,\t%s\n", t, gSD); //aca muestra el objeto g mostrando el metodo toString formateado
        
        System.setOut(fichero);
		System.out.printf("t=%d,\t%s\n", t, gSD);
        
    }
}
