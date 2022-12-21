package hw3_1;

public class Edge implements Comparable{
    private final int v,w;//Vertexes v,w
    private  final double weight;//Weight of the Edge

    Edge(int v,int w,double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double getWeight()
    {
        return weight;
    }

    public int either()
    {
        return v;
    }
    public int other(int vertex)
    {
        if(vertex == v) return w;
        else    return v;
    }


    @Override
    public int compareTo(Object o) {
        //Compare them according to their edge weight
        if (this.weight<((Edge) o).weight)
            return -1;
        else if(this.weight>((Edge) o).weight)
            return 1;
        else
            return 0;


    }
}
