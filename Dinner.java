import java.util.ArrayList;

public abstract class Dinner
{
    protected enum states {THINKING, HUNGRY, EATING;};
    protected ArrayList<states> philosophersStates;
    protected Object[] philosophers;
    protected int n_philosophers;

    public Dinner(int n_philosophers)
    {
        this.n_philosophers = n_philosophers;
        this.philosophersStates = new ArrayList<>(n_philosophers);
    }

    protected int getRight (int position) {
        return (position + 1) % n_philosophers;
    }

    protected int getLeft (int position) {
        return (position + n_philosophers - 1) % n_philosophers;
    }

    protected void take_cutlery(int p_id){};

    protected void return_cutlery(int p_id){};

    protected boolean canEat (int p_id) {
        return (philosophersStates.get(getRight(p_id)) != states.EATING &&
                philosophersStates.get(getLeft(p_id)) != states.EATING);
    }
    
}