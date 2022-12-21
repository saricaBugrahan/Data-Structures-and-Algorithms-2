package hw2_2;

import java.util.LinkedList;

public class SOS {
    static int counter = 0;
    char name;//Name of the Char (O or S)
    int rep;//ID of the Char
    LinkedList<Boolean> neighboursControl = new LinkedList<>();
    SOS(char name)
    {
        this.name = name;
        rep = counter;
        counter++;
    }
    public void setName(char name)
    {
        this.name = name;
    }
}
