package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String data = "";

        try {
            data = getStringFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = getMapFromString(data);

        Profile profile = new Profile();
        profile.setName(map.get("Name"));
        profile.setAge(Integer.valueOf(map.get("Age")));
        profile.setEmail(map.get("Email"));
        profile.setPhone(Long.valueOf(map.get("Phone")));

        return profile;
    }

    private Map<String, String> getMapFromString(String data) {
        Map<String, String> map = new HashMap<>();
        String[] kvs = data.split(System.lineSeparator());
        for (String pair : kvs) {
            if (!pair.contains(": ")) {
                continue;
            }
            String[] kv = pair.split(": ");
            map.put(kv[0], kv[1]);
        }
        return map;
    }

    private String getStringFromFile(File file) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[10];

            while (inputStream.read(buffer) != -1) {
                builder.append(new String(buffer));
                buffer = new byte[10];
            }
        }
        return builder.toString();
    }
}
