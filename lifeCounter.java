
class LifeCouterThread extends Thread {
    private static int life;
    public boolean changeLife = true;
    public static int howChangeLife = 0;//select which function to use

    private void setLife(int x){
        life = x;
    }
    private void decrementLife(){
        life--;
    }
    public int whatIsLife(){
        return life;
    }
    public void selectHowChangeLife(int x){
        howChangeLife = x;
    }
    @Override
    public void run(){
        while (changeLife){
            try{
                switch (howChangeLife) {//switch case for function implementation
                    case 1:
                        setLife(3);
                        howChangeLife = 0;
                        break;
                    case 2:
                        decrementLife();
                        howChangeLife = 0;

                
                    default:
                        break;
                }
                Thread.sleep(1000);//Pause for 1000 miliseconds
            } catch(InterruptedException e){
                System.out.println("Life Counter Thread interrupted");
                return;
            }
        }
    }

}


public class lifeCounter {
    public static void main(String[] args){
        //test code
        LifeCouterThread lifeCouterThread = new LifeCouterThread();
        Thread t1 = new Thread(lifeCouterThread);
        System.out.println(((LifeCouterThread) t1).whatIsLife());
        t1.start();
        System.out.println(((LifeCouterThread) t1).whatIsLife());
        ((LifeCouterThread)t1).selectHowChangeLife(1); 
        System.out.println(((LifeCouterThread) t1).whatIsLife());

        
    }
}
