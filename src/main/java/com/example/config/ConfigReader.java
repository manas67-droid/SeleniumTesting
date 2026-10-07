package com.example.config;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read and cache configuration properties from config.properties.
 * Supports JVM System property overrides (e.g., -Dheadless=false).
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        // 1. Try loading from thread context class loader
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                properties.load(is);
                return;
            }
        } catch (IOException e) {
            System.err.println("Failed to load config.properties from classpath: " + e.getMessage());
        }

        // 2. Fallback to standard relative file paths
        String[] fallbackPaths = {
            "src/test/resources/config.properties",
            "src/main/resources/config.properties",
            "config.properties"
        };

        for (String path : fallbackPaths) {
            File file = new File(path);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                    return;
                } catch (IOException e) {
                    System.err.println("Error reading " + path + ": " + e.getMessage());
                }
            }
        }
    }

    public static String getProperty(String key) {
        // System properties override file properties (useful for CI/CD and CLI flags)
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.trim().isEmpty()) {
            return systemProp.trim();
        }
        String value = properties.getProperty(key);
        return (value != null) ? value.trim() : null;
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getBrowser() {
        String browser = getProperty("browser");
        return (browser != null) ? browser : "chrome";
    }

    public static boolean isHeadless() {
        String headless = getProperty("headless");
        return headless == null || Boolean.parseBoolean(headless);
    }

    public static int getExplicitWait() {
        String wait = getProperty("timeout.explicit");
        return (wait != null) ? Integer.parseInt(wait) : 15;
    }

    public static int getImplicitWait() {
        String wait = getProperty("timeout.implicit");
        return (wait != null) ? Integer.parseInt(wait) : 10;
    }
}
