/*
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

package com.hp.hpl.jena.reasoner.sparqlinrules.test;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.reasoner.rulesys.impl.ResultList;
import com.hp.hpl.jena.reasoner.rulesys.impl.ResultRow;
import java.util.ArrayList;
import java.util.List;


public class ConvertResult {       
        
        public ConvertResult() {};
        
        public ResultList getresult(ResultSet rs) {
            rs.getResultVars();

            List<String> resultVars = rs.getResultVars();

            ArrayList<ResultRow> rows = new ArrayList<>();

            while(rs.hasNext()) {

                QuerySolution qs = rs.nextSolution();
                
                ResultRow row = new ResultRow();

                for(String field : resultVars) {     
                    row.addResult(field, qs.get(field).asNode());
                }

                rows.add(row);

            }    
            return new ResultList(rows);
        }        
    }