package com.chimokku;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    static boolean isTest = false;
    /*
    * -work 1 -brake 1 -count 1
    * split()
    * [-work, 1, -brake, 1, -count, 1]*/
    public static void main(String[] args) {
        System.out.println("Hello Pomodoro! Напиши пожалуйста команду."); //приветствие
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        int workMin = 25; //vremya raboti
        int brakeMin = 5;
        int count = 1;

        boolean isCalHelp = false;

        int sizeLoadBar = 30;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-h", "-help" -> {
                    printMessage();
                    isCalHelp = true;

                }
                case "-work" -> workMin = Integer.parseInt(cmd[++i]);
                case "-break" -> brakeMin = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-t" -> isTest = true;

            }

        }

        if (!isCalHelp) {
            System.out.printf("Время работы: %d минут(a), " +
                    "время отдыха: %d минут(a), количество повторов: %d\n", workMin, brakeMin, count);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                try {
                    timer( workMin, brakeMin, sizeLoadBar );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("Таймер истек: " + (endTime - startTime)/(1000 * 60) + "min.");
        }
    }

    private static void timer (int workTime, int breakTime, int sizeLoadBar) throws InterruptedException {
        printProgress ("Время работать:", workTime, sizeLoadBar);

        printProgress ("Время отдыхать:", breakTime, sizeLoadBar);

    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60* time / size;
        rep = 60* time /length;
        int stretchb = size /(3* time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(!isTest){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

    private static void printMessage() {
        System.out.println(
                "\n\nPomodoro - сделай свое время более эффективным. \n"
        );
        System.out.println(
                "   -work <time>: время работы, сколько хочешь работать (задется в минутах).\n"
        );
        System.out.println(
                "   -brake <time>: время отдыха, сколько хочешь отдыхать (задается в минутах).\n"
        );
        System.out.println(
                "   -count <count>: количество повторений.\n"
        );
        System.out.println(
                "   --help: меню помощи.\n");
    }
}
