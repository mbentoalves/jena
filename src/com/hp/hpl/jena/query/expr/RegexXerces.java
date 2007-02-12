/*
 * (c) Copyright 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
 * [See end of file]
 */

package com.hp.hpl.jena.query.expr;

import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.apache.xerces.impl.xpath.regex.ParseException;

/** 
 * @author Andy Seaborne
 * @version $Id: RegexXerces.java,v 1.2 2007/01/02 11:19:16 andy_seaborne Exp $
 */

public class RegexXerces implements RegexEngine
{
    RegularExpression regexPattern ;

    public RegexXerces(String pattern, String flags)
    {
        regexPattern = makePattern(pattern, flags) ;
    }
    
    public boolean match(String s)
    {
        return regexPattern.matches(s) ;
    }
    
    private RegularExpression makePattern(String patternStr, String flags)
    {
        // Input : only  m s i x
        // Check/modify flags.
        // Always "u", never patternStr
        //x: Rmove whitespace characters (#x9, #xA, #xD and #x20) unless in [] classes
        try { return new RegularExpression(patternStr, flags) ; }
        catch (ParseException pEx)
        { throw new ExprException("Regex: Pattern exception: "+pEx) ; }
    }
}

/*
 *  (c) Copyright 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
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
