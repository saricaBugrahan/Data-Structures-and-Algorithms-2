package hw2_2;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void changeIndexes(SOS[][] arr, LinkedList<Integer> indexes)
    {
        //This method changes the chars in the 2d array according to the indexes.
        for(int i =0;i<indexes.size();i++)
        {
            int row = indexes.get(i)/arr.length;
            int col = indexes.get(i)%arr[0].length;
            arr[row][col].setName('X');
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SOS[][] graphNodes = new SOS[scan.nextInt()][scan.nextInt()];//Row and column number
        String bos = scan.nextLine();
        for(int i = 0;i<graphNodes.length;i++)
        {

            String temp = scan.nextLine();
            temp = temp.replaceAll("\\s","");
            for(int j = 0;j<temp.length();j++)
            {
                graphNodes[i][j] = new SOS(temp.charAt(j));//Put the input into 2d array
            }
        }
        Graph graph = new Graph(graphNodes.length*graphNodes[0].length);

        for(int i = 0;i<graphNodes.length;i++)
        {
            for(int j = 0;j<graphNodes[0].length;j++)
            {
                //Insert the 2d array in undirected graph
                if(i != graphNodes.length-1)
                {
                    if(j != graphNodes.length-1)
                    {
                        graph.addEdge(graphNodes[i][j],graphNodes[i][j+1]);
                        graph.addEdge(graphNodes[i][j],graphNodes[i+1][j]);
                    }
                    else
                        graph.addEdge(graphNodes[i][j],graphNodes[i+1][j]);
                }
                else
                {
                        if(j != graphNodes[0].length-1)
                        {
                            graph.addEdge(graphNodes[i][j],graphNodes[i][j+1]);
                        }
                }
            }
        }

        Algorithm algorithm = new Algorithm(graph,graphNodes[0][0]);//Start from the beginning vertex
        LinkedList<Integer> indexes = algorithm.getChangedCharactersIndexes();//Take the changed list.
        changeIndexes(graphNodes,indexes);//Change the indexes with the above method.
        for(int i = 0;i<graphNodes.length;i++)
        {
            //Print out the graph
            for(int j = 0;j<graphNodes[0].length;j++)
            {
                System.out.print(graphNodes[i][j].name+" ");
            }
            System.out.println();
        }

    }
}
