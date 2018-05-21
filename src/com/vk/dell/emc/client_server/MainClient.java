package com.vk.dell.emc.client_server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class MainClient {

    private static final String hostname = "rmi://localhost:5099/simple";
    private static RmiServer remote;

    static {
        try {
            remote = (RmiServer) Naming.lookup(hostname);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    private volatile static boolean stop = false;

    static class Task implements Callable<Long> {

        @Override
        public Long call() {
            long called = 0;
            while (!stop) {
                try {
                    remote.nextRandomNumber();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                ++called;
            }
            return called;
        }
    }

    private static void usage() {
        System.out.println("Usage: MainServer [N]\n" +
                "N - number of threads");
    }

    public static void main(String[] args) {

        int threadsNumber = 0;

        if (args.length == 1) {
            try {
                threadsNumber = Integer.parseInt(args[0]);
                if (threadsNumber < 1 || threadsNumber > 100) {
                    System.out.println("Threads' number should be [1, 100]");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                System.out.println("Argument N must be an integer!");
                System.exit(1);
            }
        } else {
            usage();
            System.exit(1);
        }

        List<Future<Long>> futureList = new ArrayList<>();

        Task task = new Task();
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        List<Long> result = new ArrayList<>();

        for (int i = 0; i < threadsNumber; i++) {
            futureList.add(executor.submit(task));
        }

        final int workTime = 100;
        try {
            executor.shutdown();
            executor.awaitTermination(workTime, TimeUnit.SECONDS);
            stop = true;

            for (Future<Long> future : futureList) {
                result.add(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }

        long sum = result.stream().reduce((a, b) -> a + b).orElse((long) 0);

        System.out.println((double) sum / workTime);
    }
}
