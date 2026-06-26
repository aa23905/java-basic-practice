package day1;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {

    /*    练习需求：闰年判断

                目标

        编写一个 Java 命令行程序 day1.LeapYear.java，让用户输入一个年份，程序判断并输出该年份是否为闰年。

        闰年规则

        满足以下两个条件之一即为闰年：

        1. 能被 400 整除
        2. 能被 4 整除，且 不能被 100 整除

        其余情况都不是闰年。*/

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入一个年份：（输入 exit 退出）");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try {
                int year = Integer.parseInt(input);
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    System.out.println(year + " 是闰年");
                } else {
                    System.out.println(year + " 不是闰年");
                }

            } catch (Exception e) {
                System.out.println("输入无效，请输入一个整数年份。");
            }

        }


        sc.close();

    }
}
