package hw2_2;

import java.util.LinkedList;

public class Algorithm {
    private boolean[] marked;
    private LinkedList<Integer> changedCharactersIndexes;//Holding the indexes that is need to be changed
    private Graph graph;
    private LinkedList<Integer> visitedOs;//To break the loop we need to store the visitedO's
    Algorithm(Graph graph, SOS startVertex)
    {
        this.graph = graph;
        marked = new boolean[graph.getV()];
        changedCharactersIndexes = new LinkedList<>();
        visitedOs = new LinkedList<>();
        changedCharactersIndexes = dfs(startVertex);
    }
    private boolean multipleOValues(SOS vertex)
    {
        for(SOS w: graph.adj(vertex))
        {
            if(w.name =='S')
                vertex.neighboursControl.add(true);
        }
        if(vertex.neighboursControl.size() == 3)
            return true;
        return false;
    }
    private LinkedList<Integer> dfs(SOS startVertex)
    {
        marked[startVertex.rep] = true;
        if(startVertex.name =='O')//if we are hitting the O we need to check it's adjecents.
        {
            for(SOS neighbours: graph.adj(startVertex))
            {

                if(neighbours.name =='S')
                {
                    startVertex.neighboursControl.add(true);//if it's S we will store them
                }
                if(neighbours.name =='O')
                {

                    if(!visitedOs.contains(neighbours.rep))//if it's O and not visited
                    {
                        visitedOs.add(startVertex.rep);//Add as visited and start the dfs again.
                        dfs(neighbours);
                    }
                    startVertex.neighboursControl.add(true);
                }

            }
            if(startVertex.neighboursControl.size()==4 && !startVertex.neighboursControl.contains(false))//if neighbours control's size 4 and it's not containing false add the index to the changeIndexes
            {

                changedCharactersIndexes.add(startVertex.rep);
            }


        }

        for(SOS w: graph.adj(startVertex))
        {
            if(w.rep == startVertex.rep)
                continue;
            if(!marked[w.rep])
            {
                dfs(w);//If it's not marked start dfs again
            }
        }
    return changedCharactersIndexes;
    }

    public LinkedList<Integer> getChangedCharactersIndexes()
    {
        return this.changedCharactersIndexes;//Returns the list.
    }

}
