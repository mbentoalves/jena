/*
 * (c) Copyright 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.query.lang;

import org.apache.commons.logging.*;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryParseException;
import com.hp.hpl.jena.query.Syntax;

/** This class provides the lower level access to all the parsers.
 *  It hides the details of the poer-language exception handlers and other
 *  javacc details to provide a methods that deal with setting up Query objects
 *  and using QueryException exceptions for problems.    
 * 
 * @author Andy Seaborne
 * @version $Id: Parser.java,v 1.10 2007/01/02 11:19:09 andy_seaborne Exp $
 */

public abstract class Parser
{
    static Log log = LogFactory.getLog(Parser.class) ;
    
    public abstract Query parse(Query query, String queryString) throws QueryParseException ;
    
    public static boolean canParse(Syntax syntaxURI)
    {
        Parser p = createParser(syntaxURI) ;
        return p != null ;
    }
    
    public static Parser createParser(Syntax syntaxURI)
    {
        if (syntaxURI.equals(Syntax.syntaxSPARQL))
            return new ParserSPARQL() ;
        
        if (syntaxURI.equals(Syntax.syntaxSPARQLdev))
            return new ParserSPARQLdev() ;
        
        if (syntaxURI.equals(Syntax.syntaxPrefix))
            return new ParserPrefix() ;
        
        if (syntaxURI.equals(Syntax.syntaxSPARQL_X))
            return new ParserXML() ;

        if (syntaxURI.equals(Syntax.syntaxARQ))
            return new ParserARQ() ;

        if (syntaxURI.equals(Syntax.syntaxRDQL))
            return new ParserRDQL();

        if (syntaxURI.equals(Syntax.syntaxN3QL))
            return new ParserN3QL();

        return null;
    }

    // Do any testing of queries after the construction of the parse tree.
    protected void validateParsedQuery(Query query)
    {
    }
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