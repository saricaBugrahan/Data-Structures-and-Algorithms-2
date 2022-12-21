package hw2_1;

public class Algorithm {
    private boolean[] marked;//marked array to hold the visited vertexes.
    private DiGraph DiGraph;//Reference to the DiGraph in the main.java
    private String firstCourse,secondCourse;//Prerequisite question to check to vertexes.

    Algorithm(DiGraph diGraph, String firstCourse, String secondCourse)
    {
        //Initializing the values above.
        this.DiGraph = diGraph;
        this.firstCourse = firstCourse;
        this.secondCourse = secondCourse;
        marked = new boolean[diGraph.getV()];
    }
    private boolean reacheable(String firstCourse,String secondCourse)
    {
        marked[DiGraph.getSymbolTable().get(firstCourse)] = true;//mark the vertex as visited
        if(firstCourse.equals(secondCourse))//if we reached the wanted vertex return true
        {
            return true;
        }
        for(String w: DiGraph.adj(firstCourse))//Check the adj vertexes.
        {
            if(reacheable(w,secondCourse))//Call recursively
            {
                return true;//if it hits somewhere return true for all recursive calls.
            }
        }
        return false;//Otherwise return false.
    }

    public void resultOfReacheable()
    {
        //Printing phase of the Algorithm
        if(reacheable(firstCourse,secondCourse))
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }



}
