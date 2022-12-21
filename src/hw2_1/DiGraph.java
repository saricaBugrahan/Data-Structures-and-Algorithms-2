package hw2_1;
import java.util.HashMap;
import java.util.LinkedList;
public class DiGraph {
    private int V;//Our vertexes are char values so the max value can be 26
    private LinkedList<String>[] adj; //adj to find out the adj vertexes
    private HashMap<String,Integer> symbolTable;
    DiGraph(int vertexCount,String[] vertexes)
    {
        symbolTable = new HashMap<>();//Symbol table representation of the Digraph
        V= vertexCount;
        adj = (LinkedList<String>[]) new LinkedList[V];
        for(int i = 0;i<V;i++)
        {
            symbolTable.putIfAbsent(vertexes[i],i);//Put integer values to the Hashmap if it's absent according to the input
            adj[i] = new LinkedList<>();//Initiliaze the adj and filled it with the LinkedLists.
        }
    }
    public void addEdge(String v,String w)
    {
        //Take the the corresponding value from symbol table and add the String value
        adj[symbolTable.get(v)].add(w);


    }
    public Iterable<String> adj(String v)
    {
        //Find the corresponding vertex id from symbolTable, then return the adj of the given index.
        return adj[symbolTable.get(v)];
    }
    public int getV()
    {
        return this.V;//Return the current vertex number.
    }
    public HashMap<String,Integer> getSymbolTable()
    {
        return symbolTable;//Return the hashmap
    }
}
