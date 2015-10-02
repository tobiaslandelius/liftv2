package lift;

public class Lift extends Thread {

    private Monitor monitor;

    public Lift(Monitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            monitor.moveLift();
            monitor.newFloor();
        }
    }
}
