package day3;

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("🐈 " + name + "：喵喵~");
        super.makeSound();
    }

    // 子类特有方法
    public void climb() {
        System.out.println("🌳 " + name + " 在爬树");
    }






}
