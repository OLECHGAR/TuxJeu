package management;

public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private int limite;

    public Chronometre(int limite) {
        this.limite = limite ;
        this.begin=0;
        this.end=30000;
        this.current=0;
    }
    
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end =System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
   public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
    
    /**
    * Method to know if it remains time.
    */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        long timeSpent;
        timeSpent = (long) (current-begin); 
        return timeSpent < this.end;
    }
    
    @Override
    public String toString()
    {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((begin+(limite*1000)- current)/1000.0);        
        return "T.R :"+Integer.toString(timeSpent);
    }
     
}
