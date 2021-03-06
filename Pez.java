
public class Pez extends Problem {

	
    //VARIABLES DEL PROBLEMA
    
	private int[] x = new int[nVars];
    private int[] p = new int[nVars];
   
    // INICIALIZACIÓN DE PEZ

    public Pez() {
        for (int j = 0; j < nVars; j++) {
            x[j] = StdRandom.uniform(2);
        }

    }
    
    protected int computeFitness() {
        return computeFitness(x);
    }

    protected int computeFitnessPBest() {
        return computeFitness(p);
    }

    protected boolean isFeasible() {
        return checkConstraint(x);
    }

    protected boolean isBetterThanPBest() {
        return computeFitness() < computeFitnessPBest();
    }

    protected boolean isBetterThan(Pez g) {
        return computeFitness(x) < computeFitness(g.x);
    }

    
    private int toBinary(double x) {
		return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
	}
	
    // MOVIMIENTO DE PEZ VELA
    
    protected void SFmoveFuera(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda) {      
        for (int j = 0; j < nVars; j++) {
            x[j] = toBinary(gSF.x[j] - lamda * (StdRandom.uniform(0,1) * ((gSF.x[j]+ gSD.x[j])/2)- x[j])); // EQ(6)
        }
    }
    
    // MOVIMIENTO DE PEZ SARDINA
    
    protected void SDmove(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda, double ataque) {        
        
        for (int j = 0; j < nVars; j++) {
        	x[j] =  toBinary(StdRandom.uniform(0,1) * (gSF.x[j]-  gSD.x[j] + ataque));
        }
    }
    
    // MOVIMIENTO DE PEZ SARDINA CON ALFA Y BETA
    
    protected void SDmoveAlpha(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda, Pez s, double ataque) {
        
        for (int j = 0; j < nVars; j++) {
        	x[j] =  toBinary(StdRandom.uniform(0,1) * (s.x[j] - x[j] + ataque));
        }
    }

    protected void copy(Object object) {
        if (object instanceof Pez) {
            System.arraycopy(((Pez) object).x, 0, this.x, 0, nVars);
        }
    }

    private float diff() {
        return computeFitness(x) - optimum();
    }

    private float rpd() {
        return diff() / optimum() * 100f;
    }

    private String showSolution() {
        return java.util.Arrays.toString(x);
    }

    @Override
    public String toString() {
        return String.format("optimal value: %d, fitness: %d, diff: %.1f, rpd: %.2f%%, p: %s", optimum(),
                computeFitness(x), diff(), rpd(), showSolution());
    }
}
