/*
 *  (c) Copyright Hewlett-Packard Company 1999-2001 
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
 *
 * $Id: Map1Iterator.java,v 1.2 2003-03-26 12:08:05 chris-dollin Exp $
 *
 */

package com.hp.hpl.jena.util.iterator;


//Map1Iterator.java
import java.util.Iterator;
/**
 * Convert objects as they are iterated over.
 * @author jjc
 * @version  Release='$Name: not supported by cvs2svn $' Revision='$Revision: 1.2 $' Date='$Date: 2003-03-26 12:08:05 $'
 */
public class Map1Iterator extends NiceIterator implements ClosableIterator
{
	Map1 map;
	Iterator underlying;
        /**
         * Construct a list of the converted.
         * @param m The conversion to apply.
         * @param theDamned An Iterator over the unconverted.
         */
	public Map1Iterator( Map1 m, Iterator iterator) {
		underlying = iterator;
		map = m;
	}
	public boolean hasNext() {
			   return underlying.hasNext();
	}
	public Object next() {
		return map.map1(underlying.next());
	}
    /**
     * <code>remove()</code> the unconverted object from the underlying Iterator.
     *
     */
    public void remove() {
      underlying.remove();
    }
    public void close() {
    	WrappedIterator.close( underlying );
    }

}
