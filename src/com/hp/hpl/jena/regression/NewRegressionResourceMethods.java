/*
 	(c) Copyright 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 	All rights reserved - see end of file.
 	$Id: NewRegressionResourceMethods.java,v 1.11 2009-01-16 17:23:50 andy_seaborne Exp $
*/

package com.hp.hpl.jena.regression;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.shared.PropertyNotFoundException;
import com.hp.hpl.jena.vocabulary.RDF;

import junit.framework.*;

public class NewRegressionResourceMethods extends NewRegressionBase
    {
    public NewRegressionResourceMethods( String name )
        { super( name );  }

    public static Test suite()
        { return new TestSuite( NewRegressionResourceMethods.class ); }

    protected Model getModel()
        { return ModelFactory.createDefaultModel(); }

    protected Model m;
    
    protected Resource r;

    protected final String lang = "en";
    
    protected Literal tvLiteral;

    protected Resource tvResource;
    
    @Override
    public void setUp()
        { 
        m = getModel();
        tvLiteral = m.createLiteral( "test 12 string 2" );
        tvResource = m.createResource();
        r = 
            m.createResource()
            .addLiteral( RDF.value, tvBoolean )
            .addLiteral( RDF.value, tvByte )
            .addLiteral( RDF.value, tvShort )
            .addLiteral( RDF.value, tvInt )
            .addLiteral( RDF.value, tvLong )
            .addLiteral( RDF.value, tvChar )
            .addLiteral( RDF.value, tvFloat )
            .addLiteral( RDF.value, tvDouble )
            .addProperty( RDF.value, tvString )
            .addProperty( RDF.value, tvString , lang )
            .addLiteral( RDF.value, tvObject )
            .addProperty( RDF.value, tvLiteral )
            .addProperty( RDF.value, tvResource )
            ;
        }
    
    public void testBoolean()
        { assertTrue( r.hasLiteral( RDF.value, tvBoolean ) ); }
    
    public void testByte()
        { assertTrue( r.hasLiteral( RDF.value, tvByte ) ); }
    
    public void testShort()
        { assertTrue( r.hasLiteral( RDF.value, tvShort ) ); }
    
    public void testInt()
        { assertTrue( r.hasLiteral( RDF.value, tvInt ) ); }
    
    public void testLong()
        { assertTrue( r.hasLiteral( RDF.value, tvLong ) ); }
    
    public void testChar()
        { assertTrue( r.hasLiteral( RDF.value, tvChar ) ); }
    
    public void testFloat()
        { assertTrue( r.hasLiteral( RDF.value, tvFloat ) ); }
    
    public void testDouble()
        { assertTrue( r.hasLiteral( RDF.value, tvDouble ) ); }
    
    public void testString()
        { assertTrue( r.hasProperty( RDF.value, tvString ) ); }
    
    public void testStringWithLanguage()
        { assertTrue( r.hasProperty( RDF.value, tvString, lang ) ); }
    
    public void testObject()
        { assertTrue( r.hasLiteral( RDF.value, tvObject ) ); }
    
    public void testLiteral()
        { assertTrue( r.hasProperty( RDF.value, tvLiteral ) ); }
    
    public void testResource()
        { assertTrue( r.hasProperty( RDF.value, tvResource ) ); }
    
    public void testCorrectSubject()
        { assertEquals( r, r.getRequiredProperty( RDF.value ).getSubject() ); }
    
    public void testNoSuchPropertyException()
        {
        try { r.getRequiredProperty( RDF.type ); fail( "missing property should throw exception" ); }
        catch (PropertyNotFoundException e) { pass(); }
        }
    
    public void testNoSuchPropertyNull()
        { assertNull( r.getProperty( RDF.type ) );  }
    
    public void testAllSubjectsCorrect()
        {
        testHasSubjectR( m.listStatements() );
        testHasSubjectR( r.listProperties() );
        }

    protected void testHasSubjectR( StmtIterator it )
        { while (it.hasNext()) assertEquals( r, it.nextStatement().getSubject() ); }
    
    public void testCountsCorrect()
        {
        assertEquals( 13, iteratorToList( m.listStatements() ).size() );
        assertEquals( 13, iteratorToList( r.listProperties( RDF.value ) ).size() );
        assertEquals( 0, iteratorToList( r.listProperties( RDF.type ) ).size() );
        }
    
    public void testRemoveProperties()
        {
        r.removeProperties();
        assertEquals( false, m.listStatements( r, null, (RDFNode) null ).hasNext() );
        }
    }


/*
 * (c) Copyright 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
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