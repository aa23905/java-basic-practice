package day2;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAdvanced {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                                new Student("张三", "数学", 85),
                                 new Student("张三", "英语", 92),
                                 new Student("李四", "数学", 58),
                                 new Student("李四", "英语", 70),
                                 new Student("王五", "数学", 95),
                                 new Student("王五", "英语", 88),
                                 new Student("赵六", "数学", 45),
                                 new Student("赵六", "英语", 62),
                                 new Student("张三", "体育", 78),
                                 new Student("王五", "体育", 55)
        );
// reduce — 累加 / 拼接
                 int totalScore = students.stream()
                         .mapToInt(s -> s.score)
                         .reduce(0, (a, b) -> a + b);
                 System.out.println("总分：" + totalScore);

                 int maxScore = students.stream()
                         .mapToInt(s -> s.score)
                         .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);
                 System.out.println("最高分：" + maxScore);

                 String nameList = students.stream()
                         .map(s -> s.name)
                         .distinct()
                         .reduce("", (a, b) -> a + " " + b);
                 System.out.println("学生名单：" + nameList.trim());

                 // groupingBy — 分组统计
                 Map<String, List<Student>> byName = students.stream()
                         .collect(Collectors.groupingBy(s -> s.name));

                 byName.forEach((name, list) -> {
                         System.out.println(name + " 的成绩：" + list.stream()
                                         .map(s -> s.subject + "=" + s.score)
                                         .collect(Collectors.joining(", ")));
                     });

                 Map<String, Double> avgBySubject = students.stream()
                         .collect(Collectors.groupingBy(s -> s.subject, Collectors.averagingInt(s -> s.score)
                                 ));
                 System.out.println("\n各科平均分：");
                 avgBySubject.forEach((subj, avg) ->
                                 System.out.println("  " + subj + ": " + String.format("%.1f", avg)));

                 // partitioningBy — 按条件分成及格/不及格两组
                 Map<Boolean, List<Student>> passFail = students.stream()
                         .collect(Collectors.partitioningBy(s -> s.score >= 60));
                 System.out.println("\n及格人数：" + passFail.get(true).size());
                 System.out.println("不及格人数：" + passFail.get(false).size());

                 // flatMap — 扁平化
        System.out.println("--------------------------------------------------");
        System.out.println(byName.values());
                List<List<Student>> grouped = new ArrayList<>(byName.values());
                 List<Student> all = grouped.stream()
                         .flatMap(list -> list.stream())
                         .collect(Collectors.toList());
                 System.out.println("flatMap 展开后共 " + all.size() + " 条记录");
        System.out.println("--------------------------------------------------");
        System.out.println(all);

                 List<List<String>> words = Arrays.asList(
                                 Arrays.asList("Java", "Python"),
                                 Arrays.asList("Go", "Rust", "Java"),
                                 Arrays.asList("Python")
                         );
                 List<String> uniqueLanguages = words.stream()
                         .flatMap(list -> list.stream())
                         .distinct()
                         .collect(Collectors.toList());
                 System.out.println("编程语言（去重）：" + uniqueLanguages);

                // findFirst / findAny
                 String firstPassed = students.stream()
                         .filter(s -> s.score >= 60)
                         .findFirst()
                         .map(s -> s.name)
                         .orElse("无");
                 System.out.println("第一个及格的：" + firstPassed);

                 String anyMathFail = students.stream()
                         .filter(s -> s.subject.equals("数学") && s.score < 60)
                        .findAny()
                        .map(s -> s.name + "（" + s.score + "分）")
                         .orElse("数学全部及格");
                 System.out.println("数学不及格的（任意一个）：" + anyMathFail);

                 // anyMatch / allMatch / noneMatch — 短路求值，找到结果就停
                 boolean hasFullMark = students.stream().anyMatch(s -> s.score == 100);
                 System.out.println("有满分吗？" + (hasFullMark ? "有" : "没有"));

                 boolean allMathPassed = students.stream()
                         .filter(s -> s.subject.equals("数学"))
                         .allMatch(s -> s.score >= 60);
                 System.out.println("数学全部及格？" + (allMathPassed ? "是" : "否（有人挂了）"));

                 boolean noNegativeScore = students.stream().noneMatch(s -> s.score < 0);
                System.out.println("没有负分？" + (noNegativeScore ? "是" : "否"));

                 // summarizingInt — 一次统计出所有汇总数据
                 IntSummaryStatistics stats = students.stream()
                         .collect(Collectors.summarizingInt(s -> s.score));
                 System.out.println("\n汇总统计：共" + stats.getCount() + "条，总分" + stats.getSum()
                                 + "，平均" + String.format("%.1f", stats.getAverage())
                                 + "，最高" + stats.getMax() + "，最低" + stats.getMin());

                // peek — 调试用，观察流中间环节的数据
                 System.out.println("\n不及格的学生：");
                 List<Student> failed = students.stream()
                         .filter(s -> s.score < 60)
                         .peek(s -> System.out.println("  " + s))
                         .sorted(Comparator.comparingInt(s -> s.score))
                        .collect(Collectors.toList());
               System.out.println("共 " + failed.size() + " 人不及格");

                 // 综合：不及格科目按学生汇总
                 Map<String, List<String>> failedSubjects = students.stream()
                         .filter(s -> s.score < 60)
                         .collect(Collectors.groupingBy(
                                         s -> s.name,
                                         Collectors.mapping(s -> s.subject + " " + s.score, Collectors.toList())
                                ));
                 System.out.println("\n各人不及格科目：");
                failedSubjects.forEach((name, subjects) ->
                                System.out.println("  " + name + "：" + String.join("、", subjects)));
             }



}
class Student {
    String name;
    String subject;
    int score;
    Student(String name, String subject, int score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }
    public String toString() {
        return name + " " + subject + " " + score;
    }
}
