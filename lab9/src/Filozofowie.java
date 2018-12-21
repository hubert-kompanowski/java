import java.util.concurrent.Semaphore;

public class Filozofowie {

    private final static int n = 5;
    private final static Filozof[] filozofowie = new Filozof[n];
    private final static Semaphore sem = new Semaphore(1);

    public static void main(String[] args) {
        filozofowie[0] = new Filozof(0);
        for (int i = 1; i < n; i++) {
            filozofowie[i] = new Filozof(i);
        }
        for (Thread t : filozofowie) {
            t.start();
        }
    }

    public static class Filozof extends Thread {
        private enum Stan {MYSLI, JEST_GLODNY, JE};
        private final int id;
        private Stan stan;
        private final Semaphore self;
        Filozof(int id) {
            this.id = id;
            self = new Semaphore(0);
            stan = Stan.MYSLI;
        }
        private Filozof lewo() {
            return filozofowie[id == 0 ? n - 1 : id - 1];
        }
        private Filozof prawo() {
            return filozofowie[(id + 1) % n];
        }
        public void run() {
            try {
                while (true) {
                    pokazStan();
                    switch(stan) {
                        case MYSLI:
                            mysliLubJe();
                            sem.acquire();
                            stan = Stan.JEST_GLODNY;
                            break;
                        case JEST_GLODNY:
                            test(this);
                            sem.release();
                            self.acquire();
                            stan = Stan.JE;
                            break;
                        case JE:
                            mysliLubJe();
                            sem.acquire();
                            stan = Stan.MYSLI;
                            test(lewo());
                            test(prawo());
                            sem.release();
                            break;
                    }
                }
            } catch(InterruptedException ignored) {}
            System.out.println();
        }
        static private void test(Filozof p) {
            if (p.lewo().stan != Stan.JE && p.stan == Stan.JEST_GLODNY &&
                    p.prawo().stan != Stan.JE) {
                p.stan = Stan.JE;
                p.self.release();
            }
        }
        private void mysliLubJe() {
            try {
                Thread.sleep( Math.round(Math.random() * 5000));
            } catch (InterruptedException e) {}
        }
        private void pokazStan() {
            System.out.println("Filozof " + id + " " + stan);
        }
    }
}