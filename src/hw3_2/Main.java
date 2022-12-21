package hw3_2;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfAuditorium = scan.nextInt();//Number of places
        int numberOfConnections = scan.nextInt();//Number of connections
        int distanceFromCampusToEntrance = scan.nextInt();//Distance between campus and first auditorium
        LinkedList<Integer> shortestPaths = new LinkedList<>();
        int[] numberOfSeats = new int[numberOfAuditorium];
        LinkedList<Boolean> temp = new LinkedList<>();
        for(int i = 0;i<numberOfAuditorium;i++)
        {
            numberOfSeats[i] = scan.nextInt();//Hold the number of seats values in an array
        }
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(numberOfAuditorium+1,numberOfSeats);//Create graph with the vertexes and numberofSeats array
        for(int i = 0;i<numberOfConnections;i++)
        {
            digraph.addEdge(new DirectedEdge(scan.nextInt(),scan.nextInt(),scan.nextInt()));//Make the connections.
        }
        digraph.addEdge(new DirectedEdge(0,1,distanceFromCampusToEntrance));//Add another edge which is campus
        int numberOfStudents = scan.nextInt();//Number of students
        ShortestPath path = new ShortestPath(digraph,0);//Run dijkstra from the vertex 0

        for(int i = 0;i<digraph.getV();i++)
        {
             shortestPaths.add(i,(int)path.distTo(i));//Add the values of the shortest paths
             temp.add(false);//add all them zero
        }
        int max = findMax(shortestPaths);//Find the max value in the shortest path
        int counter = 0;
        //While counter is not equal to the number of students
        while (counter != numberOfStudents)
        {
            //Find the min index
            int index=  findMinimumIndex(shortestPaths);
            int seatCount = 0;
            //If it's contains false that means there are available seats in the auditoriums
            if(temp.contains(false))
            {
                //If the auditoriums count not less than seatcount
                while(digraph.seatVertex.get(index)>seatCount)
                {
                    //Print the path weight
                    System.out.print(shortestPaths.get(index)+" ");
                    seatCount++;//Increase counter and seatcount
                    counter++;
                }
            }
            else
            {
                //If all of them is true there are no avaliable seats so that put -1 and increase counter.
                System.out.print(-1+" ");
                counter++;
            }
            temp.set(index,true);//To avoid the shifting delete the element and replace with true
            shortestPaths.set(index,max+1);//Same idea here, to find the next smallest path we are replacing the element with the max +1 value.
        }
    }
    public static int findMinimumIndex(LinkedList<Integer> s)
    {
        //This method gives the minimum path weight index.
        int min =0;
        for(int i = 0;i<s.size();i++)
        {
            if(s.get(min)>s.get(i))
            {
                min = i;
            }
        }
        return min;
    }
    public static int findMax(LinkedList<Integer> s)
    {
        //This method gives us the maximum path weight.
        int max = s.get(0);
        for(int i = 0;i<s.size();i++)
        {
            if(s.get(i)>max)
            {
                max = s.get(i);
            }
        }
        return max;
    }
}
