package hw3_1;

public class DijkstraSP {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private Edge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices
    private MST mst;
    public DijkstraSP(EdgeWeightedGraph G, int s) {
        mst = new MST(G);

        EdgeWeightedGraph newGraph = new EdgeWeightedGraph(G.V());
        for (Edge e:mst.getMst()) {
            newGraph.addEdge(e);
        }
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;


        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : newGraph.adj(v))
                relax(e);
        }
    }
    private void relax(Edge e) {
        int v = e.either(), w = e.other(e.either());
        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
        else if(distTo[v]>distTo[w]+e.getWeight())
        {
            distTo[v] = distTo[w] +e.getWeight();
            edgeTo[v] = e;
            if (pq.contains(v)) pq.decreaseKey(v, distTo[v]);
            else pq.insert(v, distTo[v]);
        }
    }
    public double distTo(int v) {
        return distTo[v];
    }

    public class IndexMinPQ<Key extends Comparable<Key>> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq = new int[maxN + 1];
            qp = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        public boolean isEmpty() {
            return n == 0;
        }



        public boolean contains(int i) {
            return qp[i] != -1;
        }



        public void insert(int i, Key key) {
            if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }



        public int delMin() {
            int min = pq[1];
            exch(1, n--);
            sink(1);
            assert min == pq[n + 1];
            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n + 1] = -1;        // not needed
            return min;
        }


        public void changeKey(int i, Key key) {


            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }


        @Deprecated
        public void change(int i, Key key) {
            changeKey(i, key);
        }


        public void decreaseKey(int i, Key key) {
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) < 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
            keys[i] = key;
            swim(qp[i]);
        }







        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }


        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }



    }

}