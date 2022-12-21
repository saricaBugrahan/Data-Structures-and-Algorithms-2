package hw1_1;//-----------------------------------------------------
// Title: Main(Tester) Class
// Author: Buğrahan Kadir Sarıca
// ID: 1102006051
// Section: 1
// Assignment: 1
// Description: This class has main method inside. It reads the input from the console and run the necessary methods and print out the cycle
//-----------------------------------------------------
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scan = new Scanner(System.in);//Take the input file
        String arr[] = scan.nextLine().split(",");//Split them accordingly to the ','
        for(int i = 0;i<arr.length;i++)
        {
            graph.addEdge(arr[i].charAt(0),arr[i].charAt(2));//In our array values zeroth and second indexes are the values that we needed.
        }
        DFS dfs = new DFS(graph,scan.next().charAt(0));//Create our dfs method accordingly to the input.
        dfs.Order();//Make the arrangement of the cycle if it's exist.






    }
}
