
public class Pez extends Problem {
	
	private int[] x = new int[nVars];
	private int[] p = new int[nVars];
	private double lambda =0;
	
	
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
			System.arraycopy(((Pez) object).x, 0, this.x, 0, nVars);
			((Pez) object).lambda = this.lambda;
		}
	}
	
	
	protected void move(int A, double PD, double epsilon, int t) {
		 
		lambda = (2 * StdRandom.uniform() * PD) - PD;
		
		for (int j = 0; j < nVars; j++) {
			
			/* Actualizar velocidad */
//			v[j] = v[j] * theta + alpha * StdRandom.uniform() * (g.p[j] - x[j]) + beta * StdRandom.uniform() * (p[j] - x[j]);
			/* Actualizar posicion */
//			x[j] = toBinary(x[j] + v[j]);
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
	
	

}
