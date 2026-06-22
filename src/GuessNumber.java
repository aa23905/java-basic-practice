import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(100)+1;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入 1~100 的数字：");

        while (!sc.hasNextInt()) {
            System.out.println("输入无效，请输入整数！");
            sc.next();
        }
        int num = sc.nextInt();
        int sum = 1;//计数器
        while(num != n) {
            if(num<1 || num>100) {
                System.out.println("输入数字超出范围，请重新输入");
            }else if(num<n) {
                System.out.println("输入数字偏小，请重新输入");
            }else{
                System.out.println("输入数字偏大，请重新输入");
            }
            System.out.print("请重新输入：");
            while (!sc.hasNextInt()) {
                System.out.println("输入无效，请输入整数！");
                sc.next();
            }
            num = sc.nextInt();
            sum ++;

        }

        System.out.println("恭喜你，猜对了！你一共猜了"+sum+"次");
        sc.close();
    }

}
