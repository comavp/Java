package ru.comavp;

import org.junit.jupiter.api.Test;
import ru.comavp.report.ReportService;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

public class ReportServiceTest {

    private ReportService reportService = new ReportService();

    @Test
    public void testGetReportInSingleThread() {
        LocalTime start = LocalTime.now();
        reportService.getReportInSingleThread();
        LocalTime end = LocalTime.now();

        long diff = start.until(end, ChronoUnit.MILLIS);
        System.out.println("Общее время: " + diff + " миллисекунд");
    }

    @Test
    public void testGetReportUsingExecutorService() throws ExecutionException, InterruptedException {
        LocalTime start = LocalTime.now();
        reportService.getReportUsingExecutorService();
        LocalTime end = LocalTime.now();

        long diff = start.until(end, ChronoUnit.MILLIS);
        System.out.println("Общее время: " + diff + " миллисекунд");
    }

    @Test
    public void testGetReportUsingCyclicBarrier() {
        LocalTime start = LocalTime.now();
        reportService.getReportUsingCyclicBarrier();
        LocalTime end = LocalTime.now();

        long diff = start.until(end, ChronoUnit.MILLIS);
        System.out.println("Общее время: " + diff + " миллисекунд");
    }

    @Test
    public void testGetReportUsingCompletableFuture() {
        LocalTime start = LocalTime.now();
        reportService.getReportUsingCompletableFuture();
        LocalTime end = LocalTime.now();

        long diff = start.until(end, ChronoUnit.MILLIS);
        System.out.println("Общее время: " + diff + " миллисекунд");
    }
}
