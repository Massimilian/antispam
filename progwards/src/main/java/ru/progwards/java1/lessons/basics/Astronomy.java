package ru.progwards.java1.lessons.basics;

public class Astronomy {
    static final double EARTH_RADIUS = 6371.2;
    static final double MERCURY_RADIUS = 2439.7;
    static final double JUPITER_RADIUS = 71492;

    public static Double sphereSquare(Double r) {
        return Math.pow(r, 2) * 4 * Math.PI;
    }

    public static Double earthSquare() { // которая вычисляет площадь поверхности Земли, считая радиус равным 6 371.2 км и используют функцию sphereSquare
        return sphereSquare(EARTH_RADIUS);
    }

    public static Double mercurySquare() { // которая вычисляет площадь поверхности Меркурия, считая радиус равным 2 439,7 км  и используют функцию sphereSquare
        return sphereSquare(MERCURY_RADIUS);
    }

    public static Double jupiterSquare() { // которая вычисляет площадь поверхности Юпитера, считая радиус равным 71 492 км  и используют функцию sphereSquare
        return sphereSquare(JUPITER_RADIUS);
    }

    public static Double earthVsMercury() { // которая вычисляет отношение площади поверхности Земли к площади поверхности Меркурия используя готовые функции площадей планет
        return sphereSquare(EARTH_RADIUS) / sphereSquare(MERCURY_RADIUS);
    }

    public static Double earthVsJupiter() { // которая вычисляет отношение площади поверхности Земли к площади поверхности Юпитера используя готовые функции площадей планет
        return sphereSquare(EARTH_RADIUS) / sphereSquare(JUPITER_RADIUS);
    }

}
