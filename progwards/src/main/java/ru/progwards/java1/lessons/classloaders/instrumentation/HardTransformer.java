package ru.progwards.java1.lessons.classloaders.instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class HardTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.endsWith("Main")) { // если имя класса заканчивается на "...Main"
            System.out.println("Отрабатывает замена текста"); // информируем о замене текста
            byte[] text = "Заменённый текст           ".getBytes(); // создаём последовательность байтов, причём текст по размеру равен тому, который мы заменяем (поэтому такое количество пробелов)
            System.arraycopy(text, 0, classfileBuffer,0x12D, text.length);
            return classfileBuffer;
        }
        return null;
    }
}
