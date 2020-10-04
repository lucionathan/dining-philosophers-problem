public class DinnerWithMonitor extends Dinner
{

    public DinnerWithMonitor(int n_phi)
    {
        super(n_phi);
        this.philosophers = new Object[n_phi];

        for(int i = 0; i < this.n_philosophers; i++)
        {
            this.philosophersStates.add(states.THINKING);
            this.philosophers[i] = new Object();
        }
    }

    @Override
    public void take_cutlery(int p_id)
    {
        philosophersStates.set(p_id, states.HUNGRY);
        System.out.println(philosophersStates);

        synchronized(philosophers[p_id])
        {
            if (canEat(p_id)) philosophersStates.set(p_id, states.EATING);
            while (philosophersStates.get(p_id) != states.EATING)
            {
                try{
                    this.philosophers[p_id].wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(philosophersStates);
        }
    }

    @Override
    public void return_cutlery(int p_id)
    {
        philosophersStates.set(p_id, states.THINKING);
        System.out.println(philosophersStates);

        if (philosophersStates.get(getLeft(p_id)) == states.HUNGRY && canEat(getLeft(p_id)))
        {
            philosophersStates.set(getLeft(p_id), states.EATING);
            synchronized (philosophers[getLeft(p_id)])
            {
                philosophers[getLeft(p_id)].notify();
            }
        }

        if (philosophersStates.get(getRight(p_id)) == states.HUNGRY && canEat(getRight(p_id)))
        {
            philosophersStates.set(getRight(p_id), states.EATING);

            synchronized (philosophers[getRight(p_id)])
            {
                philosophers[getRight(p_id)].notify();
            }
        }
        System.out.println(philosophersStates);
    }

}