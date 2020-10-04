public class Philosopher implements Runnable {

    private int id;
    private int thinkingDuration;
    private int eatingDuration;
    private Dinner dinner;

    public Philosopher(int id, int thinkingDuration, int eatingDuration, Dinner dinner) {
        this.id = id;
        this.thinkingDuration = thinkingDuration;
        this.eatingDuration = eatingDuration;
        this.dinner = dinner;
        new Thread((Runnable)this, "Philosopher" + id).start();
    }

    @Override
    public void run() {
        while(true) {
            think();
            take_cutlery();
            eat();
            return_cutlery();
        }
    }

    private void think () {
        try {
            Thread.sleep(this.thinkingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void take_cutlery () {
        dinner.take_cutlery(this.id);
    }

    private void eat () {
        try {
            Thread.sleep(this.eatingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void return_cutlery () {
        dinner.return_cutlery(this.id);
    }
}