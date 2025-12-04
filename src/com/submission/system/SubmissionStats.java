package com.submission.system;

import java.util.concurrent.atomic.AtomicInteger;

public class SubmissionStats {
    // AtomicIntegers are essential for thread-safety without heavy locking
    private AtomicInteger successful;
    private AtomicInteger failed;

    public SubmissionStats() {
        this.successful = new AtomicInteger(0);
        this.failed = new AtomicInteger(0);
    }

    // [cite: 35] Track successful submissions
    public void recordSuccess() {
        successful.incrementAndGet();
    }

    // [cite: 35] Track failed submissions
    public void recordFailure() {
        failed.incrementAndGet();
    }

    // [cite: 37] Method to display results
    public void printResults(String method, long totalTimeMs) {
        int successCount = successful.get();
        int failCount = failed.get();
        int totalProcessed = successCount + failCount;

        // Calculate percentage (protect against division by zero)
//        double successRate = (totalProcessed > 0)
//                ? ((double) successCount / totalProcessed) * 100
//                : 0.0;
        double successRate;
        if (totalProcessed > 0) {
            successRate = ((double) successCount / totalProcessed) * 100;
        } else {
            successRate = 0.0;
        }

        System.out.println("====== " + method + " Statistics ======");
        System.out.println("Total execution time: " + totalTimeMs + " ms"); // [cite: 38]
        System.out.println("Total students processed: " + totalProcessed); // [cite: 39]
        System.out.println("Successful submissions: " + successCount);     // [cite: 40]
        System.out.println("Failed submissions: " + failCount);            // [cite: 41]
        System.out.printf("Success rate: %.2f%%\n", successRate);         // [cite: 42]
        System.out.println("==========================================");
    }
}
