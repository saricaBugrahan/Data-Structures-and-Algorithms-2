package hw3_1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfStudent = scan.nextInt();//Number of students
        int numberOfConnections = scan.nextInt();//Number of connections
        int numberOfQuestions = scan.nextInt();//Number of queries

        EdgeWeightedGraph graph = new EdgeWeightedGraph(numberOfStudent+1);//Creating graph(+1 is for the vertex 0).There is no student with 0
        for(int i = 0;i<numberOfConnections;i++)
        {
            //Connect the graph with the edges
            graph.addEdge(new Edge(scan.nextInt(),scan.nextInt(),scan.nextInt()));
        }

        for(int i = 0;i<numberOfQuestions;i++)
        {
            //Run Dijkstra for all the queries again and again.
            DijkstraSP algorithm = new DijkstraSP(graph,scan.nextInt());
            System.out.println((int)algorithm.distTo(scan.nextInt()));
        }



    }
}
