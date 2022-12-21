package hw3_2;

import java.util.HashMap;
import java.util.LinkedList;

public class EdgeWeightedDigraph
{
    private final int V;//Vertex count
    private final LinkedList<DirectedEdge>[] adj;//Adjecency List
    HashMap<Integer,Integer> seatVertex;//Vertex and avaliable seats hash map
    public EdgeWeightedDigraph(int V,int[] avaliability)
    {
        seatVertex = new HashMap<>();
        this.V = V;
        adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++)
        {
            adj[v] = new LinkedList<>();//Create empty linkedlist in the indexes.
            if(v ==0)
            {
                seatVertex.put(0,0);//If it's zero we are in the beginning vertex which is the campus
                continue;
            }
            seatVertex.put(v,avaliability[v-1]);//if it's not 0 we are taking the seats values.
        }

    }
    public void addEdge(DirectedEdge e)
    {
        int v = e.from();//Add Edge method
        adj[v].add(e);
    }
    public Iterable<DirectedEdge> adj(int v)
    { return adj[v]; }
    public int getV() {
        return V;
    }
}
