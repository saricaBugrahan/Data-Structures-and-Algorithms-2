package hw1_2;
import java.util.Collections;
import java.util.LinkedList;

public class Algorithm {
    boolean[] marked;//To control vertexes that are visited already.
    char startVertex, endVertex; //The path between these specific vertexes.
    Graph G;
    int numberOfVertexInPath;//The input value that it wants to exact same amount of vertex
    LinkedList<LinkedList<Character>> AllThePaths;//To store the all the paths
    LinkedList<Character> path;//Traversing in the graph to find a path

    public Algorithm(Graph G, char startVertex, char endVertex, int numberOfVertexInPath) {
        //Initializing the variables above.
        this.G = G;
        marked = new boolean[G.V];
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.numberOfVertexInPath = numberOfVertexInPath;
        AllThePaths = new LinkedList<LinkedList<Character>>();
        path = new LinkedList<>();
        path.add(startVertex);//Add the beginning vertex because we are skipping it when we are running dfs.
        dfs(G, startVertex, endVertex);//Start our dfs.

    }

    private void dfs(Graph G, char startVertex, char endVertex) {
        marked[startVertex % 97] = true;//Mark the start vertex
        if (startVertex == endVertex) { //if the start vertex is equal to the endVertex, we find a path and we need to add into AllThePaths variable.
            LinkedList<Character> temp = new LinkedList<>();//To keep the values of path we are creating a temp variable and store all the data's in the path.
            for (int i = 0; i < path.size(); i++) {//We cannot directly add the path because we will delete some of them in the path to find out the new roads.
                temp.add(path.get(i));
            }
            AllThePaths.add(temp);//Push the path value.
        } else {
            //If it's not equal we are searching it's adj.
            for (char w : G.adj(startVertex)) {
                int wID = w % 97;
                //If it's not marked we need to add it into the path and start dfs from that value.
                if (!marked[wID]) {
                    path.add(w);
                    dfs(G, w, endVertex);
                    path.removeLast();//When we detect a cycle or in a dead end, our dfs method will continue and remove the last item in the path and search for other vertexes.
                }
                //if it's already marked we can skip it.
                else
                {
                    continue;
                }
            }

        }
        //At the end of the dfs, for the sake of finding new roads we need to marked as false to find different roads.
        marked[startVertex % 97] = false;
    }
    public void printPath ()
    {
        int count = 1;
        //If it's empty we dont have any path.
        if (!AllThePaths.isEmpty()) {
            for (int i = 0; i < AllThePaths.size(); i++) {
                LinkedList<Character> temp = AllThePaths.get(i);//Take the one of the paths.
                Collections.sort(temp);//Sort them with the Collections library method
                if (temp.size() == numberOfVertexInPath) {//If the path contains exact same amount of vertex which is taken from input print it
                    System.out.print(count + ") ");
                    for (int j = 0; j < temp.size() - 1; j++) {
                        System.out.print(temp.get(j) + "-");
                    }
                    System.out.print(temp.get(temp.size() - 1));
                    System.out.println();
                    count++;
                }
            }
        }

        //If our value stuck with the 1, even though there are paths between these vertexes, there is no path with the given amount of vertexes.
        if(count == 1)
            System.out.println("No cycle");
    }
}






