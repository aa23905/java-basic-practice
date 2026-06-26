package day1;

import java.util.Scanner;

public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = new String[100];
        double[] scores = new double[100];

        int count = 0;
        while (true) {
            System.out.println("\n===== 学生成绩管理系统（数组版） =====");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 修改成绩");
            System.out.println("4. 查询学生");
            System.out.println("5. 显示所有学生");
            System.out.println("6. 统计信息");
            System.out.println("0. 退出");
            System.out.print("请选择操作：");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("输入无效，请输入 0-6 之间的数字！");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    if (count >= names.length) {
                        System.out.println("数组已满，无法添加学生");
                        break;
                    }
                    System.out.println("添加学生");
                    System.out.print("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.print("请输入成绩："); //
                    if (!sc.hasNextDouble()) {
                        System.out.println("成绩输入无效，请输入 0-100 之间的数字");
                        sc.nextLine();  // 吃掉错误输入
                        break;
                    }
                    double score = sc.nextDouble();
                    sc.nextLine(); //
                    if (score < 0 || score > 100) {
                        System.out.println("成绩输入错误，请输入 0-100 之间的数字");
                        break;
                    } else {
                        names[count] = name;
                        scores[count] = score;
                        count++;
                        System.out.println("添加成功！当前共 " + count + " 人");
                        break;
                    }


                case 2:
                    if (count == 0) {
                        System.out.println("暂无学生信息，无法删除");
                        break;
                    }
                    System.out.print("请输入要删除的学生姓名：");
                    String deleteName = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (deleteName.equals(names[i])) {
                            for (int j = i; j < count - 1; j++) {
                                names[j] = names[j + 1];
                                scores[j] = scores[j + 1];
                            }
                            names[count - 1] = null;
                            scores[count - 1] = 0;
                            count--;
                            found = true;
                            System.out.println("删除成功！当前共 " + count + " 人");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("未找到学生 " + deleteName);

                    }
                    break;
                case 3:
                    if (count == 0) {
                        System.out.println("暂无学生信息，无法修改");
                        break;
                    }
                    System.out.print("请输入要修改成绩的学生姓名：");
                    String updateName = sc.nextLine();
                    for (int i = 0; i < count; i++) {
                        if (names[i].equals(updateName)) {
                            System.out.print("请输入新的成绩：");
                            if (!sc.hasNextDouble()) {
                                System.out.println("成绩输入无效，请输入 0-100 之间的数字");
                                sc.nextLine();  // 吃掉错误输入
                                break;
                            }
                            double newScore = sc.nextDouble();
                            sc.nextLine();
                            if (newScore < 0 || newScore > 100) {
                                System.out.println("成绩输入错误，请输入 0-100 之间的数字");
                            } else {
                                scores[i] = newScore;
                                System.out.println("修改成功！当前共 " + count + " 人");
                            }
                        }
                    }


                    break;
                case 4:
                    System.out.println("输入学生姓名");

                    String searchName = sc.nextLine();
                    boolean flag = false;
                    for (int i = 0; i < count; i++) {
                        if (names[i].equals(searchName)) {
                            System.out.println("找到学生 " + names[i] + "，成绩为 " + scores[i]);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("未找到学生 " + searchName);
                        break;
                    }
                    break;
                case 5: {
                    if (count == 0) {
                        System.out.println("暂无学生信息！");
                        break;
                    }
                    System.out.println("--- 学生列表 ---");
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + ". " + names[i] + " - " + scores[i]);
                    }
                    break;

                }
                case 6:
                    if (count == 0) {
                        System.out.println("暂无学生信息！");
                        break;
                    }
                    double sum = 0;
                    double max = scores[0];
                    double min = scores[0];
                    int passCount = 0;
                    for (int i = 0; i < count; i++) {
                        sum += scores[i];
                        if (scores[i] > max) {
                            max = scores[i];
                        }
                        if (scores[i] < min) {
                            min = scores[i];
                        }
                        if (scores[i] >= 60) {
                            passCount++;
                        }
                    }
                    double avg = sum / count;
                    System.out.println("===== 统计信息 =====");
                    System.out.println("总人数：" + count);
                    System.out.println("最高分：" + max);
                    System.out.println("最低分：" + min);
                    System.out.printf("平均分：%.2f\n", avg);
                    System.out.println("及格人数：" + passCount + "（" + (passCount * 100 / count) + "%）");
                    System.out.println("不及格人数：" + (count - passCount));
                    break;
                case 0:
                    System.out.println("感谢使用，再见！");
                    sc.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }


        }


    }
}
