/*
  (c) Copyright 2002, 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
  [See end of file]
  $Id: JenaTestBase.java,v 1.25 2007-01-02 11:53:31 andy_seaborne Exp $
*/

package com.hp.hpl.jena.test;

import java.lang.reflect.*;
import junit.framework.*;
import java.util.*;

import com.hp.hpl.jena.util.CollectionFactory;
import com.hp.hpl.jena.util.iterator.*;

/**
    A basis for Jena test cases which provides assertFalse and assertDiffer.
    Often the logic of the names is clearer than using a negation (well, Chris
    thinks so anyway).
    
 	@author kers
*/
public class JenaTestBase extends TestCase
    {
    public JenaTestBase( String name )
        { super( name ); }
        
    /**
        Does what it says on the can.
    */
    public void testToSilenceJunitComplaintsAboutNoTestMethods()
        {}
    
    /**
        assert that the two objects must be unequal according to .equals().
        @param title a labelling string for the assertion failure text
        @param x an object to test; the subject of a .equals()
        @param y the other object; the argument of the .equals()
    */
    public static void assertDiffer( String title, Object x, Object y )
        { 
        if (x == null ? y == null : x.equals( y ))
            fail( (title == null ? "objects should be different, but both were: " : title) + x );
        }
        
    /**
        assert that the two objects must be unequal according to .equals().
        @param x an object to test; the subject of a .equals()
        @param y the other object; the argument of the .equals()
    */
    public static void assertDiffer( Object x, Object y )
        { assertDiffer( null, x, y ); }
    
    /**
        assert that the object <code>x</code> must be of the class 
        <code>expected</code>.
    */
    public static void assertInstanceOf( Class expected, Object x )
        {
        if (x == null)
            fail( "expected instance of " + expected + ", but had null" );
        if (!expected.isInstance( x )) 
            fail( "expected instance of " + expected + ", but had instance of " + x.getClass() );
        }
        
    /**
    	Answer a Set formed from the elements of the List <code>L</code>.
    */
    public static Set listToSet( List L )
        { return CollectionFactory.createHashedSet( L ); }

    /**
        Answer a List of the substrings of <code>s</code> that are separated 
        by spaces.
    */
    protected static List listOfStrings( String s )
        {
        List result = new ArrayList();
        StringTokenizer st = new StringTokenizer( s );
        while (st.hasMoreTokens()) result.add( st.nextToken() );
        return result;
        }
    
    /**
        Answer a Set of the substrings of <code>s</code> that are separated 
        by spaces.
    */
    protected static Set setOfStrings( String s )
        {
        Set result = new HashSet();
        StringTokenizer st = new StringTokenizer( s );
        while (st.hasMoreTokens()) result.add( st.nextToken() );
        return result;
        }

    /**
        Answer a list containing the single object <code>x</code>.
    */
    protected static List listOfOne( Object x )
        {
        List result = new ArrayList();
        result.add( x );
        return result;
        }

    /**
        Answer a Set containing the single object <code>x</code>.
    */
    protected static Set setOfOne( Object x )
        {
        Set result = new HashSet();
        result.add( x );
        return result;
        }
    
    /**
        Answer a fresh list which is the concatenation of <code>L</code> then
        <code>R</code>. Neither <code>L</code> nor <code>R</code> is updated.
    */
    public static List append( List L, List R )
        { List result = new ArrayList( L );
        result.addAll( R );
        return result; }
    
    /**
        Answer an iterator over the space-separated substrings of <code>s</code>.
    */
    protected static ExtendedIterator iteratorOfStrings( String s )
        { return WrappedIterator.create( listOfStrings( s ).iterator() ); }
    
    /**
        Do nothing; a way of notating that a test has succeeded, useful in the 
        body of a catch-block to silence excessively [un]helpful disgnostics. 
    */
    public static void pass()
        {}
        
    /**
        Answer the constructor of the class <code>c</code> which takes arguments 
        of the type(s) in <code>args</code>, or <code>null</code> if there 
        isn't one.
    */
    public static Constructor getConstructor( Class c, Class [] args )
        {
        try { return c.getConstructor( args ); }
        catch (NoSuchMethodException e) { return null; }
        }

    /**
        Answer true iff the method <code>m</code> is a public method which fits
        the pattern of being a test method, ie, test*() returning void.
    */
    public static boolean isPublicTestMethod( Method m ) 
        { return Modifier.isPublic( m.getModifiers() ) && isTestMethod( m ); }
     
    /**
        Answer true iff the method <code>m</code> has a name starting "test",
        takes no arguments, and returns void; must catch junit tests, in other 
        words.
    */
    public static boolean isTestMethod( Method m ) 
        { return 
            m.getName().startsWith( "test" ) 
            && m.getParameterTypes().length == 0 
            && m.getReturnType().equals( Void.TYPE ); }              

    /**
         Answer true iff <code>subClass</code> is the same class as 
         <code>superClass</code>, if its superclass <i>is</i> <code>superClass</code>,
         or if one of its interfaces hasAsInterface that class.
    */
    public static boolean hasAsParent( Class subClass, Class superClass )
        {
        if (subClass == superClass || subClass.getSuperclass() == superClass) return true;
        Class [] is = subClass.getInterfaces();
        for (int i = 0; i < is.length; i += 1) if (hasAsParent( is[i], superClass )) return true;
        return false;
        }
    
    /**
         Fail unless <code>subClass</code> has <code>superClass</code> as a
         parent, either a superclass or an implemented (directly or not) interface.
    */
    public static void assertHasParent( Class subClass, Class superClass )
        {
        if (hasAsParent( subClass, superClass ) == false)
            fail( "" + subClass + " should have " + superClass + " as a parent" );
        }
    }


/*
    (c) Copyright 2003, 2004, 2005, 2006, 2007 Hewlett-Packard Development Company, LP
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.

    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.

    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/