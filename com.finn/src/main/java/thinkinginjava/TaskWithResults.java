package thinkinginjava;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResults implements Callable<String> {
    private int id;
    public TaskWithResults(int id) {
        this.id = id;
    }

    public String call() {
        return "results of TaskResult " + id;
    }
}

class CallDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            results.add(executorService.submit(new TaskWithResults(i)));
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                executorService.shutdown();
            }
        }
    }
}
