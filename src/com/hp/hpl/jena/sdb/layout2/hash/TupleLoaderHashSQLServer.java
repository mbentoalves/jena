package com.hp.hpl.jena.sdb.layout2.hash;

import com.hp.hpl.jena.sdb.layout2.TableDescNodes;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.TableDesc;

public class TupleLoaderHashSQLServer extends Layout2TupleLoaderHashBase {

	public TupleLoaderHashSQLServer(SDBConnection connection, TableDesc tableDesc,
			int chunkSize) {
		super(connection, tableDesc, chunkSize);
	}
	
	public String[] getNodeColTypes() {
		return new String[] {"BIGINT", "VARCHAR", "VARCHAR(10)", "VARCHAR("+ TableDescNodes.DatatypeUriLength+ ")", "INT"};
	}
	
	public String getTupleColType() {
		return "BIGINT";
	}
	
	public String[] getCreateTempTable() {
		return new String[] { "CREATE TABLE" , "" };
	}
	
	@Override
	public String getNodeLoader() {
		// In SQL Server temp tables start with a #
		return "#"+super.getNodeLoader();
	}

	@Override
	public String getTupleLoader() {
		// In SQL Server temp tables start with a #
		return "#"+super.getTupleLoader();
	}
}
