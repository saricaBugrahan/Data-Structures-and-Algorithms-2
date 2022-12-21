package hw2_1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int courseCount = scan.nextInt();//Total number of vertexes.
        String[] courses = scan.next().split(",");//Split with the '-' and put it into the array
        DiGraph diGraph = new DiGraph(courseCount,courses);//Create an instance of DiGraph
        int numberOfPrerequisite = scan.nextInt();//Take the numberOfPrerequisite from keyboard
        for(int i = 0;i<numberOfPrerequisite;i++)
        {
            String[] temp = scan.next().split("-");
            diGraph.addEdge(temp[0],temp[1]);//addEdge with the given indexes.
        }
        String[] question = scan.next().split("-");//Prerequisite question.
        Algorithm algorithm = new Algorithm(diGraph,question[0],question[1]);//Create instance of Algorithm
        algorithm.resultOfReacheable();//Result of the Algorithm


    }
}
