import java.util.concurrent.Semaphore;

public class DinnerWithSemaphore extends Dinner
{
    private Semaphore mutex;
    public DinnerWithSemaphore(int n_phi)
    {
        super(n_phi);
        this.mutex = new Semaphore(1);
        this.philosophers = new Semaphore[this.n_philosophers];

        for(int i = 0; i < this.n_philosophers; i++)
        {
            this.philosophersStates.add(states.THINKING);
            this.philosophers[i] = new Semaphore(0);
        }
    }

    @Override
    public void take_cutlery(int p_id)
    {
        try{
            mutex.acquire();
            System.out.println(philosophersStates);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }

        this.philosophersStates.set(p_id, states.HUNGRY);

        if(canEat(p_id)){
            ((Semaphore) philosophers[p_id]).release();
            philosophersStates.set(p_id, states.EATING);
        }
        mutex.release();
        try{
            ((Semaphore) philosophers[p_id]).acquire();
            System.out.println(philosophersStates);
        } catch (InterruptedException e){
            System.out.println("Interrupted Exception");
        }
    }

    @Override
    public void return_cutlery(int p_id)
    {
        try{
            mutex.acquire();
            System.out.println(philosophersStates);
        } catch (InterruptedException e){
            System.out.println("Interrupted Exception");
        }
        philosophersStates.set(p_id, states.THINKING);

        if (philosophersStates.get(getRight(p_id)) == states.HUNGRY && canEat(getRight(p_id))) {
            philosophersStates.set(getRight(p_id), states.EATING);
            ((Semaphore) philosophers[getRight(p_id)]).release();
        }
        if (philosophersStates.get(getLeft(p_id)) == states.HUNGRY && canEat(getLeft(p_id))) {
            philosophersStates.set(getLeft(p_id), states.EATING);
            ((Semaphore) philosophers[getLeft(p_id)]).release();
        }
        System.out.println(philosophersStates);
        mutex.release();
    }
}