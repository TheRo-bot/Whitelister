/*
AUTHOR: Ro Boden <19821601>
DATE: 16th August 2019
PURPOSE:
    A general use LinkedList class. Predominately created for further understanding of the storage type
    **This java file has been obtained and edited on from my Practical 3 LinkedList submission**
*/

import java.util.*;


public class LinkedList implements Iterable
{


    // setup Iterator class
    public Iterator iterator()
    {
        return new LinkedListIterator(this);

    }

    // setup private inner LinkedListIterator
    private class LinkedListIterator implements Iterator
    {
        private ListNode iterNext;



        /*
        CONSTRUCTOR: Alternate
        IMPORT: theLIst (LinkedList)
        */
        public LinkedListIterator(LinkedList theList)
        {
            iterNext = theList.head;
        }

        
        /*
        ACCESSOR: hasNext
        EXPORT: (boolean)
        */
        public boolean hasNext()
        {   
            return (iterNext != null);   }



        /*
        SUBMODULE: next
        EXPORT: value (Object)
        */
        public Object next()
        {
            Object value;
            // if there's no item next, set it to null
            if( iterNext == null )
            {
                value = null;
            }
            // otherwise, get the value and then move the cursor once across
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getFWNode();
            }

            return value;
        }


        public void remove()
        {
            throw new UnsupportedOperationException();
        }

    }


    // set up private inner class ListNode
    private class ListNode
    {
        // Class fields --
        private Object value;
        private ListNode fwNode;
        private ListNode bwNode;

        /*
        CONSTRUCTOR: Default
        */
        public ListNode()
        {

            setValue(null);
            setFWNode(null);   
            setBWNode(null);
        }


        /*
        CONSTRUCTOR: Alternate
        IMPORT: value (Object)
        PURPOSE:
            To create a valid object based off of the given imports
        */
        public ListNode(Object valueIn)
        {

            setValue(valueIn);
            setFWNode(null);
            setBWNode(null);
        }



        /*
        CONSTRUCTOR: Copy
        IMPORT: copyListNode (ListNode)
        PURPOSE:
            To create a valid object based off of a given ListNode
        */
        public ListNode(ListNode copyListNode)
        {

            setValue(copyListNode.getValue());
            setFWNode(copyListNode.getFWNode());
            setBWNode(copyListNode.getBWNode());
        }


        /*
        ACCESSOR: clone
        IMPORTS: inNode (ListNode)
        EXPORTS: exportNode (ListNode)
        PURPOSE:
            will copy this node's information
            ignores links
        */
        public ListNode clone(ListNode inNode)
        {
            return new ListNode(inNode.getValue());
        }


        public boolean hasNext()
        {
            return getFWNode() != null;
        }
        public boolean hasLast()
        {   return getBWNode() != null;   }

        /*
        ACCESSOR: getValue
        EXPORT: value
        */
        public Object getValue()
        {   return value;   }

        /*
        ACCESSOR: getFWNode
        EXPORT: fwNode
        */
        public ListNode getFWNode()
        {   return fwNode;   }

        /*
        ACCESSOR: getBWNode
        EXPORT: bwNode
        */
        public ListNode getBWNode()
        {   return bwNode;   }



        /*
        SUBMODULE: isEmpty
        IMPORT: None
        EXPORT: empty (Boolean)
        PURPOSE:
            Will return true if there is no value or fwNode connected to this node.
        */
        public boolean isEmpty()
        {
            boolean empty = false;

            if( getValue() == null )
            {
                if( getFWNode() == null )
                {
                    if( getBWNode() == null )
                    {
                        empty = true;                    
                    }
                }
            }

            return empty;
        }
        
        /*
        ACCESSOR: equals
        IMPORTS: inObject (Object)
        PURPOSE:
            Compares inObject's node value to this node's value
            *ONLY COMPARES VALUES, DOES NOTHING MORE*
            LinkedList equals method compares all
        */
        public boolean equals(Object inObject)
        {
            boolean isEqual = false;
            ListNode inNode = null;
            if( inObject instanceof ListNode )
            {
                inNode = (ListNode)inObject;
                if( getValue().equals(inNode.getValue()) )
                {
                    isEqual = true;
                }
            }
            return isEqual;
        }

        /*
        ACCESSOR: toString
        EXPORTS: exportString
        PURPOSE:
            Converts information to a string
        */
        public String toString()
        {
            String exportString = "This node holds '" + getValue() + "'";
            return exportString;
        }


        /*
        MUTATOR: setValue
        IMPORT: requestedValue (Object)
        */
        public void setValue(Object requestedValue)
        {
            value = requestedValue;
        }

        

        /*
        MUTATOR: setFWNode
        IMPORT: requestedFWNode (ListNode)
        */
        public void setFWNode(ListNode requestedFWNode)
        {
            fwNode = requestedFWNode; 
        }


        /*
        MUTATOR: setBWNode
        IMPORT: requestedBWNode (ListNode)
        */
        public void setBWNode(ListNode requestedBWNode)
        {
            bwNode = requestedBWNode;
        }
        


    }//END ListNode

    // Continue with LinkedList's setup

    // class fields 
    private ListNode head;
    private ListNode tail;
    int count;
    /*
    CONSTRUCTOR: Default
    */    
    public LinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    /*
    CONSTRUCTOR: Copy
    PURPOSE:
        Copies information about <copyList> to this new LinkedList
    */
    public LinkedList(LinkedList copyList)
    {
        head = copyList.getHead();
        tail = copyList.getTail();
        count = copyList.getCount();
    }



    /*
    METHOD: clone
    IMPORTS: inList (LinkedList)
    EXPORTS: exportList
    PURPOSE:
        To mimic inList's list
        Mainly used for adding items to a temporary list 
        without editing an existing one
    */
    public LinkedList clone()
    {
        LinkedList exportList = new LinkedList();
        ListNode thisNode = head;
        while(thisNode != null)
        {
            Object thisValue = thisNode.getValue();
            exportList.insertLast(thisValue);
            if( thisNode.hasNext())
                thisNode = thisNode.getFWNode();
            else
                thisNode = null;
        }
        return exportList;
    }

    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        LinkedList inList = null;
        if( inObject instanceof LinkedList)
        {
            inList = (LinkedList)inObject;
            if( inList.getCount() == getCount() )
            {
                ListNode homeNode = head;
                ListNode foreignNode = inList.getHead();
                boolean matchingEntries = true;
                while(homeNode != null && foreignNode != null)
                {
                    if( !homeNode.getValue().equals(foreignNode.getValue()) )
                    {
                        matchingEntries = false;
                    }
     
                    if( homeNode.hasNext() && foreignNode.hasNext() )
                    {
                        homeNode = homeNode.getFWNode();
                        foreignNode = foreignNode.getFWNode();
                    }
                    else
                    {
                        homeNode = null;
                        foreignNode = null;
                    }
                }
                if( matchingEntries )
                {
                    isEqual = true;
                }
            }
        }


        return isEqual;
    }

    /*
    MUTATOR: insertFirst
    IMPORT: newValue (Object)
    */
    public void insertFirst(Object newValue)
    {

        ListNode newNode = new ListNode(newValue);
        
        if( isEmpty() ) 
        {// if the list is empty
            head = newNode;
            tail = newNode;
            count++;
        }
        else if( head.getFWNode() == null )
        {// if there's only one
            tail = head;
            head = newNode;
            head.setFWNode(tail);
            tail.setBWNode(head);
            count++;
        }
        else
        {// if there's more than 1 amount of things
            head.setBWNode(newNode);
            newNode.setFWNode(head);
            head = newNode;
            count++;
        }
        
    }


    /*
    MUTATOR: insertLast
    IMPORT: newValue (Object)
    */
    public void insertLast(Object newValue)
    {
        // create the new node from the value
        ListNode newNode = new ListNode(newValue);
    
        // if the list is empty
        if( isEmpty() )
        {
            head = newNode;
            tail = newNode;
            count++;
        }
        // if the list has one entry
        else if( head.getFWNode() == null )
        {
            tail = newNode;
            head.setFWNode(tail);
            tail.setBWNode(head);
            count++;
        }
        else
        {// multiple
            // mutate the current tail's fw pointer to newNode
            tail.setFWNode(newNode);
            newNode.setBWNode(tail);
            // change tail to be newNode now, as it's at the very end.
            tail = newNode;
            count++;
        }

    }

    public int getCount()   
    {
        return count;
    }



    /*
    ACCESSOR: getHead
    EXPORT: head (ListNode)
    */
    public ListNode getHead()
    {   return head;    }


    /*
    ACCESSOR: getTail
    EXPORT: head (ListNode)
    */
    public ListNode getTail()
    {   return tail;    }


    /*
    ACCESSOR: isEmpty
    EXPORT: empty (Boolean)
    */
    public boolean isEmpty()
    {   return (count == 0);  }



    /*
    ACCESSOR: peekFirst
    EXPORT: nodeValue (Object)
    */
    public Object peekFirst()
    {

        if( isEmpty() )
        {
            throw new NullPointerException("List is empty!");
        }
        return head.getValue();
    }


    /*
    ACCESSOR: peekLast
    EXPORT: nodeValue (Object)
    */
    public Object peekLast()
    {
        if( isEmpty() )
        {
            throw new NullPointerException("List is empty!");
        }

        return tail.getValue();
    }


    /*
    MUTATOR: removeFirst
    EXPORT: nodeValue (Object)
    */
    public Object removeFirst()
    {

        Object nodeValue = null;

        if( head == null )
        {
            throw new NullPointerException("List is empty!");
        }
        // if there's only one item in the linked list
        else if( head.getFWNode() == null )
        {   
            nodeValue = head.getValue();
            head = null;
            tail = null;
            count--;
        }
        // if there's n amount of items in the linked list
        else
        {
            // get head's value
            nodeValue = head.getValue();
            // point head to second entry in the list
            head = head.getFWNode();
            // point new head's backward node to the void
            head.setBWNode(null);
            count--;
        }

        return nodeValue;   
    }

    


    /*
    MUTATOR: removeLast
    EXPORT: nodeValue (Object)
    */
    public Object removeLast()
    {
        Object nodeValue;
        if( isEmpty() )
        {
            throw new NullPointerException("List is empty!");
        }
        else if( tail.getBWNode() == null )
        {// if there's only one entry
            nodeValue = tail.getValue();
            tail = null;
            head = null;
            count--;
        }
        else
        {// if there's a bunch of entries
            nodeValue = tail.getValue();
            tail = tail.getBWNode();
            tail.setFWNode(null);
            count--;
        }



        return nodeValue;
    }


    /*
    METHOD: removeEntry
    IMPORTS: selectedNode (Object)
    EXPORTS: exportValue (Object)
    PURPOSE:
        Will search iteratively through the LinkedList, will remove selectedNode when found.
        O(N) time.
    */
    public void removeEntryObject(Object selectedNode)
    {
        if( isEmpty() )
            throw new NullPointerException("List is empty!");

        ListNode thisNode = head;

        boolean found = false;
        while( thisNode != null && !found)
        {
            if( thisNode.getValue().equals(selectedNode) )
            {
                // remove the link between thisNode from the LL
                found = true;
                // one dude
                if( head.getFWNode() == null )
                {                   
                    removeFirst();
                }
                // many dudes
                else
                {
                    // if it's not at the end
                    if( thisNode.getFWNode() != null )
                    {
                        thisNode.getFWNode().setBWNode(thisNode.getBWNode());
                    }
                    // it's at the end
                    else
                    {
                        tail = thisNode.getBWNode();
                        tail.setFWNode(null);
                    }
                    // if it's not at the beginning
                    if( thisNode.getBWNode() != null )
                    {
                        thisNode.getBWNode().setFWNode(thisNode.getFWNode());
                    }
                    // it's at the beginning
                    else
                    {
                        head = thisNode.getFWNode();
                        head.setBWNode(null);
                    }
                    count--;
                }
            }
            // iterate through 
            if( thisNode.hasNext() )
                thisNode = thisNode.getFWNode();
            else
                thisNode = null;
        }

    }




    /*
    MUTATOR: removeEntry
    IMPORTS: chosenTeam (int)
    EXPORT: nodeValue (Object)
        Wrapper function for recurseRemoveEntry
    */
    public Object removeEntryIndex(int selectedNode)
    {
        Object exportValue = null;

        if( isEmpty() )
        {
            throw new NullPointerException("List is empty!");
        }

        int index = 0;
        ListNode thisNode = head;
        while(index <= selectedNode && thisNode != null)
        {
            if( index == selectedNode)
            {
                exportValue = thisNode.getValue();
                if( thisNode.getBWNode() == null)
                {   // it's head
                    removeFirst();


                }
                else if( thisNode.getFWNode() == null)
                {
                    // it's tail
                    removeLast();

                }
                else
                {
                    // set the node that this place is in to the next node this node stores
                    thisNode.getBWNode().setFWNode(thisNode.getFWNode());
                    thisNode.getFWNode().setBWNode(thisNode.getBWNode());
                    count--;
                }
            }

            thisNode = thisNode.getFWNode();
            index++;
        }


        return exportValue;
        
    }




    /*
    METHOD: editEntry
    IMPORTS: oldValue, newValue (Object)
    PURPOSE:
        Iterates through the Linkedlist, searching for oldValue, will replace newValue if found.
        O(N) time
    */
    public void editEntry(Object oldValue, Object newValue)
    {
        if( isEmpty() )
        {
            throw new NullPointerException("List is empty!");
        }
        boolean done = false;
        ListNode thisNode = head;
        int index = 0;
        while( thisNode != null && !done)
        {


            if( thisNode.getValue().equals(oldValue) )
            {
                
                thisNode.setValue(newValue);

                done = true;
            }

            if( thisNode.hasNext() )
                thisNode = thisNode.getFWNode();

            else
                thisNode = null;
            index++;
        }

    }





    public boolean hasEntry(Object testObject)
    {

        ListNode thisNode = head;
        boolean found = false;
        while( !found && thisNode != null)
        {
            if( thisNode.getValue().equals(testObject))
            {
                found = true;
            }
            thisNode = thisNode.getFWNode();
        }

        return found;
    }




    /*
    METHOD: getEntryIndex
    IMPORTS: index (int)
    EXPORTS: exportObject (Object)
    PURPOSE:
        Wrapper method for recurseGetEntry
    */ 
    public Object getEntryIndex(int index)
    {
        if( isEmpty() )
            throw new NullPointerException("List is empty!");

        else if( index >= getCount() )
        {
            throw new IndexOutOfBoundsException(index + " for length " + getCount());
        }
        return recurseGetEntry(0,index,head);
    }


    /*
    METHOD recurseGetEntry
    IMPORTS: index, selectedNode (int), node (ListNode)
    EXPORTS: exportObject (Object)
    PURPOSE:
        To recursively go through the LinkedList, will obtain value at <index>
    */
    private Object recurseGetEntry(int index, int selectedNode, ListNode node)
    {
        Object exportObject = null;
        if( index == selectedNode)
        {
            exportObject = node.getValue();
        }
        else if( index < selectedNode )
        {
            exportObject = recurseGetEntry(index + 1, selectedNode, node.getFWNode());
        }

        return exportObject;
    }


}//END LinkedList
