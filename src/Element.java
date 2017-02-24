
public class Element
{
    private int index; //instance variable for index
    private double value; //instance variable for value

    public Element() //default constructor
    {
        this(0,0);
    }

    public Element(int i, double v) //constructors that input values for instance variable
    {
        index = i;
        value = v;
    }

    public int getIndex() //returns index
    {
        return index;
    }

    public void setIndex(int i) //sets new value for index
    {
        index = i;
    }

    public double getValue() //returns value
    {
        return value;
    }

    public void setValue(double v) //sets new value for value
    {
        value = v;
    }
}