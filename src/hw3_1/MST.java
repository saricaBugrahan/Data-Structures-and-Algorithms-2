package hw3_1;
import java.util.Collections;
import java.util.LinkedList;
public class MST {
    private LinkedList<Edge> mst = new LinkedList<>();//Mst to store the edges
    LinkedList<Edge> edges;//All the edges
    public MST(EdgeWeightedGraph G)
    {
        edges = G.edges();
        Collections.sort(edges);//Sort the edges to start from smallest one
        UF uf = new UF(G.V());//Create Union-Find class
        while(mst.size()<G.V()-1 || edges.size()>0)
        {
            //while mst.size<0
            Edge e = edges.get(0);//Take the smallest element
            int v = e.either(), w = e.other(v);
            if(!uf.connected(v,w))//Check if they are connected
            {
                //if not connect them and add into mst
                uf.union(v,w);
                mst.add(e);
            }
            edges.removeFirst();//Then remove the smallest one.
        }
    }
    public LinkedList<Edge> getMst()
    {
        return mst;
    }

    class UF
    {
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;

        public UF(int n) {
            count = n;
            parent = new int[n];
            rank = new byte[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }


        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }
    }
}
