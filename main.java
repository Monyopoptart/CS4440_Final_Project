
public class MyRunnable implements Runnable{
    private int var;
    public MyRunnable(int var){
        this.var = var;
    }
    public void run(){
        //Code here
    }
}

public class main {
    public static void main(String args[]){
        MyRunnable myRunnable = new MyRunnable(10);
        Thread t1 = new Thread(myRunnable);
        t1.start();
    }
    
}
