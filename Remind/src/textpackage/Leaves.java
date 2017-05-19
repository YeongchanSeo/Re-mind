package textpackage;

import java.util.Arrays;

public class Leaves {
	public String [] leaf;
	
	public Leaves(String a, String b, String c){
		leaf = new String[3];
		leaf[0]= a;
		leaf[1]= b;
		leaf[2]= c;
	}

	@Override
	public String toString() {
		return "Leaves [leaf=" + Arrays.toString(leaf) + "]";
	}
	
}
