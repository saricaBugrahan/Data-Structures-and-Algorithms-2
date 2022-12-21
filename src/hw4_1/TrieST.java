package hw4_1;
public class TrieST<Value> {
    private static final int R = 2;//Children number {0,1}
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value val) {
       root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();//if null create new node
        if (d == key.length()) {
            if (x.val == null)
            x.val = val;//update its value
            return x;
        }
        int c =0;
        //All the nodes two children because of that we are kind of representing binary tree
        if(key.charAt(d) == '0')//if it's 0 set the value of c to zero
            c = 0;
        else
            c = 1;//else set the value of c 1
        x.next[c] = put(x.next[c], key, val, d+1);//call recursively
        return x;
    }


    public String solve(String argumentNumber)
    {
        String result = "";
        Node x = root;
        for(int i = 0;i<argumentNumber.length();i++)//for each character in the argument number
        {
            char c = argumentNumber.charAt(i);//take the most-left bit
            if(c == '0')//if it's 0 then for XOR it needs to 1
            {
                if(x.next[1] != null)//if 1 it's not null insert
                {
                    result+=1;
                    x = x.next[1];//follow 1's path
                }
                else
                {
                    result+=0;//if null add 0 and follow the next path
                    x = x.next[0];
                }
            }
            else
            {

                if(x.next[0] != null)//if it's 1 and 0 is not null
                {
                    result+=0;//add zero and follow the next path
                    x = x.next[0];
                }
                else
                {
                    result+=1;//if 0 is null insert 1 and follow the next path
                    x = x.next[1];
                }
            }
        }
        return result;//return the result
    }
}
