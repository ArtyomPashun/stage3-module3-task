package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DataReader {
    public static String getRandomLineFromFile(String fileName) {
        List<String> content = getLinesFromFile(fileName);
        Random random = new Random();

        return content.isEmpty() ? "" : content.get(random.nextInt(content.size()));
    }

    public static List<String> getLinesFromFile(String fileName) {
        List<String> content = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                DataReader.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}