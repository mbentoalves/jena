/*
 * (c) Copyright 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.query.engine.iterator;

import java.util.NoSuchElementException;

import com.hp.hpl.jena.query.core.ARQInternalErrorException;
import com.hp.hpl.jena.query.engine.ExecutionContext;
import com.hp.hpl.jena.query.engine.QueryIterator;
import com.hp.hpl.jena.query.engine.binding.Binding;
import com.hp.hpl.jena.query.util.Utils;

/** An iterator that applying a condition.
 * 
 * @author Andy Seaborne
 * @version $Id: QueryIterFilter.java,v 1.2 2007/02/06 17:06:01 andy_seaborne Exp $
 */

public abstract class QueryIterFilter extends QueryIter1
{
    abstract public boolean accept(Binding binding);
    
    Binding nextBinding;

    public QueryIterFilter(QueryIterator qIter, ExecutionContext context)
    {
        super(qIter, context) ;
        nextBinding = null;
    }

    /** Are there any more acceptable objects.
    * @return true if there is another acceptable object.
    */        
    protected boolean hasNextBinding()
    {
        // Needs to be idempotent.?
        if ( isFinished() )
            return false ;
        
        if (nextBinding != null)
            return true;

        // Null iterator.
        if ( getInput() == null )
            throw new ARQInternalErrorException(Utils.className(this)+": Null iterator") ;

        while ( getInput().hasNext() )
        {
            nextBinding = getInput().nextBinding();
            if (accept(nextBinding))
                return true ; 
        }
        nextBinding = null;
        return false;
    }
    
    /** The next acceptable object in the iterator.
    * @return The next acceptable object.
    */        
    public Binding moveToNextBinding() {
        if (hasNext()) {
            Binding r = nextBinding;
            nextBinding = null;
            return r;
        }
        throw new NoSuchElementException();
    }
    
    protected void releaseResources()
    { }
}

/*
 * (c) Copyright 2005, 2006, 2007 Hewlett-Packard Development Company, LP
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