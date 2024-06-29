package dev.lpa;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        var currentThread = Thread.currentThread();
        System.out.println(currentThread.getClass().getName());
        // Çıktı : java.lang.Thread
        // Varsayılan main thread

        System.out.println(currentThread);
        printThreadState(currentThread);
        // Çıktı : ----------------------
        //Thread ID : 1
        //Thread Name : main
        //Thread Priority : 5
        //Thread State : RUNNABLE
        //Thread Group Name : main

        currentThread.setName("Main Thread");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);
        // Çıktı : ----------------------
        //Thread ID : 1
        //Thread Name : Main Thread
        //Thread Priority : 10
        //Thread State : RUNNABLE
        //Thread Group Name : main
        // Thread'lerde iş önceliği 1 ile 10 arasında sıralanır ve 1 en düşük öncelikken 10 en yüksek öncelik anlamına gelir.
        // Önceliği yüksek olanlar işlem sırasında öne alınır. 5 değeri default değerdir.

        CustomThread customThread = new CustomThread();
        customThread.start(); // her 1 saniyede bir kez 1 yazdıracak
        // Start ile asenkron çalışması için çağrılır ve eş zamanlı olarak çalışır.
        // Run ile çağırırsak senkron çalışır ve diğer işlemin bitmesini bekleyip çalışmaya başlar.

        // Runnable :
        Runnable runnable = () ->{
            for (int i = 0; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        Thread myThread = new Thread(runnable);
        myThread.start();

        for (int i = 0 ; i <=3; i++){
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void printThreadState(Thread thread){
        System.out.println("----------------------");
        System.out.println("Thread ID : " + thread.getId());
        System.out.println("Thread Name : " + thread.getName());
        System.out.println("Thread Priority : " + thread.getPriority());
        System.out.println("Thread State : " + thread.getState());
        System.out.println("Thread Group Name : " + thread.getThreadGroup().getName());
    }
}
