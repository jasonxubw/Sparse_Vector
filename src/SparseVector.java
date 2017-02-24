import java.text.DecimalFormat;
import java.util.Iterator;

public class SparseVector {

    private DoublyLinkedList<Element> vectorList; //stores the doubly linked list

    public SparseVector()
    {
        vectorList = new DoublyLinkedList();
    }

    public void addElements(Element e) //add terms to the beginning of the Doubly Linked List
    {
        vectorList.add(e);
    }

    public Element sumElements(Element a, Element b) //sums the values if the index are the same
    {
        return new Element(a.getIndex(), a.getValue() + b.getValue());
    }

    public Element differenceElements(Element a, Element b) //finds the difference if the index are the same
    {
        return new Element(a.getIndex(), a.getValue() - b.getValue());
    }

    public Element productElements(Element a, Element b) //finds the product if the index are the same
    {
        return new Element(a.getIndex(), a.getValue() * b.getValue());
    }

    public boolean smallerIndex(Element a, Element b) //checks if index of a is greater than index b
    {
        return a.getIndex() < b.getIndex();
    }

    public boolean equalIndex(Element a, Element b) //checks if index a is equal to index b
    {
        return a.getIndex() == b.getIndex();
    }

    public DoublyLinkedList<Element> getVectorList() //returns linked list stored in SparseVector
    {
        return vectorList;
    }

    public void setVectorList(DoublyLinkedList<Element> s) //assigns a linked list to the SparseVector
    {
        vectorList = s;
    }

    public void sort() //sorts the SparseVector in ascending order
    {
        int lowestIndex;
        Element temp;
        Element compare;

        if (vectorList.size() > 1) //make sure the list has greater than 1 element for this to work
        {
            for (int i = 0; i < vectorList.size() - 1; i++ ) //searches through all the elements except for the last one
            {
                compare = vectorList.get(i); //set the first element in vectorList to compare, to be compared to the rest of the elements
                for (int j = i + 1; j < vectorList.size(); j++) //search through all elements except for the first
                {
                    try
                    {
                        if (equalIndex(vectorList.get(j), compare)); //if index are equal, there is an error in the input
                    }
                    catch (Exception e)
                    {
                        System.out.println("Illegal input. There are two elements with the same index.");
                    }

                }

                lowestIndex = i; //store the first index as the lowest index
                for (int k = i + 1; k < vectorList.size(); k++) //go through all elements except the first one
                {
                    if (smallerIndex(vectorList.get(k), vectorList.get(lowestIndex))) //finds greatest term
                        lowestIndex = k;
                }

                if (lowestIndex != i) //swaps elements if next lowest value is not already in the correct position
                {
                    temp = vectorList.get(lowestIndex); //stores lowest element in temporary variable
                    vectorList.set(lowestIndex, vectorList.get(i)); //moves element of current index position to the index position of the lowest element
                    vectorList.set(i, temp); //places lowest element in current index position
                }
            }
        }
    }

    public SparseVector add(SparseVector s)
    {
        DoublyLinkedList<Element> vectorList1 = s.getVectorList(); //store linked list from parameter SparseVector
        DoublyLinkedList<Element> result = new DoublyLinkedList(); //link list to store sum
        SparseVector newVector = new SparseVector(); //SparseVector to store link list result
        Iterator<Element> itr = vectorList.iterator(); //iterator for vectorList
        Iterator<Element> itr1 = vectorList1.iterator(); //iterator for parameter vectorList

        Element element = new Element();
        Element element1 = new Element();

        if(vectorList.isEmpty()) //if the list is empty, the result is the other list
            return s;
        else if (vectorList1.isEmpty()) //if the list is empty, the result is the other list
            return this;
        else //if neither is empty, we store the first elements
        {
            element = itr.next();
            element1 = itr1.next();
        }

        while (element != null && element1 != null) //loop until one of the lists run out of elements
        {
            if(smallerIndex(element, element1)) //if an element has greater index, we don't add their values, we just add them our resulting vectorList
            {
                result.add(element);
                if (itr.hasNext())
                    element = itr.next();
                else
                    element = null;
            }
            else if (equalIndex(element, element1)) //if two elements have equal index, we add their values.
            {
                result.add(sumElements(element, element1));
                if (itr.hasNext())
                    element = itr.next();
                else
                    element = null;

                if (itr1.hasNext())
                    element1 = itr1.next();
                else
                    element1 = null;
            }
            else // if one element has greater index than other, we don't do anything to them and add them to our resulting vectorList
            {
                result.add(element1);
                if (itr1.hasNext())
                    element1 = itr1.next();
                else
                    element1 = null;
            }
        }

        while (element != null) //if only one iterator is finished, add remaining terms of the other iterator
        {
            result.add(element);
            if (itr.hasNext())
                element = itr.next();
            else
                element = null;
        }

        while (element1 != null) //if only one iterator is finished, add remaining terms of the other iterator
        {
            result.add(element1);
            if (itr1.hasNext())
                element1 = itr1.next();
            else
                element1 = null;
        }

        newVector.setVectorList(result); //store result list to our vectorList
        return newVector;
    }

    public SparseVector subtract(SparseVector s)
    {
        DoublyLinkedList<Element> vectorList1 = s.getVectorList(); //store linked list from parameter SparseVector
        DoublyLinkedList<Element> result = new DoublyLinkedList(); //link list to store sum
        SparseVector newVector = new SparseVector(); //SparseVector to store link list result
        Iterator<Element> itr = vectorList.iterator(); //iterator for vectorList
        Iterator<Element> itr1 = vectorList1.iterator(); //iterator for parameter vectorList

        Element element = new Element();
        Element element1 = new Element();

        if(vectorList.isEmpty()) //if the list is empty, the result is the other list
            return s;
        else if (vectorList1.isEmpty()) //if the list is empty, the result is the other list
            return this;
        else //if neither is empty, we store the first elements
        {
            element = itr.next();
            element1 = itr1.next();
        }

        while (element != null && element1 != null) //loop until one of the lists run out of elements
        {
            if(smallerIndex(element, element1)) //if an element has greater index, we don't add their values, we just add them our resulting vectorList
            {
                result.add(element);
                if (itr.hasNext())
                    element = itr.next();
                else
                    element = null;
            }
            else if (equalIndex(element, element1)) //if two elements have equal index, we add their values.
            {
                result.add(differenceElements(element, element1));
                if (itr.hasNext())
                    element = itr.next();
                else
                    element = null;

                if (itr1.hasNext())
                    element1 = itr1.next();
                else
                    element1 = null;
            }
            else // if one element has greater index than other, we don't do anything to them and add them to our resulting vectorList
            {
                result.add(element1);
                if (itr1.hasNext())
                    element1 = itr1.next();
                else
                    element1 = null;
            }
        }

        while (element != null) //if only one iterator is finished, add remaining terms of the other iterator
        {
            result.add(element);
            if (itr.hasNext())
                element = itr.next();
            else
                element = null;
        }

        while (element1 != null) //if only one iterator is finished, add remaining terms of the other iterator
        {
            result.add(element1);
            if (itr1.hasNext())
                element1 = itr1.next();
            else
                element1 = null;
        }

        newVector.setVectorList(result); //store result list to our vectorList
        return newVector;
    }

    public double dot(SparseVector s) //this returns the dot product of the elements that have the same index
    {
        DoublyLinkedList<Element> vectorList1 = s.getVectorList();
        DoublyLinkedList<Element> result = new DoublyLinkedList();
        SparseVector newVector = new SparseVector();
        SparseVector tempVector = new SparseVector();
        Iterator<Element> itr = vectorList.iterator();
        Iterator<Element> itr1 = vectorList1.iterator();
        Element element = new Element();
        Element element1 = new Element();
        int index;
        double value;
        double product = 0.0;

        if (vectorList.isEmpty() || (vectorList1.isEmpty()))
            return 0.0;
        else
        {
            element = itr.next();
            element1 = itr1.next();
        }

        while (element != null)
        {
            index = element.getIndex();
            value = element.getValue();
            while (element1 != null)
            {
                if (smallerIndex(element,element1))
                {
                    result.add(element);
                    if (itr.hasNext())
                        element = itr.next();
                    else
                        element = null;
                }
                else if (equalIndex(element, element1))
                {
                    Element test = new Element();
                    test= productElements(element,element1);
                    result.add(productElements(element,element1));
                    product = product + test.getValue();
                    if (itr.hasNext())
                        element = itr.next();
                    else
                        element = null;

                    if (itr1.hasNext())
                        element1 = itr1.next();
                    else
                        element1 = null;
                }
                else
                {
                    result.add(element1);
                    if (itr1.hasNext())
                        element1 = itr1.next();
                    else
                        element1 = null;
                }
            }
        }
        return product;
    }

    public String toString()
    {
        Iterator<Element> itr = vectorList.iterator();
        String print = "";
        Element element;
        int count = 0;
        print = "( ";

        while (itr.hasNext())
        {
            element = itr.next();
            print =  print + "[" + element.getIndex() + ", " + element.getValue() + "] ";
        }
        print = print + ")";
        return print;
    }
}
