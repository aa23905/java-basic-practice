package day1;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = sc.nextLine();
        if (isPalindrome(str)) {
            System.out.println(str + " 是回文字符串");
        } else {
            System.out.println(str + " 不是回文字符串");
        }

        sc.close();
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {

                return false;
            }

            left++;
            right--;

        }

        return true;
    }


}
