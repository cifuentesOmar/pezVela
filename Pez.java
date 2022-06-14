
public class Pez extends Problem {
	
	private int[] x = new int[nVars];
	private int[] r = new int[nVars];
	private int[] p = new int[nVars];
	
	public Pez() {
		for (int j = 0; j < nVars; j++) {
			x[j] = StdRandom.uniform(2);
			r[j] = StdRandom.uniform(2);
			p[j] = StdRandom.uniform(2);
		
		}
	}
	
	protected boolean isFeasible() {
		return checkConstraint(x);
	}
	
	protected boolean isBetterThan(Pez g) {
		return computeFitness(x) < computeFitness(g.x);
	}
	
	protected void copy(Object object) {
		if (object instanceof Pez) {
			System.arraycopy(((Pez) object).x, 0, this.x, 0, nVars);
//			System.arraycopy(((Pez) object).f, 0, this.f, 0, nVars);
//			System.arraycopy(((Pez) object).v, 0, this.v, 0, nVars);
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
