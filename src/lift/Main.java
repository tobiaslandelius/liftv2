package lift;

public class Main {

	private static Monitor monitor;
	
	public static void main(String[] args) {
		monitor = new Monitor();
		new Lift(monitor).start();
		for (int i = 0; i < 2; i++) {
			new Person(monitor, i).start();
		}
	}
}
