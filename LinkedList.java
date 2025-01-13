public class LinkedList 
{
        public CoordinateNode head;
        // APPENDLIST AND REVERSED SHOULD MAKE A COPY AND NOT HCNAGE THE OG LIST!!!!!!!!!!
        public LinkedList() 
        {
            this.head = null;
        }
    
        public LinkedList(int x, int y) 
        {
            this.head = new CoordinateNode(x, y);
        }
    
        public void append(int x, int y) //add node at end of list
        {
            if (head == null)
            {
                head = new CoordinateNode(x , y);
            }
            else
            {
                appendHelper(head,x,y);
            }
        }

        private void appendHelper (CoordinateNode node, int x, int y)
        {
            if(node.next == null)
            {
                node.next = new CoordinateNode(x, y);
            }

            else
            {
                appendHelper (node.next ,x ,y );
            }
        }
    
        public void appendList(LinkedList other) 
        {
            if (other != null && other.head != null) 
            {
                LinkedList copy = other.copy(); // Create a copy of the other list
                if (head == null) 
                {
                    head = copy.head;
                } 
                else 
                {
                    appendListHelper(head, copy.head);
                }
            }
        }

        private void appendListHelper (CoordinateNode current, CoordinateNode otherHead)
        {
            if (current.next == null)
            {
                current.next = otherHead;
            }
            
            else
            {
                appendListHelper (current.next, otherHead);
            }
        }

        public LinkedList copy() 
        {
            LinkedList newList = new LinkedList();
            if (head != null) 
            {
                newList.head = copyHelper(head);
            }
            return newList;
        }
        
        private CoordinateNode copyHelper(CoordinateNode node) 
        {
            if (node == null) 
            {
                return null;
            }
            CoordinateNode newNode = new CoordinateNode(node.x, node.y);
            newNode.next = copyHelper(node.next);
            return newNode;
        }
    
        public boolean contains(int x, int y) 
        {
            return containsHelper(head, x, y);
        }

        private boolean containsHelper (CoordinateNode node, int x, int y)
        {
            if (node == null)
            {
                return false;
            }
            if (node.x == x && node.y == y)
            {
                return true;
            }

            return containsHelper(node.next, x, y);
        }
    
        @Override
        public String toString() 
        {
            if (head == null)
            {
                return "Empty List";
            }
            return toStringHelper(head);
        }

        private String toStringHelper (CoordinateNode node)
        {
            if (node.next == null)
            {
                return node.toString();
            }

            return node.toString() + " -> " + toStringHelper(node.next);
        }
    
        public int length() 
        {
            return lengthHelper(head);
        }

        private int lengthHelper(CoordinateNode node)
        {
            if (node == null)
            {
                return 0;
            }

            return 1+lengthHelper(node.next);
        }
    
        public LinkedList reversed() 
        {
            LinkedList reversed = new LinkedList();
            reversedHelper(head, reversed);
            return reversed;
        }
        
        private void reversedHelper (CoordinateNode node, LinkedList reversed)
        {
            if (node == null)
            {
                return;
            }

            reversedHelper (node.next, reversed);
            reversed.append (node.x, node.y);
        }
    
    
}
