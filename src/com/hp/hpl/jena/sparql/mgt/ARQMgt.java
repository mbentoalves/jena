/*
 * (c) Copyright 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.sparql.mgt;

import java.lang.management.ManagementFactory ;
import java.util.HashMap ;
import java.util.Map ;

import javax.management.InstanceAlreadyExistsException ;
import javax.management.MBeanRegistrationException ;
import javax.management.MBeanServer ;
import javax.management.MalformedObjectNameException ;
import javax.management.NotCompliantMBeanException ;
import javax.management.ObjectName ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import com.hp.hpl.jena.query.ARQ ;
import com.hp.hpl.jena.sparql.ARQException ;
import com.hp.hpl.jena.sparql.engine.QueryEngineBase ;

public class ARQMgt
{
    static private Logger log = LoggerFactory.getLogger(ARQMgt.class) ;
    private static boolean initialized = false ;
    private static Map<ObjectName, Object> mgtObjects = new HashMap<ObjectName, Object>() ;
    private static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
    
    public static synchronized void init()
    {
        if ( initialized )
            return ;
        initialized = true ;
        
        String NS = ARQ.PATH ;
        
        register(NS+".system:type=SystemInfo", new SystemInfo(ARQ.arqIRI, ARQ.VERSION, ARQ.BUILD_DATE)) ;
        register(NS+".system:type=Context", new ContextMBean(ARQ.getContext())) ;
        register(NS+".system:type=Engine", QueryEngineBase.queryEngineInfo) ;
    }
    
    public static void register(String name, Object bean)
    {
        ObjectName objName = null ;
        try
        { objName = new ObjectName(name) ; }
        catch (MalformedObjectNameException ex)
        {  throw new ARQException("Failed to create name '"+name+"': "+ex.getMessage(), ex) ; }
        
        try {
            log.debug("Register MBean: "+objName) ;
            mbs.registerMBean(bean, objName);
            
            // remember ...
            mgtObjects.put(objName, bean) ;

        } catch (NotCompliantMBeanException ex)
        {
            throw new ARQException("Failed to register '"+objName.getCanonicalName()+"': "+ex.getMessage(), ex) ;
        } catch (InstanceAlreadyExistsException ex)
        {
            throw new ARQException("Failed to register '"+objName.getCanonicalName()+"': "+ex.getMessage(), ex) ;
        } catch (MBeanRegistrationException ex)
        {
            throw new ARQException("Failed to register '"+objName.getCanonicalName()+"': "+ex.getMessage(), ex) ;
        }
    }
}

/*
 * (c) Copyright 2009 Hewlett-Packard Development Company, LP
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