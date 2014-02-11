/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.fuseki.async;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.HashMap ;
import java.util.Map ;
import java.util.concurrent.* ;
import java.util.concurrent.atomic.AtomicLong ;

import org.apache.jena.fuseki.server.DataService ;
import org.eclipse.jetty.util.thread.Scheduler.Task ;

/** The set of currently active async tasks */
public class AsyncPool
{
    private static int nMaxThreads = 2 ;
    
    // See Executors.newCachedThreadPool and Executors.newFixedThreadPool 
    private ExecutorService executor = new ThreadPoolExecutor(0, nMaxThreads,
                                                              120L, TimeUnit.SECONDS,
                                                              new LinkedBlockingQueue<Runnable>()) ;
    private final Object mutex = new Object() ; 
    private AtomicLong counter = new AtomicLong(0) ;
    private Map<String, AsyncTask> running = new HashMap<>() ; 
    
    private static AsyncPool instance = new AsyncPool() ;
    public static AsyncPool get() 
    { return instance ; }

    private AsyncPool() { }
    
    public AsyncTask submit(Runnable task, String displayName, DataService dataService) { 
        synchronized(mutex) {
            String taskId = Long.toString(counter.incrementAndGet()) ;
            Callable<Object> c = Executors.callable(task) ;
            AsyncTask asyncTask = new AsyncTask(c, this, taskId, displayName, dataService) ;
            Future<Object> x = executor.submit(asyncTask) ;
            running.put(taskId, asyncTask) ;
            return asyncTask ;
        }
    }
    
    public Collection<AsyncTask> tasks() {
        synchronized(mutex) {
            return new ArrayList<>(running.values()) ;
        }
    }
    
    public void finished(AsyncTask task) { 
        synchronized(mutex) {
            running.remove(task.getTaskId()) ;
        }
    }
    
    public AsyncTask get(String taskId) {
        synchronized(mutex) {
            return running.get(taskId) ;
        }
    }
}

