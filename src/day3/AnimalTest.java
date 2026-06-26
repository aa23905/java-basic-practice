package day3;

public class AnimalTest {
    public static void main(String[] args) {
        System.out.println("===== 继承 + 多态 =====");

        // 1. 直接使用子类
        Dog dog = new Dog("旺财", 3);
        Cat cat = new Cat("咪咪", 2);

        dog.info();
        dog.makeSound();
        dog.eat();
        dog.guard();

        System.out.println();

        cat.info();
        cat.makeSound();
        cat.eat();
        cat.climb();

        System.out.println();

        // 2. 向上转型 —— 多态
        System.out.println("--- 多态演示：向上转型 ---");
        Animal[] animals = {
                new Dog("来福", 1),
                new Cat("小花", 2),
                new Dog("阿黄", 4)
        };
    }
}
