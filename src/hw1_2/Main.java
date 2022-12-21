package hw1_2;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
            Graph G = new Graph();
            Scanner scan = new Scanner(System.in);
            String arr[] = scan.nextLine().split(",");
            for(int i = 0;i<arr.length;i++)
            {
                    G.addEdge(arr[i].charAt(0),arr[i].charAt(2));//In our array values zeroth and second indexes are the values that we needed.
            }
            Algorithm alg  =new Algorithm(G,scan.next().charAt(0),scan.next().charAt(0),scan.nextInt());
            alg.printPath();
    }
}
