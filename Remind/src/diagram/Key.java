package diagram;

public class Key {

	private static int key; 
	public static int getKey() {
		return key++;
	}

	private void makeKey() {
		key++;
	}
}

//key�� �ٽ� �ѹ� �����غ���.
