package hw3_2;
public class DirectedEdge
{
    private final int v, w;//v and w vertexes
    private final double weight;//Weight of the edge
    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int from()
    { return v; }
    public int to()
    { return w; }
    public double weight()
    { return weight; }
}