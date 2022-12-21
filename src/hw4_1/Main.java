package hw4_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TrieST<Integer> trie = new TrieST<>();//Trie object
        Scanner scan = new Scanner(System.in);//scanner object
        int numberOfBinaryNumber = scan.nextInt();//number of entry
        for(int i = 0;i<numberOfBinaryNumber;i++)
        {
            trie.put(scan.next(),i);//insert into the trie
        }
        String argumentNumber = scan.next();//number that according to follow
        System.out.println(trie.solve(argumentNumber));
    }
}
