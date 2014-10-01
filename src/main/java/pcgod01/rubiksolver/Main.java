package main.java.pcgod01.rubiksolver;

public class Main {
	public static final void main(String[] args) {
		if(args.length < 1) {
			throw new IllegalArgumentException("Needs path to cube file");
		}
		
		String scrambledPath = args[0];
	}
}
