/*
 * (c) Copyright 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package dev;

public class DevTDB
{
    // ** metadata files and BPT creation.
    
    
    // == 0.8.2
    // Need (more) tests for symUnionDefaultGraph
    // ** Document concurrency.
    // ** Check for tests of assembler and GraphTDB
    //
    // ** Move DatasetGraphBase to ARQ [later]
    // Enable metadata? [later]
    
    // ------
    // NodeTable as ( Index<Node, NodeID>, Index<NodeId, Node> )
    //    Assumes variable length records
    //    Need better var index support first
    
    // BDB-JE? BlockMgr as index?
    
    // ** New ARQ build (arq-extra?)
    //    Commands need moving.
    //      EarlReport
    //      Update ivy
    
    // Document concurrency 
    // Graph.getLock in Jena?  Share with dataset.
    
    // ----
    
    // Clean up BPlusTree creation.  Currently need different blockmgrs for each B+T nodes, leaves. 
    // Ability to overwrite.
    
    // == tdbcheck
    //  Checking graph (with IRI cache).
    
    // == tdbdump && tdbrestore
    // FileSetMetadata - const names in sys.Names (currently in BPlusTreeParams)
    // See IndexFactoryBPlusTree.createRangeIndex
    // See BPlusTreeParams.readMeta

    // To ARQ:
    //   DatasetGraphBase
    //   Atlas? Iterator.
    
    // NodeLib.encode/decode ==> swap to a Nodec

    // Version of BufferingWriter that works on OutputStreams.

    //  Reopenable files.
    //    Alterntaive length hash codes.
    //    Record lengths
    
    //   Quad loader; dataset merge.
    //   Dataset management??
    
    // ---- Optimizer
    //   The disjunction/UNION rewrite (ARQ)
    
    // ---- Documentation
    //  Concurrency policy
    //  Change assembler page to emphasise creating a dataset. [Done?]
    
    // ---- BlockMgrs
    // Consistency - do not manage in block managers except where MRSW not safe.
    // TupleTable.size - at least an isEmpty 
    // ==> Reopenable
    // ==> .release(id)
    // ==> Accurate size (?? meaningful beyond isEmpty/notEmpty?)
    // ==> Metablocks.
    //   ==> Moveable roots.
    
    // ---- Misc
    // Inlines => Inline56, Inline64

    // ---- tdbloader: 
    //   ** (script) to work on gzip files

    // ---- 32 bit mode.
    // Different caching schemes.
}
