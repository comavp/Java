package ru.comavp.report;

import lombok.extern.slf4j.Slf4j;
import ru.comavp.client.CustomerClient;
import ru.comavp.client.MarketingClient;
import ru.comavp.client.OrderClient;
import ru.comavp.client.ProductClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class ReportService {

    private CustomerClient customerClient = new CustomerClient();
    private MarketingClient marketingClient = new MarketingClient();
    private OrderClient orderClient = new OrderClient();
    private ProductClient productClient = new ProductClient();

    private static int COUNTER = 1;

    public void getReportInSingleThread() {
        log.info("Report generation started");

        String customersInfo = customerClient.getCustomersInfo();
        String marketingInfo = marketingClient.getMarketingInfo();
        String ordersInfo = orderClient.getOrderInfo();
        String productsInfo = productClient.getProductsInfo();

        String report = generateReport(customersInfo, marketingInfo, ordersInfo, productsInfo);

        log.info("Report generation finished: {}", report);
    }

    public void getReportUsingExecutorService() throws ExecutionException, InterruptedException {
        log.info("Report generation started");

        ExecutorService executor = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setName("my-thread-" + COUNTER++);
            return thread;
        });
        Future<String> customersInfo = executor.submit(() -> customerClient.getCustomersInfo());
        Future<String> marketingInfo = executor.submit(() -> marketingClient.getMarketingInfo());
        Future<String> ordersInfo = executor.submit(() -> orderClient.getOrderInfo());
        Future<String> productsInfo = executor.submit(() -> productClient.getProductsInfo());

        String report = generateReport(customersInfo.get(), marketingInfo.get(), ordersInfo.get(), productsInfo.get());

        log.info("Report generation finished: {}", report);
    }

    public void getReportUsingCyclicBarrier() {
        log.info("Report generation started");

        Map<String, String> resultMap = new ConcurrentHashMap<>();
        CyclicBarrier barrier = new CyclicBarrier(5);

        List<Runnable> taskList = List.of(
                () -> {
                    resultMap.put("customerClient", customerClient.getCustomersInfo());
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    resultMap.put("marketingClient", marketingClient.getMarketingInfo());
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    resultMap.put("orderClient", orderClient.getOrderInfo());
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    resultMap.put("productClient", productClient.getProductsInfo());
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        for (int i = 0; i < taskList.size(); i++) {
            new Thread(taskList.get(i), "my-thread-" + (i +1)).start();
        }

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Report generation finished: {}", generateReport(
                resultMap.get("customerClient"),
                resultMap.get("marketingClient"),
                resultMap.get("orderClient"),
                resultMap.get("productClient")
                ));
    }

    public void getReportUsingCompletableFuture() {
        log.info("Report generation started");

        List<CompletableFuture<String>> tasksList = List.of(
                CompletableFuture.supplyAsync(() -> customerClient.getCustomersInfo()),
                CompletableFuture.supplyAsync(() -> marketingClient.getMarketingInfo()),
                CompletableFuture.supplyAsync(() -> orderClient.getOrderInfo()),
                CompletableFuture.supplyAsync(() -> productClient.getProductsInfo())
        );

        List<String> clientsInfoList;

        try {
            clientsInfoList = CompletableFuture.allOf(tasksList.toArray(new CompletableFuture[0]))
                    .thenApply(x -> tasksList.stream()
                            .map(CompletableFuture::join)
                            .toList())
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        log.info("Report generation finished: {}", generateReport(clientsInfoList));
    }

    private String generateReport(List<String> clientsInfoList) {
        return clientsInfoList.stream().collect(Collectors.joining(" "));
    }

    private String generateReport(String customersInfo, String marketingInfo, String ordersInfo, String productsInfo) {
        return new StringBuilder(customersInfo)
                .append(" ")
                .append(marketingInfo)
                .append(" ")
                .append(ordersInfo)
                .append(" ")
                .append(productsInfo)
                .toString();
    }
}
