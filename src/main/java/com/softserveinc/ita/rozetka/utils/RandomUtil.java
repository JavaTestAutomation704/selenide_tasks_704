package com.softserveinc.ita.rozetka.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomUtil {

    public static String getRandomCyrillicString() {
        var random = new Random();
        // TODO: String should have at least 2 chars, so this solves the problem when the random number is 0
        int randomNumber = random.nextInt(7) + 2;

        var randomString = new StringBuilder(randomNumber);
        var alphabetUa = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        for (int i = 0; i < randomNumber; i++) {
            int randomLetterIndex = random.nextInt(alphabetUa.length());
            randomString.append(alphabetUa.charAt(randomLetterIndex));
        }
        return randomString.toString();
    }

    public static <T extends Enum<?>> T getRandomEnum(Class<T> clazz) {
        return clazz.getEnumConstants()[new Random().nextInt(clazz.getEnumConstants().length)];
    }
}