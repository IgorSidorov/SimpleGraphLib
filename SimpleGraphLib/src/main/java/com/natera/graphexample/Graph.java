package com.natera.graphexample;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple graph lib
 * @author SidorovIV
 * @ created 31.05.2020
 * @ 1$
 */
public class Graph<T> {

    private HashMap<Vertex, List<Vertex>> adjVertices = new HashMap<>();
    private HashMap<T, Vertex<T>> vertices = new HashMap<>();

    public void addVertex(T label) {
        Vertex<T> vertex = new Vertex(label);
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
        vertices.putIfAbsent(label, vertex);
    }

    private List<Vertex> getAdjVertices(T label) {
        return adjVertices.get(new Vertex(label));
    }

    /**
     * Create edge between 2 vertices
     * without direction
     *
     * @param label1 first vertex
     * @param label2 second vertex
     */
    public void addEdge(T label1, T label2) {
        Vertex v1 = vertices.get(label1);
        Vertex v2 = vertices.get(label2);

        Edge edge = new Edge(v1, v2);
        updateVertices(v1, v2, edge, label1);
        updateVertices(v2, v1, edge, label2);
    }

    private void updateVertices(Vertex<T> v1, Vertex<T> v2, Edge edge, T label) {
        v1.setToUndirectedEdgeList(edge);
        updateContainers(v1, v2, label);
    }

    private void updateContainers(Vertex<T> v1, Vertex<T> v2, T label) {
        List<Vertex> vertexList = adjVertices.get(v1);
        vertexList.add(v2);
        adjVertices.put(v1, vertexList);
        vertices.put(label, v1);
    }

    /**
     * create Edge with direction
     * @param startingLabel
     * @param endingLabel
     */
    public void addEdgeWithDirection(T startingLabel, T endingLabel) {
        Vertex v1 = vertices.get(startingLabel);
        Vertex v2 = vertices.get(endingLabel);
        Edge edge = new Edge(v1, v2);
        v1.addToOutcomeEdgeList(edge);
        v2.addToIncomeEdgeList(edge);
        updateContainers(v1, v2, startingLabel);
        updateContainers(v2, v1, endingLabel);
    }

    /**
     * path it is list of edges between two vertices
     * not optimal
     * @param startingLabel starting vertex
     * @param endLabel target vertex
     * @return List of edges between two vertices
     */
    public List<Edge> getPath(T startingLabel, T endLabel) {
        Vertex<T> targetVertex = vertices.get(endLabel);
        Set<T> visited = new LinkedHashSet();
        Stack<T> stack = new Stack();
        Set<Edge> edgeList = new HashSet(vertices.size());
        stack.push(startingLabel);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            List<Vertex> vertexList = getAdjVertices(vertex);
            edgeList.add(getEdge(vertices.get(startingLabel), vertices.get(vertex)));
            startingLabel = vertex; //swap
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex<T> v : vertexList) {
                    if (v.equals(targetVertex)) {
                        edgeList.add(getEdge(vertices.get(startingLabel), v));
                        return edgeList.stream().filter(edge -> edge != null).collect(Collectors.toList());
                    }
                    stack.push(v.getLabel());
                }
            }
        }
        return edgeList.stream().filter(edge -> edge != null).collect(Collectors.toList());
    }

    private Edge getEdge(Vertex<T> v1, Vertex<T> v2) {
        if (v1.equals(v2)) {
            return null;
        } else if (v1.getUndirectedEdgeList() != null && v2.getUndirectedEdgeList() != null) { //undirected Graph
            for (Edge edge : v1.getUndirectedEdgeList()) {
                if (v2.getUndirectedEdgeList().contains(edge)) {
                    return edge;
                }
            }
        } else if (v1.getIncomeEdgeList() != null || v1.getOutcomeEdgeList() != null) { //directed Graph
            return getDirectedEdge(v1, v2);
        }
        return null;
    }

    private Edge getDirectedEdge(Vertex<T> v1, Vertex<T> v2) {
        for (Edge edge : v1.getOutcomeEdgeList()) {
            if (v2.getIncomeEdgeList().contains(edge)) {
                return edge;
            }
        }
        for (Edge edge : v1.getIncomeEdgeList()) {
            if (v2.getOutcomeEdgeList().contains(edge)) {
                return edge;
            }
        }
        return null;
    }
}
