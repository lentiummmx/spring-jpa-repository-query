package org.hibernate.jpa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class QueryHints {

    /**
     * For to use to writing hint plans that contains hinting phrases. Hint phrases consist of hint
     * names followed by hint parameters enclosed in parentheses and delimited by spaces. <br>
     * <code>
     * yugabyte=# &#47;*+ <br>
     * yugabyte*#    HashJoin(a b) <br>
     * yugabyte*#    SeqScan(a) <br>
     * yugabyte*#  *&#47; <br>
     * </code>
     */
    public static final String HINT_CUSTOM_QUERY_HINT = "custom_query_hint";

    /*
     * Hints for scan methods - see:
     * https://docs.yugabyte.com/preview/explore/query-1-performance/pg-hint-plan/#hints-for-scan-
     * methods
     */

    /**
     * SeqScan(table) - Enable SeqScan on the table.
     */
    public static final String HINT_SEQ_SCAN = "seq_scan";

    /**
     * NoSeqScan(table) - Do not enable SeqScan on the table.
     */
    public static final String HINT_NO_SEQ_SCAN = "no_seq_scan";

    /**
     * IndexScan(table) - Enable IndexScan on the table. <br>
     * IndexScan(table idx) - Enable IndexScan on the table using the index idx.
     */
    public static final String HINT_INDEX_SCAN = "index_scan";

    /**
     * IndexScan(table idx) - Enable IndexScan on the table using the index idx.
     */
    @Deprecated
    public static final String HINT_INDEX_SCAN_IDX = "index_scan_idx";

    /**
     * NoIndexScan(table) - Do not enable IndexScan on the table.
     */
    public static final String HINT_NO_INDEX_SCAN = "no_index_scan";

    /**
     * IndexOnlyScan(table) - Enable IndexOnlyScan on the table.
     */
    public static final String HINT_INDEX_ONLY_SCAN = "index_only_scan";

    /**
     * NoIndexOnlyScan(table) - Do not enable IndexOnlyScan on the table.
     */
    public static final String HINT_NO_INDEX_ONLY_SCAN = "no_index_only_scan";

    /**
     * IndexScanRegexp(table regex) - Enable index scan on the table whose indices match with the
     * regular expression defined by regex.
     */
    public static final String HINT_INDEX_SCAN_REGEXP = "index_scan_regexp";

    /**
     * IndexOnlyScanRegexp(table regex) - Do not enable index scan on the table whose indices match with
     * the regular expression defined by regex.
     */
    public static final String HINT_INDEX_ONLY_SCAN_REGEXP = "index_only_scan_regexp";

    /*
     * Hints for join methods - see:
     * https://docs.yugabyte.com/preview/explore/query-1-performance/pg-hint-plan/#hints-for-join-
     * methods
     */

    /**
     * HashJoin(t1 t2 t3 ...) - Join t1, t2, and t3 using HashJoin.
     */
    public static final String HINT_HASH_JOIN = "hash_join";

    /**
     * NoHashJoin(t1 t2 t3 ...) - Do not join t1, t2, and t3 using HashJoin.
     */
    public static final String HINT_NO_HASH_JOIN = "no_hash_join";

    /**
     * NestLoop(t1 t2 t3 ...) - Join t1, t2, and t3 using NestLoop join.
     */
    public static final String HINT_NEST_LOOP = "nest_loop";

    /**
     * NoNestLoop(t1 t2 t3 ...) - Do not join t1, t2, and t3 using NestLoop join.
     */
    public static final String HINT_NO_NEST_LOOP = "no_nest_loop";

    /*
     * Hints for joining order - see:
     * https://docs.yugabyte.com/preview/explore/query-1-performance/pg-hint-plan/#hints-for-joining-
     * order
     */

    /**
     * Leading(t1 t2 t3) - Enforce joining in a specific order <br>
     * Leading(t2 t3 t1)
     */
    public static final String HINT_LEADING = "leading";

    /*
     * Setting working memory - see:
     * https://docs.yugabyte.com/preview/explore/query-1-performance/pg-hint-plan/#setting-working-
     * memory
     */

    /**
     * Set(work_mem "1MB") - Leverage the <i>work_mem</i> setting in PostgreSQL to improve the
     * performance of slow queries that sort, join, or aggregate large sets of table rows.
     */
    public static final String HINT_WORK_MEM = "work_mem";

    /*
     * Configuring the planner method - see:
     * https://docs.yugabyte.com/preview/explore/query-1-performance/pg-hint-plan/#configuring-the-
     * planner-method,
     * https://www.postgresql.org/docs/11/runtime-config-query.html#RUNTIME-CONFIG-QUERY-ENABLE
     */

    /**
     * Set(enable_hashagg off) - Enables or disables the query planner's use of hashed aggregation plan
     * types. The default is <i>on</i>. <br>
     * Set(enable_hashagg on)
     */
    public static final String HINT_ENABLE_HASH_AGG = "enable_hash_agg";

    /**
     * Set(enable_hashjoin off) - Enables or disables the query planner's use of hash-join plan types.
     * The default is <i>on</i>. <br>
     * Set(enable_hashjoin on)
     */
    public static final String HINT_ENABLE_HASH_JOIN = "enable_hash_join";

    /**
     * Set(enable_indexscan off) - Enables or disables the query planner's use of index-scan plan types.
     * The default is <i>on</i>. <br>
     * Set(enable_indexscan on)
     */
    public static final String HINT_ENABLE_INDEX_SCAN = "enable_index_scan";

    /**
     * Set(enable_indexonlyscan off) - Enables or disables the query planner's use of index-only-scan
     * plan types. The default is <i>on</i>. <br>
     * Set(enable_indexonlyscan on)
     */
    public static final String HINT_ENABLE_INDEX_ONLY_SCAN = "enable_index_only_scan";

    /**
     * Set(enable_material off) - Enables or disables the query planner's use of materialization. It is
     * impossible to suppress materialization entirely, but turning this variable off prevents the
     * planner from inserting materialize nodes except in cases where it is required for correctness.
     * The default is <i>on</i>. <br>
     * Set(enable_material on)
     */
    public static final String HINT_ENABLE_MATERIAL = "enable_material";

    /**
     * Set(enable_nestloop off) - Enables or disables the query planner's use of nested-loop join plans.
     * It is impossible to suppress nested-loop joins entirely, but turning this variable off
     * discourages the planner from using one if there are other methods available. The default is
     * <i>on</i>. <br>
     * Set(enable_nestloop on)
     */
    public static final String HINT_ENABLE_NEST_LOOP = "enable_nest_loop";

    /**
     * Set(enable_partition_pruning off) - Enables or disables the query planner's ability to eliminate
     * a partitioned table's partitions from query plans. This also controls the planner's ability to
     * generate query plans which allow the query executor to remove (ignore) partitions during query
     * execution. The default is <i>on</i>. <br>
     * Set(enable_partition_pruning on)
     */
    public static final String HINT_ENABLE_PARTITION_PRUNING = "enable_partition_pruning";

    /**
     * Set(enable_partitionwise_join on) - Enables or disables the query planner's use of partitionwise
     * join, which allows a join between partitioned tables to be performed by joining the matching
     * partitions. Partitionwise join currently applies only when the join conditions include all the
     * partition keys, which must be of the same data type and have exactly matching sets of child
     * partitions. Because partitionwise join planning can use significantly more CPU time and memory
     * during planning, the default is <i>off</i>. <br>
     * Set(enable_partitionwise_join off)
     */
    public static final String HINT_ENABLE_PARTITION_WISE_JOIN = "enable_partition_wise_join";

    /**
     * Set(enable_partitionwise_aggregate on) - Enables or disables the query planner's use of
     * partitionwise grouping or aggregation, which allows grouping or aggregation on a partitioned
     * tables performed separately for each partition. If the GROUP BY clause does not include the
     * partition keys, only partial aggregation can be performed on a per-partition basis, and
     * finalization must be performed later. Because partitionwise grouping or aggregation can use
     * significantly more CPU time and memory during planning, the default is <i>off</i>. <br>
     * Set(enable_partitionwise_aggregate off)
     */
    public static final String HINT_ENABLE_PARTITION_WISE_AGGREGATE = "enable_partition_wise_aggregate";

    /**
     * Set(enable_seqscan off) - Enables or disables the query planner's use of sequential scan plan
     * types. It is impossible to suppress sequential scans entirely, but turning this variable off
     * discourages the planner from using one if there are other methods available. The default is
     * <i>on</i>. <br>
     * Set(enable_seqscan on)
     */
    public static final String HINT_ENABLE_SEQ_SCAN = "enable_seq_scan";

    /**
     * Set(enable_sort off) - Enables or disables the query planner's use of explicit sort steps. It is
     * impossible to suppress explicit sorts entirely, but turning this variable off discourages the
     * planner from using one if there are other methods available. The default is <i>on</i>. <br>
     * Set(enable_sort on)
     */
    public static final String HINT_ENABLE_SORT = "enable_sort";

    private static final Set<String> HINTS = buildHintsSet();

    private static Set<String> buildHintsSet() {
        HashSet<String> hints = new HashSet<String>();
        hints.add(HINT_CUSTOM_QUERY_HINT);
        hints.add(HINT_SEQ_SCAN);
        hints.add(HINT_NO_SEQ_SCAN);
        hints.add(HINT_INDEX_SCAN);
        hints.add(HINT_INDEX_SCAN_IDX);
        hints.add(HINT_NO_INDEX_SCAN);
        hints.add(HINT_INDEX_ONLY_SCAN);
        hints.add(HINT_NO_INDEX_ONLY_SCAN);
        hints.add(HINT_INDEX_SCAN_REGEXP);
        hints.add(HINT_INDEX_ONLY_SCAN_REGEXP);
        hints.add(HINT_HASH_JOIN);
        hints.add(HINT_NO_HASH_JOIN);
        hints.add(HINT_NEST_LOOP);
        hints.add(HINT_NO_NEST_LOOP);
        hints.add(HINT_LEADING);
        hints.add(HINT_WORK_MEM);
        hints.add(HINT_ENABLE_HASH_AGG);
        hints.add(HINT_ENABLE_HASH_JOIN);
        hints.add(HINT_ENABLE_INDEX_SCAN);
        hints.add(HINT_ENABLE_INDEX_ONLY_SCAN);
        hints.add(HINT_ENABLE_MATERIAL);
        hints.add(HINT_ENABLE_NEST_LOOP);
        hints.add(HINT_ENABLE_PARTITION_PRUNING);
        hints.add(HINT_ENABLE_PARTITION_WISE_JOIN);
        hints.add(HINT_ENABLE_PARTITION_WISE_AGGREGATE);
        hints.add(HINT_ENABLE_SEQ_SCAN);
        hints.add(HINT_ENABLE_SORT);
        return Collections.unmodifiableSet(hints);
    }

    public static Set<String> getDefinedHints() {
        return HINTS;
    }

    protected QueryHints() {}
}
