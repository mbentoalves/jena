/*
 * (c) Copyright 2001, 2002, 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * [See end of file]
 */

package com.hp.hpl.jena.rdql.parser;


import java.io.PrintWriter;
import com.hp.hpl.jena.graph.query.IndexValues;
import com.hp.hpl.jena.graph.query.Expression ;
import com.hp.hpl.jena.rdql.*;

/* This file is automatically generated - do not edit.
 * Arguments: 
 *       Name = Multiply
 *       Operator = *
 *       Print Name = mult
 *       Template = ExprNumericBLANK2.java
 */

/** 
 * @author Automatically generated class: Operator: Multiply
 * @version $Id: Q_Multiply.java,v 1.11 2007-01-02 11:49:58 andy_seaborne Exp $
 */

public class Q_Multiply extends ExprNode implements Expr, ExprNumeric
{
    Expr left ;
    Expr right ;

    private String printName = "mult" ;
    private String opSymbol = "*" ;

    Q_Multiply(int id) { super(id); }

    Q_Multiply(RDQLParser p, int id) { super(p, id); }

    public NodeValue eval(Query q, IndexValues env)
    {
        //int n = jjtGetNumChildren() ;

        NodeValue x = left.eval(q, env) ;
        NodeValue y = right.eval(q, env) ;

        if ( ! x.isNumber() )
            throw new EvalTypeException("Q_Multiply: Wanted a number: "+x) ;
        if ( ! y.isNumber() )
            throw new EvalTypeException("Q_Multiply: Wanted a number: "+y) ;
        
        NodeValueSettable result ;
        if ( x instanceof NodeValueSettable )
            result = (NodeValueSettable)x ;
        else if ( y instanceof NodeValueSettable )
            result = (NodeValueSettable)y ;
        else
            result = new WorkingVar() ;

        if ( x.isInt() && y.isInt() )
            result.setInt(x.getInt() * y.getInt()) ;
        else
            result.setDouble(x.getDouble() * y.getDouble()) ;

        return result ;
    }

    // Could do some checking that children are numeric
    // Could collapse constant expressions

    public void jjtClose()
    {
        int n = jjtGetNumChildren() ;
        if ( n != 2 )
            throw new QueryException("Q_Multiply: Wrong number of children: "+n) ;
        
        left = (Expr)jjtGetChild(0) ;
        right = (Expr)jjtGetChild(1) ;
    }

    // -----------
    // graph.query.Expression

    public boolean isApply()         { return true ; }
    public String getFun()           { return super.constructURI(this.getClass().getName()) ; }
    public int argCount()            { return 2; }
    public Expression getArg(int i)  
    {
        if ( i == 0 && left instanceof Expression )
            return (Expression)left ;
        if ( i == 1 && right instanceof Expression )
            return (Expression)right ;
        return null;
    }

    public String asInfixString()
    {
        return QueryPrintUtils.asInfixString2(left, right, printName, opSymbol) ;
    }

    public String asPrefixString()
    {
        return QueryPrintUtils.asPrefixString(left, right, printName, opSymbol) ;
    }

    public void print(PrintWriter pw, int level)
    {
        QueryPrintUtils.print(pw, left, right, printName, opSymbol, level) ;
    }

    public String toString()
    {
        return asInfixString() ;
    }
}

/*
 *  (c) Copyright 2001, 2002, 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 *  All rights reserved.
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
