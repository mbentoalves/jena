/*
 * (c) Copyright 2006 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.sdb.graph;

import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.impl.SimpleBulkUpdateHandler;

/**
 * This is very dumb, but ensures we bulk update when we can
 * 
 * @author pldms
 *
 */

public class UpdateHandlerSDB extends SimpleBulkUpdateHandler {
	
	public UpdateHandlerSDB(GraphSDB graph)
	{
		super(graph);
	}

	@Override
    public void add(Triple[] arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.add(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
    public void add(List arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.add(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
    public void add(Iterator arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.add(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
     	public void add(Graph arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.add(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
	public void add(Graph arg0, boolean arg1) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.add(arg0, arg1); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
	public void delete(Triple[] arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.delete(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
	public void delete(List arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.delete(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
	public void delete(Iterator arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.delete(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

    @Override
	public void delete(Graph arg0) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.delete(arg0); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

	@Override
    public void delete(Graph arg0, boolean arg1) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.delete(arg0, arg1); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

	@Override
    public void remove(Node arg0, Node arg1, Node arg2) {
		try { ((GraphSDB) graph).startBulkUpdate(); super.remove(arg0, arg1, arg2); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}

	@Override
    public void removeAll() {
		try { ((GraphSDB) graph).startBulkUpdate(); super.removeAll(); }
		finally { ((GraphSDB) graph).finishBulkUpdate(); }
	}
	
	
	
}

/*
 * (c) Copyright 2006 Hewlett-Packard Development Company, LP
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