/* Generated By:JJTree: Do not edit this line. Q_QName.java */

package com.hp.hpl.jena.rdql.parser;
import com.hp.hpl.jena.rdql.* ;

public class Q_QName extends Q_URI
{
    // The form actually coming from the parser.
    String seen = "";
    String prefix = null ;
    String lcname = null  ;
    
    public Q_QName(int id)
    {
        super(id);
    }

    public Q_QName(RDQLParser p, int id)
    {
        super(p, id);
    }

    void set(String s)
    {
        seen = s ;
    }
    
    public void jjtClose()
    {
        if ( jjtGetNumChildren() != 2 )
            throw new RDQL_InternalErrorException("Q_QName: expected 2 children: got "+jjtGetNumChildren()) ;
        prefix = ((Q_Identifier)jjtGetChild(0)).id ;
        lcname = ((Q_Identifier)jjtGetChild(1)).id ;
        seen = prefix+":"+lcname ;
        //super.setURI(seen) ;
    }

    public void fixup(Q_Query qnode)
    {
        
        if ( isRDFResource() )
            // Already done.
            return ;
        String full = qnode.getPrefix(prefix) ;

        if ( full == null )
            throw new QueryException("Query error: QName '"+seen+"' can not be expanded.") ;

        super.setRDFResource(model.createResource(full+lcname)) ;
    }
    


    
    public String asQuotedString()    { return seen ; }
    
    public String asUnquotedString()  { return seen ; }
    // Must return the expanded form
    public String valueString()       { return super.getURI() ; }

    public String toString() { return seen ; }
}
