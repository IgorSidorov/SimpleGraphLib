package com.natera.graphexample;

import java.util.Objects;

/**
 * Edge og Graph
 * @author SidorovIV
 * @ created 31.05.2020
 * @ 1$
 */
public class Edge {
    private Vertex v1;
    private Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(v1, edge.v1) &&
                Objects.equals(v2, edge.v2);
    }
}
