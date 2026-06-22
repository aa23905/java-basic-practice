import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("排序前：" + Arrays.toString(numbers));

        int n = numbers.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;// 没发生交换，提前退出
        }
        System.out.println("排序后：" + Arrays.toString(numbers));

    }
}
