package hw2_2;

import java.util.LinkedList;

public class Graph {
    private int V;//Vertex count
    LinkedList<SOS>[] adj;//Adjecent vertexes

    Graph(int V)
    {
        this.V = V;
        adj =(LinkedList<SOS>[]) new LinkedList[V];
        for(int i = 0;i<V;i++)
        {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(SOS a, SOS b)
    {
        adj[a.rep].add(b);//adding processes
        adj[b.rep].add(a);
    }
    public Iterable<SOS> adj(SOS a)
    {
        return adj[a.rep];
    }
    public int getV()
    {
        return this.V;
    }


}
