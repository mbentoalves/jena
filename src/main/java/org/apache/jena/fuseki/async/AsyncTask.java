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

import static java.lang.String.format ;

import java.util.concurrent.Callable ;

import com.hp.hpl.jena.sparql.util.Utils ;

import org.apache.jena.atlas.lib.InternalErrorException ;
import org.apache.jena.atlas.logging.Log ;
import org.apache.jena.fuseki.Fuseki ;
import org.apache.jena.fuseki.server.DataService ;
import org.slf4j.Logger ;

/** An asynchronous task */ 
public class AsyncTask implements Callable<Object>  
{
    private static Logger log = Fuseki.serverLog ; 
    
    private final Callable<Object> callable ;
    private final AsyncPool pool ;

    private final String displayName ;
    private final DataService dataService ;

    private String startPoint = null ;
    private String finishPoint = null ;

    private final String taskId ;

    /*package*/ AsyncTask(Callable<Object> callable, 
                          AsyncPool pool,
                          String taskId,
                          String displayName,
                          DataService dataService ) {
        this.callable = callable ;
        this.pool = pool ;
        this.taskId = taskId ; 
        this.displayName = displayName ;
        this.dataService = dataService ;
    }

    /** Unique task id */
    public String getTaskId() { return taskId ; }
    
    /** Display name - no newlines */
    public String displayName() { return displayName ; }
    
    public DataService getDataService() { return dataService ; }

    private void start() {
        if ( startPoint != null ) {
            String msg = format("[Task %s] Async task has already been started", taskId) ;
            Log.warn(Fuseki.serverLog, msg) ;
            throw new InternalErrorException("Finish has already been called ["+getTaskId()+"]") ; 
        }
            
        Fuseki.serverLog.info(format("[Task %s] starts : %s",taskId, displayName)) ;
        startPoint = Utils.nowAsXSDDateTimeString() ;
    }
    
    public void finish() {
        if ( finishPoint != null ) {
            String msg = format("[Task %s] Async task has already been finished", taskId) ;
            Log.warn(Fuseki.serverLog, msg) ;
            throw new InternalErrorException("Finish has already been called ["+getTaskId()+"]") ; 
        }
        finishPoint = Utils.nowAsXSDDateTimeString() ;
        Fuseki.serverLog.info(format("[Task %s] finishes : %s",taskId, displayName)) ;
    }
    
    @Override
    public Object call() {
        try {
            start() ;
            return callable.call() ;
        }
        catch (Exception ex) {
            log.error("Async task threw an expection", ex) ;
            return null ;
        }
        finally {
            finish() ;
            pool.finished(this) ;
        }
    }

    public String getStartPoint() {
        return startPoint ;
    }

    public String getFinishPoint() {
        return finishPoint ;
    }
}

