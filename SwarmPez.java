
public class SwarmPez {

    //variables
    private final int pSF = 100; //20
    private final int pSD = 400; //30
    private final int T = 1000;
    private final int A = 4;
    private final double epsilon = 0.01;
    private final double PD = 1 - (pSF / (pSF + pSD));
    private double lamda;

    //private java.util.List<Pez> swarm = null;
    private java.util.List<Pez> swarmSF = null;
    private java.util.List<Pez> swarmSD = null;

    private Pez gSF = null;
    private Pez gSD = null;

    public void execute() {
        init();
        evolve();
    }

    private void init() {
        //swarm = new java.util.ArrayList<>();
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
            lamda = 2 * StdRandom.uniform() * PD - PD;
            Pez p = new Pez();
            double ataque = A * (1 - (2 * t * epsilon)); // EQ 10 ataque

            if (ataque < 0.5) {
                
                double alpha=pSD*ataque;
                double betha=1*ataque;
                
                int i = 0;
                while (i < pSF) { //puede ser for igual ya tengo la restricción
                    
                           //ver donde va esta variable. 
                    do {

                        //p.SFmove(A, epsilon, t, gSF, gSD, lamda);
                        p.SFmove(A, epsilon, t, gSF, gSD, lamda, alpha);
                        p.copy(swarmSF.get(i));

                    } while (!p.isFeasible());
                    if (swarmSF.get(i).isBetterThan(gSF)) {
                        gSF.copy(swarmSF.get(i));
                    }
                    i++;
                }

            } else {
                int j = 0;
                while (j < pSD) {
                    
                    do {
                        
                        p.SDmove(A, epsilon, t, gSF, gSD, lamda, ataque);
                        p.copy(swarmSD.get(j));

                    } while (!p.isFeasible());
                    if (swarmSD.get(j).isBetterThan(gSD)) {
                        gSD.copy(swarmSD.get(j));
                    }
                    j++;
                }

                log(t);
                t++;
            }

        }
    }
    private void log(int t) {
        StdOut.printf("t=%d,\t%s\n", t, gSF); //aca muestra el objeto g mostrando el metodo toString formateado
    }
}
