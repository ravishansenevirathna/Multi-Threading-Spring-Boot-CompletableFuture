package com.example.multi.threading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// Marks this class as a configuration class to define beans and configuration settings.
@Configuration
// Enables Spring's asynchronous method execution capability (@Async annotation support).
@EnableAsync
public class AsyncConfig {

    // Defines a bean named "taskExecutor" to configure a thread pool executor.
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        // Creates an instance of ThreadPoolTaskExecutor, a flexible thread pool implementation.
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Sets the core pool size (number of threads that are always active and waiting for tasks).
        executor.setCorePoolSize(4);

        // Sets the maximum number of threads the pool can grow to when the workload increases.
        executor.setMaxPoolSize(4);

        // Configures the queue capacity, determining how many tasks can wait in the queue if all threads are busy.
        executor.setQueueCapacity(100);

        // Sets a custom prefix for thread names created by this executor for easier debugging.
        executor.setThreadNamePrefix("asyncThread-");

        // Initializes the executor after configuration is complete.
        executor.initialize();

        // Returns the configured executor as a bean for the application context.
        return executor;
    }
}
