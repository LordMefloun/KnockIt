package me.lordmefloun.kek.knockit.Utils;

public class Random {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
