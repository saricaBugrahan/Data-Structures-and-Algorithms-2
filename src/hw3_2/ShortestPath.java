package hw3_2;
public class ShortestPath {
    private double[] distTo;        //Distance between the source and given index
    private DirectedEdge[] edgeTo;   //Parent of the given edge
    private IndexMinPQ<Double> pq;    //Priority queue for Dijkstra

    public ShortestPath(EdgeWeightedDigraph G, int s) {

        //Initiliaze the values distTo and edgeTo
        distTo = new double[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];

        for (int v = 0; v < G.getV(); v++)
            distTo[v] = Double.POSITIVE_INFINITY; //Insert distTo with the max value
        distTo[s] = 0.0; //Initiliaze the source as 0


        pq = new IndexMinPQ<>(G.getV());//Initiliaze the pq
        pq.insert(s, distTo[s]);//Insert the source
        while (!pq.isEmpty()) {
            int v = pq.delMin();//Take the minimum edge
            for (DirectedEdge e : G.adj(v))
                relax(e);//Relax it's neighbors
        }
    }
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        //if our distance is lower than edgeWeight and distTo value of the index we are changing the distTo and edgeTo variable.
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }
    //Returns the distance between source and destination.
    public double distTo(int v) {
        return distTo[v];
    }
    //-----------------------------------------------------
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