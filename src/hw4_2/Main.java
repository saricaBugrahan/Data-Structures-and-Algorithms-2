package hw4_2;

import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//scanner
        TrieST<Integer> trie = new TrieST<>();//trie object
        int numberOfWords = scan.nextInt();//number of strings
        for(int i = 0;i<numberOfWords;i++)
        {
            trie.put(scan.next(),i);//insert them into trie
        }
        LinkedList<String> longestPrefixes = trie.solve();//take all the prefixes among the trie
        String max = longestPrefixes.get(0);
        for(String w: longestPrefixes)//find the maximum string that contains specific prefix
        {
            if (trie.keysWithPrefix(max).size()<trie.keysWithPrefix(w).size())
            {
                max =w;
            }
        }
        System.out.println("Longest common prefix: "+max);//print out the value
        System.out.println("Number of strings: "+trie.keysWithPrefix(max).size());
        scan.close();

    }
}
