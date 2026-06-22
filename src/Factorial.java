
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个非负整数：");
        int n = sc.nextInt();
        System.out.println(factorial(n));
        sc.close();
    }

    public static String factorial(int n) {
        if (n == 0 || n == 1) return n + "! = 1";
        if (n < 0) {
            return "错误：阶乘只定义在非负整数上。";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("! = ");
        long result = 1;
        for (int i = n; 1 <= i; i--) {
            sb.append(i);
            if (i > 1) {
                sb.append(" * ");
            }
            result *= i;
        }
        sb.append(" = ").append(result);
        return sb.toString();
    }

}
