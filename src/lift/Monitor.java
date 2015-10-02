package lift;

public class Monitor {

    private int here;
    private int next;
    private int direction;
    private int nmbrOfPersons;

    private int[] waitEntry;
    private int[] waitExit;
    private int load;
    private LiftView lv;

    public Monitor() {
        lv = new LiftView();
        here = 0;
        next = 1;
        direction = 1;
        waitEntry = new int[7];
        waitExit = new int[7];
        load = 0;
        nmbrOfPersons = 0;
    }

    private void drawLevel(int level) {
        lv.drawLevel(level, waitEntry[level]);
    }

    private void drawLift() {
        System.out.println(here+ " : " +load);
        lv.drawLift(here, load);
    }

    public synchronized void addPerson(int from, int to) {
        nmbrOfPersons++;
        waitEntry[from]++;
        drawLevel(from);
        notifyAll();
        try {
            while (here != from || load == 4 || waitExit[here] != 0) wait();
            load++;
            drawLift();
            --waitEntry[here];
            waitExit[to]++;
            drawLevel(here);
            notifyAll();

            while (here != to) wait();
            load--;
            drawLift();
            --waitExit[here];
            drawLevel(here);
            nmbrOfPersons--;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void newFloor() {
        notifyAll();
        while (waitExit[here] != 0 || (waitEntry[here] != 0 && load < 4) || nmbrOfPersons == 0)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void moveLift() {
        int from = here;
        here = -1;
        lv.moveLift(from, next);
        here = next;

        if (here == 0) {
            direction = 1;
        } else if (here == 6) {
            direction = -1;
        }
        next += direction;
    }
}
