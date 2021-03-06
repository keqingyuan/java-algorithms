package com.jqc;

import com.jqc.graph.Graph;
import com.jqc.graph.ListGraph;
import com.jqc.graph.Graph.VertexVisit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.jqc.graph.Graph.EdgeInfo;
import com.jqc.graph.Graph.WeightManager;
import com.jqc.graph.Graph.PathInfo;


public class Main {

    static WeightManager<Double> weightManager = new WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return Double.compare(w1, w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    public static void main(String[] args) {
        // 测试图添加和删除
//        test1();
        // 测试广度遍历
//        testBFS();
        // 测试深度遍历
//        testDFS();
        // 测试拓扑算法
//        testTopo();
        // 测试最小生成树
//        testMst();
        // 测试最短路径
//        testSp();
        // 测试求多源最短路径
        testMultiSp();
    }

    /**
     * graph 简单图测试
     */
    static void test1() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

        graph.removeEdge("V0", "V4");
        graph.removeVertex("V0");

        graph.print();
    }


    static void testBFS(){
        // Data.BFS_01 对应img/bfs_01 无向图
//        Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
//        graph.bfs("A", new VertexVisit() {
//            @Override
//            public boolean visit(Object value) {
//                System.out.println(value);
//                return false;
//            }
//        });
        // lambuda
//        graph.bfs("A", (Object value) -> {
//            System.out.println(value);
//            return false;
//        });

        // Data.BFS_02 对应img/bfs_02 有向图
//        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
//        graph.bfs(0, (Object value) -> {
//            System.out.println(value);
//            return false;
//        });

        // Data.BFS_03 对应img/bfs_03 无向图
//        Graph<Object, Double> graph = undirectedGraph(Data.BFS_03);
//        graph.bfs(0, (Object value) -> {
//            System.out.println(value);
//            return false;
//        });

        // Data.BFS_04 对应img/bfs_04 有向图
        Graph<Object, Double> graph = directedGraph(Data.BFS_04);
        graph.bfs(5, (Object value) -> {
            System.out.println(value);
            return false;
        });

    }

    static void testDFS() {
        // Data.DFS_01 对应img/dfs_01 无向图 答案不唯一 图片中的答案仅供参考
//        Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
//        graph.dfs(1, (Object value) -> {
//            System.out.println(value);
//            return false;
//        });

        // Data.DFS_01 对应img/dfs_01 无向图 答案不唯一 图片中的答案仅供参考
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("a", (Object value) -> {
            System.out.println(value);
            return false;
        });
    }

    static void testTopo() {
        // 对应img/topo.png图
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<EdgeInfo<Object, Double>> infos = graph.mst();
        for (EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void testSp() {
        // 测试Dijkstra最短路径 对应dijkstra.png图片
//        Graph<Object, Double> graph = directedGraph(Data.SP);
//        Map<Object, PathInfo<Object, Double>> sp = graph.shortestPath("A");
//        if (sp == null) return;
//        sp.forEach((Object v, PathInfo<Object, Double> path) -> {
//            System.out.println(v + " - " + path);
//        });

        // 测试bellmanFord最短路径 无负权环 见图img/negative_weight_01.png
//        Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
//        Map<Object, PathInfo<Object, Double>> sp = graph.shortestPath("A");
//        if (sp == null) return;
//        sp.forEach((Object v, PathInfo<Object, Double> path) -> {
//            System.out.println(v + " - " + path);
//        });

        // 测试bellmanFord最短路径 有负权环 见图img/negative_weight_02.png
       Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT2);
        Map<Object, PathInfo<Object, Double>> sp = graph.shortestPath(0);
        if (sp == null) return;
        sp.forEach((Object v, PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });

    }


    static void testMultiSp() {
        Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
        Map<Object, Map<Object, PathInfo<Object, Double>>> sp = graph.shortestPath();
        sp.forEach((Object from, Map<Object, PathInfo<Object, Double>> paths) -> {
            System.out.println(from + "---------------------");
            paths.forEach((Object to, PathInfo<Object, Double> path) -> {
                System.out.println(to + " - " + path);
            });
        });

    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

}
