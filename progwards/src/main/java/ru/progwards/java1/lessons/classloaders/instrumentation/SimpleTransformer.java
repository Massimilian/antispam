package ru.progwards.java1.lessons.classloaders.instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class SimpleTransformer implements ClassFileTransformer { // Это класс, который и будет заниматься изменением классов на низком уровне
    @Override
    public byte[] transform( // метод для переопределения
                             ClassLoader loader,
                             String className, // имя класса
                             Class<?> classBeingRedefined,
                             ProtectionDomain protectionDomain,
                             byte[] classfileBuffer) // здесь находится байт-код класса
            throws IllegalClassFormatException {
        if (className.contains("Simple")) { // если имя загружаемого класса содержит "Simple"
            System.out.println("SimpleTransformer: загружается класс " + className);  // выводим информацию о том, что этот класс загружается
        }
        return null; // по идее, метод возвращает байт-код (classFileBuffer), но если мы ничего не поменяли, то лучше вернуть null
    }
}
