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

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.reasoner.rulesys.impl.Result;
import com.hp.hpl.jena.reasoner.rulesys.impl.ResultList;
import java.util.ArrayList;


public class CompareResults {
    
    public static boolean sameResult(ResultSet r1, ResultSet r2) {
        ResultList result1 = (new ConvertResult()).getresult(r1);

        ResultList result2 = (new ConvertResult()).getresult(r2);
         
        return (sameResult(result1.getResult(), result2.getResult()));
    }
    
    public static boolean sameResult_notEmpty(ResultSet r1, ResultSet r2) {
        ResultList result1 = (new ConvertResult()).getresult(r1);

        ResultList result2 = (new ConvertResult()).getresult(r2);

        
        return (result1.getResult().size() > 0 && sameResult(result1.getResult(), result2.getResult()));
    }
    
    public static boolean sameResult (ArrayList<? extends Result> vl1, ArrayList<? extends Result> vl2) {
            boolean retV = (vl1.size() == vl2.size());

            if(retV) {

                boolean cont = true;
                while(cont && vl1.size()>0) {
                    int pos = getPosAL(vl1.get(0), vl2);
                    if(pos>=0) {
                        vl2.remove(pos);
                        vl1.remove(0);
                    }
                    else {
                        cont = false;
                    }
                }
               retV = retV && vl2.isEmpty();
            }

            return retV;
        }
    
    public static boolean sameResult_notEmpty (ArrayList<? extends Result> vl1, ArrayList<? extends Result> vl2) {
            boolean retV = (vl1.size() >0 && vl2.size() > 0 && vl1.size() == vl2.size());

            if(retV) {

                boolean cont = true;
                while(cont && vl1.size()>0) {
                    int pos = getPosAL(vl1.get(0), vl2);
                    if(pos>=0) {
                        vl2.remove(pos);
                        vl1.remove(0);
                    }
                    else {
                        cont = false;
                    }
                }
               retV = retV && vl2.isEmpty();
            }

            return retV;
        }
    
    public static int getPosAL(Result o, ArrayList<? extends Result> alO) {
            int pos = -1;
            int i=0;
            boolean found = false; 

            while(i<alO.size() && !found) {
                if(alO.get(i).sameResult(o)) {
                    pos = i;
                    found = true;
                }
                else {
                    i++;
                }
            }

            return pos; 
        }

    }
