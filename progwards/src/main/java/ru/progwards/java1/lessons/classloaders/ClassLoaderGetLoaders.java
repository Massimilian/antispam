package ru.progwards.java1.lessons.classloaders;

public class ClassLoaderGetLoaders {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getPlatformClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}