package io.kimmking.rpcfx.client.util;

import io.kimmking.rpcfx.client.service.nettyclient.util.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorFactoryUtils {
    private static final NamedThreadFactory NAMED_THREAD_FACTORY_FOR_SYNC_INCIDENT = new NamedThreadFactory("SYNC_INCIDENT");

    private static final NamedThreadFactory NAMED_THREAD_FACTORY_FOR_BACKGROUND = new NamedThreadFactory("BACKGROUND");


    private static final NamedThreadFactory NAMED_THREAD_FACTORY_FOR_MDVR_RESOURCE_VEHICLE_ALARM = new NamedThreadFactory("MDVR_RESOURCE_VEHICLE_ALARM");

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR_SYNC_INCIDENT = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()/2,
            Runtime.getRuntime().availableProcessors()/2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), NAMED_THREAD_FACTORY_FOR_SYNC_INCIDENT);

    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR_VEHICLE_RELATED_RMS;

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR_MDVR_RESOURCE_VEHICLE_ALARM = new ThreadPoolExecutor(2,
            2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), NAMED_THREAD_FACTORY_FOR_MDVR_RESOURCE_VEHICLE_ALARM);

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR_V2_CENTER_COMMON_USE = new ThreadPoolExecutor(7,
            7, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), NAMED_THREAD_FACTORY_FOR_BACKGROUND);

}
