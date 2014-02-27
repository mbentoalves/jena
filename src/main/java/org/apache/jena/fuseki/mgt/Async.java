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

package org.apache.jena.fuseki.mgt;

import org.apache.jena.atlas.json.JsonBuilder ;
import org.apache.jena.atlas.json.JsonValue ;
import org.apache.jena.fuseki.async.AsyncPool ;
import org.apache.jena.fuseki.async.AsyncTask ;
import org.apache.jena.fuseki.server.DataService ;

public class Async
{
    public static JsonValue asyncTask(AsyncPool asyncPool, String displayName, DataService dataService, Runnable task) {
        AsyncTask asyncTask = asyncPool.submit(task, displayName, dataService) ;
        JsonBuilder builder = new JsonBuilder() ;
        builder.startObject("outer") ;
        builder.key(JsonConst.taskId).value(asyncTask.getTaskId()) ;
        builder.finishObject("outer") ;
        return builder.build() ;
    }
}
