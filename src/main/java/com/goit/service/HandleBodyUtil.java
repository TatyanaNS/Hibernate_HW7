package com.goit.service;

import com.google.gson.Gson;
import org.apache.logging.log4j.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HandleBodyUtil {

    private static final Logger LOGGER = LogManager.getLogger(HandleBodyUtil.class);

    private static final Gson GSON = new Gson();

    public static <T> Optional<T> getModelFromStream(InputStream in, Class<T> returnType) {
        try (InputStream inputStream = in;
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String jsonStr = scanner.nextLine();
//            System.out.println("jsonStr = " + jsonStr);
//            System.out.println("GSON_Str = " + GSON.fromJson(jsonStr, returnType));
            return Optional.of(GSON.fromJson(jsonStr, returnType));
        } catch (IOException e) {
            LOGGER.error("Request body getting error", e);
        }
        return Optional.empty();
    }

}
