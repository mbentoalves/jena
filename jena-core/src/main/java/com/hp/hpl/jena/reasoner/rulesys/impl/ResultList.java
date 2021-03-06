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

package com.hp.hpl.jena.reasoner.rulesys.impl;

import java.util.ArrayList;


public class ResultList extends Result{
    ArrayList<ResultRow> result;

    public ResultList(ArrayList<ResultRow> presult) {
        result = presult;
    }

    public ResultList() {
        result = new ArrayList<>();
    }
    
    public void addResultRow(ResultRow rr) {
        result.add(rr);
    }
    
    public void print() {
        for(ResultRow rs : result) {
            System.out.println(rs.toString());
        }
    }

    public ArrayList<ResultRow> getResult() {
        return result;
    }
        
    @Override
    public boolean sameResult(Result vrl) {
        ArrayList<ResultRow> r1 = new ArrayList<>();
        r1.addAll(result);
        ArrayList<ResultRow> r2 = new ArrayList<>();
        r2.addAll(((ResultList) vrl).result);
        return ResultsOp.sameResult(r1, r2);
    }
    
    public boolean sameResult_notEmpty(Result vrl) {
        if(((ResultList) vrl).getResult().size() > 0) {
            return sameResult(vrl);
        }
        else {
            return false;
        }
    }
   
}
