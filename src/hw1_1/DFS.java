package hw1_1;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private boolean marked[]; //Storing the information of a vertex whether it's visited or not.
    private char edgeTo[]; //Storing the parent of the vertex.
    private char cycle_start,cycle_end;//If we find a cycle we need to assign these values to find out the path.
    private Stack<Character> stack;//To find out the all path I used stack to store
    private char start;//Cycle starting vertex. I started the beginning vertex as the vertex that contained in a cycle.
    private Graph G;//We need an reference to the graph.
    private int count;//We need to find out how many items in the path, if it's exist.
    //private boolean ordinaryLoop = true;
    //private boolean reverseLoop = true;


    public DFS(Graph G, char start)
    {
        //Initializing the values.
        this.G = G;
        this.start = start;
        marked = new boolean[G.V];
        edgeTo = new char[G.V];
        stack = new Stack<>();
    }
    private boolean dfs(Graph G, char v, char parent)
    {
        int ID = v%97;//Take the vertex id.
        marked[ID] = true;//Marked them as true because we visited.
        for(char w: G.adj(v))//Check for it's adj vertexes.
        {
            int wID = w%97; //Adj vertex id.
            int parentID = parent%97;//Parent vertex id
            if(wID == parentID) //We need to check whether the adj vertex parent or not. If it's parent skip it.
                continue;
            //If there is any adj vertex that is marked and it's not a parent,we find a cycle
            if(marked[wID])
            {
                cycle_start = w;//Initialize the start value and end value
                cycle_end = v;
                return true; //If it hit's there return true
            }
            //We are keeping the track of where we reached this vertex.
            edgeTo[wID] = v;
            //We continue until we hit the marked[wID] is true or all the vertexes is visited and there is no cycle.
            if(dfs(G,w,edgeTo[wID]))
            {
                return true;
            }
        }
        return false;//If there is no cycle return false.
    }

    public void Order()
    {

        //If our dfs method returns true find the all the vertexes that is inside the path.
        if(dfs(G,start,start))
        {

            char end = cycle_end; //Helper char variable
            while (end!=cycle_start)//We are backtracking until we hit the starting value
            {
                stack.push(end);//Pushing inside of a stack.
                end = edgeTo[end%97];//Taking the parent of the end value until it's equal to the start
                count++;//Increase the count. Later we will use this to create a char array.
            }
            stack.push(cycle_start);//Additionaly we need to push the cycle_start value.
            count++;
            char arr[] = new char[count];
            for(int i = 0;i<arr.length;i++)
            {
                arr[i] = stack.pop();//Fill the array with the path.
            }
            Arrays.sort(arr);//Sort the array in order to print in order.
            printInOrder(arr);
        }
        else
        {
            //If dfs returns false, then we have no cycle that contains our char value.
            System.out.println("No Cycle");
        }
    }
    private void printInOrder(char[] arr)
    {
        //Print out the array in the given format.
        for(int i = 0;i<count-1;i++)
        {
            System.out.print(arr[i]+"-");
        }
        System.out.print(arr[count-1]);
    }


}
