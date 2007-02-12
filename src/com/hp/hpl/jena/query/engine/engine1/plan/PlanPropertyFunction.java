/*
 * (c) Copyright 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.query.engine.engine1.plan;


import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.engine.ExecutionContext;
import com.hp.hpl.jena.query.engine.QueryIterator;
import com.hp.hpl.jena.query.engine.engine1.CompileException;
import com.hp.hpl.jena.query.engine.engine1.PlanElement;
import com.hp.hpl.jena.query.engine.engine1.PlanVisitor;
import com.hp.hpl.jena.query.engine.engine1.compiler.PFuncOps;
import com.hp.hpl.jena.query.pfunction.PropFuncArg;
import com.hp.hpl.jena.query.pfunction.PropertyFunction;
import com.hp.hpl.jena.query.pfunction.PropertyFunctionFactory;
import com.hp.hpl.jena.query.pfunction.PropertyFunctionRegistry;
import com.hp.hpl.jena.query.util.Context;
import com.hp.hpl.jena.query.util.Utils;


public class PlanPropertyFunction extends PlanElement0
{
    Node predicate ;
    PropFuncArg subjArgs ;
    PropFuncArg objArgs ;
    
    public static PlanElement make(Context context, 
                                   PropFuncArg sArgs, Node predicate, PropFuncArg oArgs)
    { return new PlanPropertyFunction(context, sArgs, predicate, oArgs) ; }
    
    private PlanPropertyFunction(Context context, PropFuncArg sArgs, Node predicate, PropFuncArg oArgs)
    {
        super(context) ; 
        this.subjArgs = sArgs ;
        this.predicate = predicate ;
        this.objArgs = oArgs ;
    }
    
    public QueryIterator build(QueryIterator input, ExecutionContext execCxt)
    {
        String uri = predicate.getURI() ;
        
        PropertyFunctionRegistry registry = PFuncOps.chooseRegistry(execCxt.getContext()) ;
        PropertyFunctionFactory factory = registry.get(uri) ; 
        PropertyFunction propFunc = null ;
        
        if ( factory == null )
            throw new CompileException("No property function for '"+uri+"'") ;

        propFunc = factory.create(uri) ;
        
        propFunc.build(getSubjArgs(), getPredicate(), getObjArgs(), execCxt) ;
        
        if ( input == null )
            LogFactory.getLog(this.getClass()).fatal("Null input to "+Utils.classShortName(this.getClass())) ;
        
        // Create the implementation iterator.
        QueryIterator qIter = propFunc.exec(input, getSubjArgs(), getPredicate(), getObjArgs(), execCxt) ;
        return qIter ;
    }
    
    public void visit(PlanVisitor visitor) { visitor.visit(this) ; }
    
    public PlanElement apply(Transform transform)  { return transform.transform(this) ; }
    
    public PlanElement copy()
    {
        return make(getContext(), subjArgs, predicate, objArgs) ;
    }

    public PropFuncArg getSubjArgs()   { return subjArgs ; }
    public Node getPredicate()         { return predicate ; }
    public PropFuncArg getObjArgs()    { return objArgs ; }
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