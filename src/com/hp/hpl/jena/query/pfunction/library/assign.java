/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.query.pfunction.library;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.QueryExecException;
import com.hp.hpl.jena.query.core.Var;
import com.hp.hpl.jena.query.engine.ExecutionContext;
import com.hp.hpl.jena.query.engine.QueryIterator;
import com.hp.hpl.jena.query.engine.binding.Binding;
import com.hp.hpl.jena.query.pfunction.PFLib;
import com.hp.hpl.jena.query.pfunction.PFuncSimple;
import com.hp.hpl.jena.query.util.FmtUtils;

/** Assignment: does not chnage the value of an existing binding.
 *  Either the subject or object must be a constant or be a boudn variable.
 *  If both are bound, it degenerates to a test of RDF term equality.
 *   
 * @author Andy Seaborne
 * @version $Id: assign.java,v 1.9 2007/02/06 17:05:59 andy_seaborne Exp $
 */

public class assign extends PFuncSimple
{
    public QueryIterator execEvaluated(Binding binding, Node subject, Node predicate, Node object, ExecutionContext execCxt)
    {
        if ( subject.isVariable() && object.isVariable() )
            throw new QueryExecException("Both subject and object are unbound variables: "+FmtUtils.stringForNode(predicate, execCxt.getQuery().getPrefixMapping())) ;
        if ( subject.isVariable() )
            // Object not a variable or already bound
            return PFLib.oneResult(binding, Var.alloc(subject), object, execCxt) ;
        if ( object.isVariable() )
            // Subjects not a variable or already bound
            return PFLib.oneResult(binding, Var.alloc(object), subject, execCxt) ;
        // Both bound.  Must be the same.
        if ( subject.sameValueAs(object) )
            return PFLib.result(binding, execCxt) ;
        // different
        return PFLib.noResults(execCxt) ;
    }

}

/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
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