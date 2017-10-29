package cn.aofeng.threadpool4j;

import java.util.concurrent.Future;

public class TestThread {

    public static void main(String[] args) throws Exception {
        ThreadPoolManager tpm = ThreadPoolManager.getSingleton();
        tpm.init();

        ThreadPool threadPool = tpm.getThreadPool();

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        CallableAnsyTask task = new CallableAnsyTask(arr);

        // 将异步任务交给默认的线程池default执行
        Future<Long> future = threadPool.submit(task);
        System.out.println("异步任务在线程池default的执行结果为:" + future.get());

        // 将异步任务交给指定的线程池other执行
        threadPool.submit(task, "other");
        System.out.println("异步任务在线程池other的执行结果为:" + future.get());


        tpm.destroy();

    }

}
