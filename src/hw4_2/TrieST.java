package hw4_2;

import java.util.LinkedList;
import java.util.Stack;

public class TrieST <Value>{

    private static final int R = 256;//Number children.
    private Node root;

    private static class Node {
        private Object val;
        private final Node[] next = new Node[R];
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }
    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null)
                x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
    public Stack<String> keysWithPrefix(String prefix) {
        Stack<String> results = new Stack<>();//Create a stack to hold the elements
        Node x = get(root, prefix, 0);//Get the element with the prefix
        collect(x, new StringBuilder(prefix), results);//collect all the elements
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Stack<String> results) {
        if (x == null) return;//if null return;
        if (x.val != null) results.push(prefix.toString());//if val is not null push.
        for (char c = 0; c < R; c++) {
            prefix.append(c);//append the character and recursively call
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);//When recursion finish delete the element of the last
        }
    }
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);//take the first char
        return get(x.next[c], key, d+1);//recursively call itself
    }
    public LinkedList<Integer> ChildrenCount(Node x)
    {
        LinkedList<Integer> indexes = new LinkedList<>();
        for(int i = 0;i<R;i++)
        {
            if(x.next[i] != null)
            {
                indexes.add(i);//Find how many children are not null
            }
        }
        return indexes;
    }
    public LinkedList<String> solve()
    {
        LinkedList<Integer> childrenOfRoot = ChildrenCount(root);//Find the children of the root
        Node x;
        LinkedList<String> allTheLongestPrefixes = new LinkedList<>();
        for(int i = 0;i<childrenOfRoot.size();i++)
        {
            int index = childrenOfRoot.get(i);//Get the first children
           x = root.next[index];
           String result = "";
           result+=(char)index;//add the value of it
           LinkedList<Integer> currentChildren = ChildrenCount(x);//Find the children's children
           while(currentChildren.size()<2)//if it has 1 element add the current result value
           {
               int number = currentChildren.get(0);
               result+=(char)number;
               x = x.next[currentChildren.get(0)];//iterate the x value
               currentChildren = ChildrenCount(x);//and find the children again

           }
           allTheLongestPrefixes.add(result);//When the while loop finish it is found a prefix for some strings add the value.

        }
        return allTheLongestPrefixes;//return the value
    }

}
