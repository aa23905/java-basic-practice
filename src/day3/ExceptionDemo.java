package day3;

/**
 * 练习 13：异常处理 + 自定义异常
 *
 * 学习目标：
 * - try-catch-finally 基本结构
 * - 多个 catch 的匹配顺序（子类在前）
 * - throw 手动抛异常 vs throws 声明异常
 * - 自定义异常类
 * - try-with-resources（自动关闭资源）
 */
public class ExceptionDemo {

    // ===== 1. 自定义异常：余额不足 =====
    static class InsufficientBalanceException extends Exception {
        private double currentBalance;
        private double attemptedAmount;

        public InsufficientBalanceException(double currentBalance, double attemptedAmount) {
            super(String.format("余额不足！当前余额：%.2f，尝试取款：%.2f", currentBalance,
                    attemptedAmount));
            this.currentBalance = currentBalance;
            this.attemptedAmount = attemptedAmount;
        }

        public double getShortfall() {
            return attemptedAmount - currentBalance;
        }
    }

    // ===== 2. 自定义异常：非法金额（运行时异常）=====
    static class InvalidAmountException extends RuntimeException {
        public InvalidAmountException(String message) {
            super(message);
        }
    }

    // ===== 3. 银行账户类 =====
    static class BankAccount {
        private String owner;
        private double balance;

        public BankAccount(String owner, double balance) {
            this.owner = owner;
            this.balance = balance;
            System.out.println("开户成功：" + owner + "，余额：¥" + balance);
        }

        // throws — 声明此方法可能抛出异常，调用方必须处理
        public void withdraw(double amount) throws InsufficientBalanceException {
            if (amount <= 0) {
                throw new InvalidAmountException("取款金额必须大于 0，你输入了：" + amount);
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(balance, amount);
            }

            balance -= amount;
            System.out.println("取款成功：" + owner + " 取了 ¥" + amount + "，剩余余额：¥" + balance);
        }
    }

    // ===== 4. 多个 catch 演示 =====
    public static void divideNumbers() {
        try {
            int[] arr = new int[2];
            arr[0] = 10;
            System.out.print("请输入一个数组下标 (0-2)：");
            java.util.Scanner sc = new java.util.Scanner(System.in);
            int index = sc.nextInt();
            System.out.print("请输入除数：");
            int divisor = sc.nextInt();
            int result = arr[index] / divisor;
            System.out.println("结果：" + result);
            sc.close();
        } catch (ArithmeticException e) {
            System.out.println("算术异常：" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("其他异常：" + e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            System.out.println("finally：无论是否异常，这段都会执行");
        }
    }

    // ===== 5. try-with-resources 演示 =====
    public static void tryWithResourcesDemo() {
        System.out.println("\n--- try-with-resources ---");
        try (java.util.Scanner sc = new java.util.Scanner("42")) {
            int num = sc.nextInt();
            System.out.println("读取到数字：" + num);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Scanner 已自动关闭（不用手动 close）");
    }

    // ===== 6. main：测试入口 =====
    public static void main(String[] args) {
        System.out.println("========== 异常处理练习 ==========\n");

        // ---------- 场景 1：正常取款 ----------
        System.out.println("--- 场景 1：正常取款 ---");
        BankAccount acc = new BankAccount("小明", 1000);
        try {
            acc.withdraw(300);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // ---------- 场景 2：余额不足（受检异常）----------
        System.out.println("--- 场景 2：余额不足（受检异常）---");
        try {
            acc.withdraw(9999);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("  差额：¥" + e.getShortfall());
        }
        System.out.println();

        // ---------- 场景 3：非法金额（运行时异常）----------
        System.out.println("--- 场景 3：非法金额（运行时异常）---");
        try {
            acc.withdraw(-100);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // ---------- 场景 4：finally 演示 ----------
        System.out.println("--- 场景 4：finally 特性 ---");
        BankAccount acc2 = new BankAccount("老王", 500);
        try {
            acc2.withdraw(600);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally 一定会执行——比如在这里记录日志");
        }
        System.out.println();

        // ---------- 场景 5：多个 catch ----------
        System.out.println("--- 场景 5：多个 catch ---");
        try {
            int[] arr = new int[2];
            arr[0] = 10;
            int index = 5;
            int divisor = 2;
            int result = arr[index] / divisor;
            System.out.println("结果：" + result);
        } catch (ArithmeticException e) {
            System.out.println("算术异常：" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("其他异常：" + e.getMessage());
        } finally {
            System.out.println("无论报什么异常都到这里兜底");
        }
        System.out.println();

        // ---------- 场景 6：try-with-resources ----------
        tryWithResourcesDemo();
        System.out.println();

        // ---------- 异常信息对比 ----------
        System.out.println("===== 异常类型对比 =====");
        System.out.printf("%-30s %-15s %-10s%n", "异常类", "类型", "必须处理?");
        System.out.printf("%-30s %-15s %-10s%n", "InsufficientBalanceException", "受检异常", "必须");
        System.out.printf("%-30s %-15s %-10s%n", "InvalidAmountException", "运行时异常", "可选");
        System.out.printf("%-30s %-15s %-10s%n", "ArithmeticException", "运行时异常", "可选");
        System.out.printf("%-30s %-15s %-10s%n", "ArrayIndexOutOfBoundsException", "运行时异常", "可选");

        System.out.println("\n练习 13 完成！");
    }
}