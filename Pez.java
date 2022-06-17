
public class Pez extends Problem {
	
	private int[] x = new int[nVars];
	private double[] x_new = new double[nVars];
	private int[] p = new int[nVars];
	private double lambda = 0;
	
	
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

	protected boolean isBetterThanPBest() {
		return computeFitness() < computeFitnessPBest();
	}

	protected boolean isBetterThan(Pez g) {
		return computeFitnessPBest() < g.computeFitnessPBest();
	}
	
	protected boolean isFeasible() {
		return checkConstraint(x);
	}
	
	protected void updatePBest() {
		System.arraycopy(x, 0, p, 0, x.length);
	}
	
	
	protected void copy(Object object) {
		if (object instanceof Pez) {
			System.arraycopy(((Pez) object).x, 0, x, 0, nVars);
			((Pez) object).lambda = this.lambda;
		}
	}
	
	
	protected void move(int A, double PD, double epsilon, int t, Pez g, double lambda  ) {
		 
//		lambda = (2 * StdRandom.uniform(0,1) * PD) - PD;
		
		for (int j = 0; j < nVars; j++) {
			/* Actualizar posicion */
			x_new[j] = g.x[j] - lambda * (StdRandom.uniform(0,1) * (g.x[j]+ 1/2)- x[j]);
			x[j] = toBinary(x[j] + x_new[j]);
		}
		
		
	}
	
	@Override
	public String toString() {
		return String.format("optimal value: %d, fitness: %d, diff: %.1f, rpd: %.2f%%, p: %s", optimum(),
				computeFitness(x), diff(), rpd(), showSolution());
	}
	
	private String showSolution() {
		return java.util.Arrays.toString(x);
	}
	
	private float diff() {
		return computeFitness(x) - optimum();
	}

	private float rpd() {
		return diff() / optimum() * 100f;
	}
	
	private int toBinary(double x) {
		return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
	}

	
	

}
