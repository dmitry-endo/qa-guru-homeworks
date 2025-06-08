package ru.infosec;

public class JavaBasics {

    public static void main(String... args) {
        // 0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        int a = 3, b = 2;

        System.out.println("a = " + a + "\nb = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));

        // 1) применить несколько арифметических операций над int и double в одном выражении
        double c = 2.5;

        System.out.println("\nc = " + c);
        System.out.println("a + b - с = " + (a + b - c));
        System.out.println("(a - b) * с = " + ((a - b) * c));
        System.out.println("a * c / b = " + (a * c / b));

        // 2) применить несколько логических операций ( < , >, >=, <= )
        System.out.println("\na > b, expected result: true\nactual result: " + (a > b));
        System.out.println("a != c, expected result: true\nactual result: " + (a != c));
        if (b >= c) {
            System.out.println("Printed if b >= c");
        } else {
            System.out.println("Printed if b < c");
        }

        // 3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой
        // (какие максимальные и минимальные значения есть, как их получить) и переполнение
        float maxFloatValue = Float.MAX_VALUE, minFloatValue = Float.MIN_VALUE;
        double maxDoubleValue = Double.MAX_VALUE, minDoubleValue = Double.MIN_VALUE;

        System.out.println("\nThe largest positive finite value of type float: " + maxFloatValue);
        System.out.println("The largest positive finite value of type double: " + maxDoubleValue);
        System.out.println("The smallest positive nonzero value of type float: " + minFloatValue);
        System.out.println("the smallest positive nonzero value of type double: " + minDoubleValue);

        // 4) получить переполнение при арифметической операции
        System.out.println("\nOverflow: " + maxFloatValue * 2);
    }
}