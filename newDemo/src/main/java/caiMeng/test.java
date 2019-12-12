package caiMeng;

public class test {
    private static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            if (test.isPrime(i)) {
                System.out.print(i);
                System.out.print(',');
            }
        }
        System.out.println();
        for (int i = 2; i < 100; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.print(i);
                System.out.print(',');
            }
        }
    }
}
