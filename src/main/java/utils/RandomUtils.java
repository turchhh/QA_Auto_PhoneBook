package utils;

import java.util.Random;

public class RandomUtils {

    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println(generateString(10));
        System.out.println(generateString(5)+"_"+generateString(5));
        System.out.println(generateEmail(7));
    }
    public static String generateString(int length){
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] randomString = new char[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            index = random.nextInt(characters.length());   //2 == c
            randomString[i] = characters.charAt(index);  //[0] - [c]
        }
        return new String(randomString);
    }

    public static String generatePhone(int length){
        String characters = "0123456789";
        char[] randomString = new char[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            index = random.nextInt(characters.length());   //2 == c
            randomString[i] = characters.charAt(index);  //[0] - [c]
        }
        return new String(randomString);
    }

    public static String generateEmail(int length){
        String[] domains = {"@mail.com", "@yahoo.com", "@gmail.com", "@example.com"};
        String domain = domains[random.nextInt(domains.length)];
        return (generateString(length) + domain);
    }
}