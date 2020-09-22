package ru.progwards.java1.lessons.classloaders.loadparentclasses;

public class LoadParentClasses {
    static { System.out.println("Статический блок LoadParentClasses"); }

    Animal animal = new Animal();
    Fox fox = new Fox();
    FennecFox fennecFox = new FennecFox();

    public static void main(String[] args) {
        System.out.println("Точка входа LoadParentClasses.main");
        new LoadParentClasses();
        //System.out.println(new FennecFox().info);
    }
}
