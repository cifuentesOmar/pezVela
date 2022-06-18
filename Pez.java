public class Pez extends Problem {



    //VARIABLES DEL PROBLEMA
    private int[] x = new int[nVars];
    private int[] p = new int[nVars];
   

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
        return computeFitness() < computeFitnessPBest(); //
    }

    protected boolean isBetterThan(Pez g) {
        return computeFitness(x) < computeFitness(g.x); //compara solucion que tiene x y la va comparar con la de g
    }

    
    private int toBinary(double x) {
		return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
	}
	
    
    protected void SFmoveFuera(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda) {
      
        //int i_sar= (int)alplha;
        
        for (int j = 0; j < nVars; j++) {
        //for (int j = 0; j < i_sar; j++) {

            x[j] = toBinary(gSF.x[j] - lamda * (StdRandom.uniform(0,1) * ((gSF.x[j]+ gSD.x[j])/2)- x[j]));

        }
    }
    
    
    protected void SFmove(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda, double alplha) {
      
        //int i_sar= (int)alplha;
        
        for (int j = 0; j < nVars; j++) {
        //for (int j = 0; j < i_sar; j++) {

            x[j] = toBinary(gSF.x[j] - lamda * (StdRandom.uniform(0,1) * ((gSF.x[j]+ gSD.x[j])/2)- x[j]));

        }
    }
    
    protected void SDmove(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda, double ataque) {

        
        
        for (int j = 0; j < nVars; j++) {
         
             x[j] =  toBinary(StdRandom.uniform(0,1) * (gSF.x[j]-  gSD.x[j] + ataque));

        }
    }
    
    
    protected void SDmoveAlpha(int A,  double epsilon, int t, Pez gSF , Pez gSD, double lamda, double ataque, double alpha) {

       int i_sar= (int)alpha;

        for (int j = 0; j < i_sar; j++) {
        //for (int j = 0; j < nVars; j++) {
         
             x[j] =  toBinary(StdRandom.uniform(0,1) * (gSF.x[j]-  gSD.x[j] + ataque));

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
