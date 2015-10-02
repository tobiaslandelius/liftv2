package lift;

public class Person extends Thread {

    private Monitor monitor;
    private int i;

    public Person(Monitor monitor, int i) {
        this.monitor = monitor;
        this.i = i;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(delay());
                int[] floors = randomizeFloors();
                monitor.addPerson(floors[0], floors[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int delay() {
        return 1000 * ((int) (Math.random() * 46.0)); // Ändra 6 till 46 sekunder sen...
    }

    private int[] randomizeFloors() {
        int from, to;
        from = (int) (Math.random() * 7);
        do {
            to = (int) (Math.random() * 7);
        } while (from == to);
        return new int[]{from, to};
    }
}