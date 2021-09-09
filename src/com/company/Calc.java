package com.company;

import java.util.Scanner;
public class Calc {
    public static void main(String[] args) {
        System.out.println("""
                Введите математическое выражения формата:\s
                 a *действие* b
                Символы должны отделяться друг от друга пробелами.
                """);
        Input string = new Input();         // Input of expression
        String[] symb = string.keyboard();

        Romans one = new Romans(symb);
                                            // Converting symbols to int
        int a = 0;
        int b = 0;
        if (one.check()){                   // Converting Roman numbers to int
          a = one.convert(0);
          b = one.convert(2);
        } else {
            try {
                a = Integer.parseInt(symb[0]);  // Parsing string to int to int
                b = Integer.parseInt(symb[2]);
                if((a>10) | (b>10) | (a<0) | (b < 0)) {
                    System.out.println("Число не может быть больше 10 или меньше 0");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Не совпадают форматы,не является выражением, дробное число, либо римское число больше X");
                System.exit(0);
            }
        }

        Count result = new Count();            // Counting
        int c = result.counting(symb, a, b, one.check());

        if (one.check()) {                     //Converting result to roman numbers if needed and output
            System.out.println(one.convertB(c));
        } else System.out.println(c);
    }
}

class Input {
    String[] keyboard() {
        Scanner scan = new Scanner(System.in);                         // Starting input
        String string = scan.nextLine();
        String[] check = string.split(" ");
        if(check.length != 3) {
            System.out.println("Неправильный формат выражения");
            System.exit(0);
        }
        return string.split(" ");                                 //Slice into numbers;
    }
}

class Romans {
    String[] s;
    Romans(String[] symbols) {                  // Constructor
        s = symbols;
    }
    boolean check() {                          // Check if numbers are romans
        boolean r = false;
        for (int i = 0; i < (s.length - 2); i = i + 2) {
            r = (s[i].contains("V") | s[i].contains("I") | s[i].contains("X")) & (s[i + 2].contains("V") | s[i + 2].contains("I") | s[i + 2].contains("X"));
        }
        return r;
    }
    int convert(int i) {                        // Convert Romans to int
        int p = 0;
        switch(s[i]) {
            case "I":
                p = 1;
                break;
            case "II":
                p = 2;
                break;
            case "III":
                p = 3;
                break;
            case "IV":
                p = 4;
                break;
            case "V":
                p = 5;
                break;
            case "VI":
                p = 6;
                break;
            case "VII":
                p = 7;
                break;
            case "VIII":
                p = 8;
                break;
            case "IX":
                p = 9;
                break;
            case "X":
                p = 10;
                break;
            default:
                System.out.println("Неправильно введена римская цифра, либо цифра больше X");
                System.exit(0);
        }
        return p;
    }
    String convertB (int var) {                         // Convert int to Roman
        String n;
        String[] RnumbersA = new String[] {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] RnumbersB = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if (var > 11) {
            int a = var / 10;
            int b = var % 10;
            n = RnumbersA[a - 1];
            n = n + RnumbersB[b];
        } else n = RnumbersB[var];
        return n;

    }
}

class Count {
    int counting(String[] symb, int a, int b, boolean d) {
        int c = 0;                                              // Choosing operation
        switch (symb[1]) {
            case "+":
                c = (a + b);
                break;
            case "-":
                c = (a - b);
                break;
            case "*":
                c = (a * b);
                break;
            case "/":
                if (b != 0)
                    c = (a / b);
                else {
                    System.out.println("Деление на ноль!");
                    System.exit(0);
                }
                break;
            default:
                System.out.println("Доступные операции: +, -, *, /");
                System.exit(0);
        }
        if(d & (c < 1)){
            System.out.println("Результат вычитания арабских чисел не может быть меньше 1");
            System.exit(0);
        }
        return c;
    }
}


