To explicitly run a method on the ForkJoinPool.commonPool instead of a custom taskExecutor in Spring Boot, 
you don't need to use @Async at all. 
Instead, you can leverage the CompletableFuture.runAsync or CompletableFuture.supplyAsync methods, which use the ForkJoinPool.commonPool by default.

ThreadPoolTaskExecutor: A highly configurable thread pool implementation provided by Spring for asynchronous processing.
Core Pool Size: Number of threads that remain active even when idle.
Max Pool Size: Maximum number of threads that can be created under high load.
Queue Capacity: Number of tasks that can wait for execution when all threads are busy. If this limit is reached, new tasks may be rejected.
