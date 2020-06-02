package com.natera.graphexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Vertex of graph
 *
 * @author SidorovIV
 * @ created 31.05.2020
 * @ 1$
 */
public class Vertex<T> {

    private T label;
    private List<Edge> incomeEdgeList;
    private List<Edge> outcomeEdgeList;
    private List<Edge> undirectedEdgeList;

    public Vertex(T label) {
        this.label = label;
    }

    public List<Edge> getIncomeEdgeList() {
        return incomeEdgeList;
    }

    public void addToIncomeEdgeList(Edge edge) {
        if (incomeEdgeList == null) {
            incomeEdgeList = new ArrayList<>();
        }
        incomeEdgeList.add(edge);
    }

    public List<Edge> getOutcomeEdgeList() {
        return outcomeEdgeList;
    }

    public void addToOutcomeEdgeList(Edge edge) {
        if (outcomeEdgeList == null) {
            outcomeEdgeList = new ArrayList<>();
        }
        outcomeEdgeList.add(edge);
    }

    public List<Edge> getUndirectedEdgeList() {
        return undirectedEdgeList;
    }

    public void setToUndirectedEdgeList(Edge edge) {
        if (undirectedEdgeList == null) {
            undirectedEdgeList = new ArrayList<>();
        }
        undirectedEdgeList.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public T getLabel() {
        return label;
    }
}
