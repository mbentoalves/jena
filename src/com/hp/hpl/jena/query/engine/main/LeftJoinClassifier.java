/*
 * (c) Copyright 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.query.engine.main;

import java.util.Set;

import com.hp.hpl.jena.query.algebra.OpVars;
import com.hp.hpl.jena.query.algebra.op.OpLeftJoin;
import com.hp.hpl.jena.query.util.SetUtils;

public class LeftJoinClassifier
{
    // Test for the "well-formed" criterion of left joins whereby they can
    // be executed agains a current set of bindings.  If not, the left join
    // has to be done by execution the left, executing the right without
    // the left (so no substitution/additional indexing), then
    // left-join-ed.  ANd that can be expensive - luckily, it only occurs
    // in doubly, or more more, nested OPTIONAL expressions. 

    // This amounts to testing whether there are any optional variables in the 
    // RHS pattern (hence they are nested in someway) that also occur in the LHS
    // of the LefyJoin being considered.  

    static public boolean isLinear(OpLeftJoin op)
    {
        Set leftVars = OpVars.patternVars(op.getLeft()) ;
        Set optRight = VarFinder.optDefined(op.getRight()) ;

        // Safe for linear execution if there are no  
        return ! SetUtils.intersectionP(leftVars, optRight) ;
    }
    
    static public Set nonLinearVars(OpLeftJoin op)
    {
        Set leftVars = OpVars.patternVars(op.getLeft()) ;
        
        Set optRight = VarFinder.optDefined(op.getRight()) ;
        return SetUtils.intersection(leftVars, optRight) ;
    }

}

/*
 * (c) Copyright 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */