public class Problem {

	private final int[] weight = {49, 45, 86, 74, 64, 73, 93, 34, 97, 80, 24, 87, 100, 75, 89,
			78,46,31,68,63,78, 28, 96, 54, 64, 31, 65, 90, 41, 47, 71, 51, 63, 44,93, 46, 46, 
			83, 68, 57, 89, 35, 99, 39, 24, 69, 64, 25, 85, 65, 81, 61, 40, 64, 88, 43, 99, 
			53, 98, 70, 38, 75, 23, 80, 72, 97, 89, 80, 38, 30, 34, 22, 61, 48, 22, 28, 99, 
			55, 89, 67, 24, 27, 91, 90, 20, 36, 77, 44, 24, 60, 96, 83, 53, 76, 27, 91, 58, 
			78, 23, 31, 99, 42, 64, 39, 73, 43, 36, 76, 97, 41, 90, 24, 82, 55, 93, 63, 61, 
			39, 73, 54, 77, 100, 46, 69, 74, 41, 32, 56, 68, 98, 61, 28, 21, 30, 47, 43, 54, 
			33, 31, 38, 49, 40, 44, 93, 20, 81, 71, 36, 71, 36, 42, 56, 85, 23, 86, 88, 95, 
			61, 41, 34, 74, 37, 82, 30, 98, 86, 37, 93, 100, 69, 25, 54, 47, 58, 50, 87, 90, 
			45, 71, 70, 38, 49, 42, 33, 78, 48, 94, 99, 100, 84, 91, 27, 69, 52, 64, 99, 30, 
			34, 55, 96, 92, 48, 88, 76, 38, 73, 90, 99, 45, 84, 94, 82, 28, 35, 94, 100, 44, 
			23, 58, 23, 35, 84, 75, 30, 58, 61, 61, 100, 63, 99, 85, 60, 78, 56, 76, 61, 59, 
			93, 83, 84, 89, 59, 75, 32, 21, 62, 27, 64, 44, 83};
	
	private final int capacity = 150;
	protected final int nVars = 250;

	protected int optimum() {
		return 104;
	}

//	No tocar esta funcion

	protected boolean checkConstraint(int[] x) {
		int chosenBin = computeFitness(x);
		if (chosenBin == 0)
			return false;

		int[] remaining = new int[chosenBin];
		for (int i = 0; i < chosenBin; i++)
			remaining[i] = capacity;
		
		if (remaining[0] < weight[0])
			return false;

		remaining[0] -= weight[0];
		for (int j = 1, i = 0; j < nVars; j++) {
			if (remaining[i] - weight[j] >= 0) {
				remaining[i] -= weight[j];
				i = 0;
			} else {
				i++;
				if (i == chosenBin)
					return false;
				j--;
			}
		}
		return true;
	}

	protected int computeFitness(int[] bin) {
		return java.util.Arrays.stream(bin).sum();
	}
}