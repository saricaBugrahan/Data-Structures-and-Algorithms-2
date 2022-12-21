package hw1_1;
import java.util.LinkedList;
public class Graph {
     final int V = 26;//Our vertexes are char values so the max value can be 26
     LinkedList<Character>[] adj; //adj to find out the adj vertexes
    Graph()
    {
        //Initiliaze the adj and filled it with the LinkedLists.
        adj = (LinkedList<Character>[]) new LinkedList[V];
        for(int i = 0;i<V;i++)
        {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(char v,char w)
    {
        //Take the idNumbers of v and w, then insert into each other
        int vNumber = v%97;
        int wNumber = w%97;
        adj[vNumber].add(w);
        adj[wNumber].add(v);

    }
    public Iterable<Character> adj(char v)
    {
        //Find the corresponding vertex id, then return the adj of the given index.
        int vNumber = v%97;
        return adj[vNumber];
    }
}
