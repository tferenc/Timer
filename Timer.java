import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Tamás Ferenc on 2017. 05. 29..
 */
public class Timer extends Thread {

    private static List<Timer> timers = new ArrayList<>();
    private String name;
    private int id;
    private int seconds;

    public Timer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void run(){
        try{
            while(true) {
                Thread.sleep(1000);
                seconds += 1;
            }
        }catch (InterruptedException e){
            return;
        }
    }

    public void check(){
            System.out.println("Name: " + name + " Thread id: " + id + " Seconds: " + seconds);
    }

    public static void main(String[] args) throws InterruptedException {
        Timer tea = null;
        Timer coffie = null;
        Thread teathread = null;
        Thread coffiethread = null;
        while(true) {
            Scanner in = new Scanner(System.in);
            String input = null;
            input = in.next();

            System.out.println(input);
            if (input.equals("starttea"))
            {
                timers.remove(tea);
                tea = new Timer("TEA", 11);
                timers.add(tea);
                teathread = new Thread(tea);
                teathread.start();
            }
            else if (input.equals("startcoffee"))
            {
                timers.remove(coffie);
                coffie = new Timer("COFFEE", 12);
                timers.add(coffie);
                coffiethread = new Thread(coffie);
                coffiethread.start();
            }
            else if(input.equals("check"))
            {
                for (Timer t:timers) {
                    t.check();
                }
            }
            else if(input.equals("stoptea"))
            {
                teathread.interrupt();

            }
            else if(input.equals("stopcoffee"))
            {
                coffiethread.interrupt();

            }
            else if(input.equals("exit"))
            {
                for (Timer t:timers) {
                    t.check();
                }
                System.exit(1);
            }
            else {
                System.out.println("Incorrect command");
            }
        }
    }
}
