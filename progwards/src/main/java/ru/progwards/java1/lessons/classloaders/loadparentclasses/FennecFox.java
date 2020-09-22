package ru.progwards.java1.lessons.classloaders.loadparentclasses;

public class FennecFox extends Fox {
    public String info = "Я лисичка фенек, у меня большие уши";

    static { System.out.println("Статический блок Лисица фенек"); }
}
