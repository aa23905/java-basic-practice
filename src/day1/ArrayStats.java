package day1;

public class ArrayStats {
    public static void main(String[] args) {
        int[] numbers = {12, 22, 813, 4, 295, 15, 3, 11};
        int n = numbers.length;
        int max = numbers[0];
        int min = numbers[0];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
            sum += numbers[i];
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Sum: " + sum);
        double average = (double) sum / n;
        System.out.println("Average: " + average);
    }
}
