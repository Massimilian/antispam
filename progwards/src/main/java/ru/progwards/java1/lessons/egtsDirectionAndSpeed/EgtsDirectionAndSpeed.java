package ru.progwards.java1.lessons.egtsDirectionAndSpeed;

//Задача1. Класс EgtsDirectionAndSpeed
//        Эта задача основана на реальной практике (с минимальным упрощением) и связана с обработкой
//        протокола EGTS для отслеживания движения и состояния транспортных средств (далее ТС).
//        Исходные данные: два целых беззнаковых числа (например, беззнаковое число хранящееся
//        в байте принимает значения от 0 до 255, поскольку старший бит используется не для знака,
//        а для самого числа), одно (dirLow) хранится в byte, второе (speedAndDir) в short.
//        В байте dirLow хранятся младшие 8 бит направления движения ТС в градусах.
//        А в 15 младших битах speedAndDir (short) хранится скорость в км/час.
//        В старшем бите speedAndDir хранится старший бит направления движения ТС.
//        Таким образом получается, что для хранения направления движения ТС используется 9 бит,
//        а для хранения скорости - 15 бит.
//        При решении задачи необходимо помнить, что в Java byte и short являются знаковыми типами,
//        но данные из протокола EGTS, положенные в byte и short, являются беззнаковыми.
//        Для решения задачи следует.
//        3.1 Реализовать функцию public static int getSpeed(short speedAndDir),
//        которая будет возвращать скорость движения ТС в км/час.
//        3.2 Реализовать функцию public static int getDirection(byte dirLow, short speedAndDir),
//        которая будет возвращать направление движения ТС в градусах.

public class EgtsDirectionAndSpeed {

    byte dirLow; // хранение направления
    short speedAndDir; // хранение скорости и направления (в одну или другую сторону)

    public static int getSpeed(short speedAndDir) {
        return speedAndDir < 0? speedAndDir * -1: speedAndDir;
    }

    public static int getDirection(byte dirLow, short speedAndDir) {
        return speedAndDir < 0? 360 - dirLow : dirLow;
    }
}
