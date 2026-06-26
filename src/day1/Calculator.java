package day1;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        //捕捉用户输入数字异常
        try {
            System.out.println("计算器，请输入一个数:");

            double a = sc.nextDouble();
            System.out.println("输入运算符号：+,-,*,/");
            String operatorInput = sc.next();

            if (operatorInput.length() != 1 || "+-*/".indexOf(operatorInput.charAt(0)) == -1) {
                System.out.println("运算符号输入错误，请输入 + - * /");
                return;
            }
            char operator = operatorInput.charAt(0);
            System.out.println("输入第二个数:");
            double b = sc.nextDouble();

            switch (operator) {
                case '+':
                    System.out.println(a + b);
                    break;
                case '-':
                    System.out.println(a - b);
                    break;
                case '*':
                    System.out.println(a * b);
                    break;
                case '/':
                    if (b == 0) {
                        System.out.println("除数不能为0");
                        return;
                    }
                    System.out.println(a / b);
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }

        } catch (Exception e) {
            System.out.println("输入错误,请输入数字或运算符号");
        } finally {
            sc.close();
        }


    }
}
