package ru.ilya.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class BaseService {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();

    public String encode(long input) {
        StringBuilder encoded = new StringBuilder();

        if (input == 0)
            return String.valueOf(allowedCharacters[0]);

        while (input > 0) {
            encoded.append(allowedCharacters[(int) (input % allowedCharacters.length)]);
            input /= allowedCharacters.length;
        }
        return encoded.reverse().toString();
    }

    public long decode(String encoded) {
        char[] chars = encoded.toCharArray();

        long decoded = 0;
        int counter = 1;
        for (char aChar : chars) {
            decoded += (long) (allowedString.indexOf(aChar)
                    * Math.pow(allowedCharacters.length, chars.length - counter));
            counter++;
        }
        return decoded;
    }
}
