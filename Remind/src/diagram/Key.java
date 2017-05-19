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

//key는 다시 한번 생각해보자.
