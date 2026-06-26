package day3;

public class Dog extends Animal{

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("🐕 " + name + "：汪汪！");
        super.makeSound();
    }
    public void guard() {
               System.out.println("🔒 " + name + " 在看家");
         }

}
