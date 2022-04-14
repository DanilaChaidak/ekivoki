package edu.javagroup.ekivoki.config.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ConfigReader {

    public Map<String, String> getMap(String fileName) {
        List<String> list = readWithoutComments(fileName);
        Map<String, String> map = new HashMap<>();
        for (String line : list) {
            String[] array = line.split("=");
            if (array.length >= 2) {
                map.put(array[0], getValue(array));
            }
        }
        return map;
    }

    private List<String> readWithoutComments(String fileName) {
        List<String> list = readAllLines(fileName);
        List<String> result = new ArrayList<>(list.size());
        for (String line : list) {
            if (!line.trim().startsWith("#")) {
                result.add(line);
            }
        }
        return result;
    }

    private List<String> readAllLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private String getValue(String[] array) {
        if (array.length == 2) {
            return array[1];
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < array.length; i++) {
            result.append(array[i]).append("=");
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }
}
