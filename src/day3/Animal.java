package day3;

public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void makeSound() {
        System.out.println("动物发出叫声...");
    }

    public void eat() {
        System.out.println(name + " 正在吃东西");
    }

    public void info() {
        System.out.println(name + "，" + age + "岁");
    }

}
