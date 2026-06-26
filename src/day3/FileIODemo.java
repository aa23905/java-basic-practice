package day3;

import java.io.*;

public class FileIODemo {
    public static void main(String[] args) {
        String inputFile = "src/day3/input.txt";
        String outputFile = "src/day3/output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String upperLine = line.toUpperCase();
                writer.write(upperLine);
                writer.newLine();
            }
            System.out.println("处理完成，结果已写入 " + outputFile);
        } catch (IOException e) {
            System.out.println("文件处理失败：" + e.getMessage());
        }


    }
}
