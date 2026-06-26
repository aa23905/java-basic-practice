package day3;

/**
 * 练习 12：面向对象 —— 接口 + 抽象类
 *
 * 对比理解：
 * - 抽象类：用 abstract 关键词，"是什么"的关系，可以有成员变量和普通方法
 * - 接口：用 interface 关键词，"能干什么"的关系，JDK 8 后可以有 default/static 方法
 */

// ===== Step 1: 抽象类 Employee =====
abstract class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public void sayHello() {
        System.out.println("大家好，我是 " + name);
    }

    public abstract double calculateSalary();
    public abstract String getJobDescription();
}

// ===== Step 2: 接口 Programmable（编程能力）=====
interface Programmable {
    void writeCode();
    void fixBug();

    default void learnNewLanguage() {
        System.out.println("📚 正在学习新编程语言...");
    }
}

// ===== Step 3: 接口 Manageable（管理能力）=====
interface Manageable {
    void leadMeeting();
    void reviewWork();
}

// ===== Step 4: 程序员类 Programmer =====
class Programmer extends Employee implements Programmable {
    private int bugCount;

    public Programmer(String name, double baseSalary, int bugCount) {
        super(name, baseSalary);
        this.bugCount = bugCount;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bugCount * 100;
    }

    @Override
    public String getJobDescription() {
        return "💻 写代码、修 bug";
    }

    @Override
    public void writeCode() {
        System.out.println("💻 " + name + " 正在写 Java 代码...");
    }

    @Override
    public void fixBug() {
        System.out.println("🐛 " + name + " 修了一个 bug！");
        bugCount++;
    }
}

// ===== Step 5: 经理类 Manager =====
class Manager extends Employee implements Manageable {
    private int teamSize;

    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + teamSize * 500;
    }

    @Override
    public String getJobDescription() {
        return "👔 管理团队，" + teamSize + " 人";
    }

    @Override
    public void leadMeeting() {
        System.out.println("📋 " + name + " 在主持周会");
    }

    @Override
    public void reviewWork() {
        System.out.println("🔍 " + name + " 在做代码审查");
    }
}

// ===== Step 6: 技术经理 TechLead =====
class TechLead extends Employee implements Programmable, Manageable {
    public TechLead(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return baseSalary + 5000;
    }

    @Override
    public String getJobDescription() {
        return "👑 技术管理两手抓";
    }

    @Override
    public void writeCode() {
        System.out.println("💻 " + name + " 在写架构代码");
    }

    @Override
    public void fixBug() {
        System.out.println("🐛 " + name + " 在修核心 bug");
    }

    @Override
    public void leadMeeting() {
        System.out.println("📋 " + name + " 在主持技术评审");
    }

    @Override
    public void reviewWork() {
        System.out.println("🔍 " + name + " 在做架构审查");
    }
}

// ===== Step 7: 测试入口 =====
public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("===== 抽象类 vs 接口 =====");

        // 多态：用父类引用统一处理
        Employee[] employees = {
                new Programmer("小明", 8000, 12),
                new Manager("老王", 12000, 5),
                new TechLead("张姐", 15000)
        };

        for (Employee e : employees) {
            System.out.println("\n--- " + e.name + " ---");
            e.sayHello();
            System.out.println("岗位：" + e.getJobDescription());
            System.out.printf("本月工资：%.0f 元%n", e.calculateSalary());
        }

        // 接口多态测试
        System.out.println("\n===== 编程能力测试 =====");
        Programmable[] coders = {
                new Programmer("小明", 0, 0),
                new TechLead("张姐", 0)
        };
        for (Programmable p : coders) {
            p.writeCode();
            p.fixBug();
            p.learnNewLanguage();
        }

        System.out.println("\n===== 管理能力测试 =====");
        Manageable[] managers = {
                new Manager("老王", 0, 0),
                new TechLead("张姐", 0)
        };
        for (Manageable m : managers) {
            m.leadMeeting();
            m.reviewWork();
        }

        System.out.println("\n✅ 练习 12 完成！");
    }
}