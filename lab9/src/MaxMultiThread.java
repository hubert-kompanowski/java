public class MaxMultiThread {

    public static void main(String[] args) {
        Thread t = new Thread(new Watek());
        t.run();
        GenerateRandoms a = new GenerateRandoms();
        a.fill();
    }
}

class Watek implements Runnable {
    @Override
    public void run() {
        for( int i=0; i<10; i++ )
            System.out.print("Watek ");
    }

}
