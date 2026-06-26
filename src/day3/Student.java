package day3;

public class Student {
    private String name;
    private String id;
    private double score;

    public Student(String id, String name, double score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score < 0 || score > 100) {
            System.out.println("❌ 成绩必须在 0~100 之间，当前值：" + score);
            return;
        }
        this.score = score;
    }

    public boolean isPass() {
        return score >= 60;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            System.out.println("❌ 姓名不能为空！");
            return;
        }
        this.name = name;
    }


    public String toString() {
        return String.format("Student{学号='%s', 姓名='%s', 成绩=%.1f, %s}",
                id, name, score, isPass() ? "✅ 及格" : "❌ 不及格");
    }

    public static void main(String[] args) {
        System.out.println("===== 测试 Student 类 =====");

        Student s1 = new Student("001", "张三", 85.5);
        System.out.println(s1);
        System.out.println("姓名：" + s1.getName() + "，成绩：" + s1.getScore());
        s1.setScore(92);
        System.out.println("修改后：" + s1);

        s1.setScore(150);
        System.out.println("（修改非法成绩后值不变）当前成绩：" + s1.getScore());
        s1.setName("");

        s1.setName("");

        // 6. 创建时传入非法成绩
        Student s2 = new Student("002", "李四", -10);
        System.out.println(s2 + "（因为校验失败，score 保持默认 0.0）");

        System.out.println(s1.getName() + " 是否及格？" + s1.isPass());
        System.out.println("\n✅ 封装练习完成！");
    }

}
