package tests;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AllLoginTestsSuite - Executes all login module Selenium test cases sequentially
 * and prints an executive summary report of results.
 */
public class AllLoginTestsSuite {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   SELENIUM AUTOMATION TEST SUITE - LOGIN MODULE");
        System.out.println("===============================================================\n");

        Map<String, String> results = new LinkedHashMap<>();
        Map<String, Long> durations = new LinkedHashMap<>();

        runTest("FirstTest", () -> FirstTest.main(new String[0]), results, durations);
        runTest("LoginValidTest", () -> LoginValidTest.main(new String[0]), results, durations);
        runTest("LoginInvalidTest", () -> LoginInvalidTest.main(new String[0]), results, durations);
        runTest("LoginEmptyCredentialsTest", () -> LoginEmptyCredentialsTest.main(new String[0]), results, durations);
        runTest("LoginLockedOutTest", () -> LoginLockedOutTest.main(new String[0]), results, durations);

        System.out.println("\n===============================================================");
        System.out.println("                   TEST EXECUTION SUMMARY");
        System.out.println("===============================================================");
        System.out.printf("%-30s | %-10s | %-10s%n", "Test Case", "Status", "Duration");
        System.out.println("---------------------------------------------------------------");

        int passed = 0;
        int failed = 0;

        for (Map.Entry<String, String> entry : results.entrySet()) {
            String testName = entry.getKey();
            String status = entry.getValue();
            long durationMs = durations.getOrDefault(testName, 0L);

            if ("PASSED".equals(status)) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("%-30s | %-10s | %d ms%n", testName, status, durationMs);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", results.size(), passed, failed);
        System.out.println("===============================================================\n");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void runTest(String name, Runnable testRunner, Map<String, String> results, Map<String, Long> durations) {
        long start = System.currentTimeMillis();
        try {
            testRunner.run();
            long duration = System.currentTimeMillis() - start;
            results.put(name, "PASSED");
            durations.put(name, duration);
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            results.put(name, "FAILED (" + t.getMessage() + ")");
            durations.put(name, duration);
        }
    }
}
