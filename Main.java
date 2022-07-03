import java.io.File;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {

	static PrintStream fichero;
	
	public static void main(String[] args) {
		try {
			
			try {
				fichero = new PrintStream(new File("fichero.txt"));
				for(int r = 1; r <= 30; r++) {
					StdRandom.newSeed();
					new SwarmPez().execute(fichero);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			StdOut.printf("%s\n%s", e.getMessage(), e.getCause());
		}
	}
} 
