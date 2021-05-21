import java.util.Random;

import static java.lang.Thread.sleep;

public class start {
    public static void main(String[] args) {
        father f = new father();
        son s = new son();
        daughter d = new daughter();
        Thread father = new Thread(f, "father");
        Thread son = new Thread(s, "son");
        Thread daughter = new Thread(d, "daughter");
        father.start();
        son.start();
        daughter.start();
    }
}

class pc_problem {
    static int mutex, empty, banana, apple;
    static int[] plate; //0:none, 1:banana, 2:apple
    static int in;
    pc_problem() {
        mutex = 1;
        empty = 0;
        banana = 0;
        apple = 0;
        plate = new int[10];
        for(int i = 0; i < 10; ++i) plate[i] = 0;
        in = 0;
    }
}

class father extends pc_problem implements Runnable {
    father() {
        super();
    }

    private static int loop_time = 100; //let process stop

    @Override
    public void run() {
        System.out.println("father starts produce");
        try {
            while(loop_time > 0) {
                Random random = new Random();   //随机数种子为系统时间
                while(empty <= 0) sleep(30);
                while(mutex <= 0) sleep(30);
                mutex--;
                int tmp = random.nextInt() % 2 + 1;
                for(int i : plate) if(i == 0) in = i;
                if(tmp == 1) System.out.println("father has put a banana");
                else System.out.println("father has put a apple");
                mutex++;
                empty--;
                if(tmp == 1) banana++;
                else apple++;
                sleep(30);
                loop_time--;
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class son extends pc_problem implements Runnable {
    private static int loop_time2 = 100; //let process stop

    @Override
    public void run() {
        System.out.println("son starts getting banana");
        try {
            while(true) {
                Random random = new Random();   //随机数种子为系统时间
                while(banana > 0) sleep(30);
                while(mutex <= 0) sleep(30);
                mutex--;
                System.out.println("son has get a banana");
                for(int i = 0; i < 10; ++i) if(plate[i] == 1) {
                    plate[i] = 0;
                    System.out.println("son has taken a banana");
                    break;
                }
                mutex++;
                empty++;
                banana--;
                sleep(30);
                loop_time2--;
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class daughter extends pc_problem implements Runnable {
    private static int loop_time3 = 100; //let process stop
    @Override
    public void run() {
        System.out.println("daughter starts getting apple");
        try {
            while(true) {
                Random random = new Random();   //随机数种子为系统时间
                while(apple > 0) sleep(30);
                while(mutex <= 0) sleep(30);
                mutex--;
                System.out.println("son has get a banana");
                for(int i = 0; i < 10; ++i) if(plate[i] == 2) {
                    plate[i] = 0;
                    System.out.println("daughter has taken a apple");
                    break;
                }
                mutex++;
                empty++;
                apple--;
                sleep(30);
                loop_time3--;
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}