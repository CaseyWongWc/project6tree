package project6tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import project2stack.LinkedStack;
import project2stack.StackInterface;
import project3queue.LinkedQueue;
import project3queue.QueueInterface;

public class TreeIterator<T> implements TreeIteratorInterface <T>
{
    private BinaryNode<T> root;

    @Override
    public Iterator<T> getPreorderIterator()
    {
       return new PreorderIterator();
    }
     private class PreorderIterator implements Iterator<T>
    {
       private StackInterface<BinaryNode<T>> nodeStack;
       private BinaryNode<T> currentNode;
 
       public PreorderIterator()
       {
          nodeStack = new LinkedStack<>();
          currentNode = root;
       } // end default constructor
 
       public boolean hasNext() 
       {
          return (currentNode.getRightChild() != null || !nodeStack.isEmpty());
       }
 
       public T next() 
       {
          BinaryNode<T> nextNode = null;
 
          if (currentNode != null && currentNode.getLeftChild() != null)
          {
             nextNode = currentNode;
             nodeStack.push(nextNode);
             currentNode= currentNode.getLeftChild();
 
          }
          else if ( currentNode != null && currentNode.getRightChild() != null)
          {
             nextNode = (currentNode);
             nodeStack.push(nextNode);
             currentNode= currentNode.getRightChild();
 
          }
          else if (currentNode != null )
          {
             
             if (!nodeStack.isEmpty())
             {
                nextNode = currentNode;
                currentNode = nodeStack.pop();
                currentNode = currentNode.getRightChild();
             }
             else
             {
                currentNode = null;
                nodeStack = null;
                return null;
             }
          }
          else
          {
             throw new NoSuchElementException();
          }
          return nextNode.getData();
       }
       
    }
    
    @Override
    public Iterator<T> getPostorderIterator()
    {
       return new PostorderIterator(root);
    }
    private class PostorderIterator implements Iterator<T>
    {
       private StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
       private BinaryNode<T> currentNode;
       private BinaryNode<T> lastVisited;
 
       public PostorderIterator(BinaryNode<T> root)
       {
          if (root != null)
          {
             nodeStack.push(root);
             currentNode = root;
          }
       } // end default constructor
 
       // !!! CONSIDER THIS !!!
       public boolean hasNext() 
       {
          //return ((currentNode.getLeftChild() != null && currentNode.getRightChild() != null) || !nodeStack.isEmpty());
          return !nodeStack.isEmpty();
       }
 
       public T next() 
       {
         while(hasNext())
         {
          currentNode = nodeStack.peek();
 
          //if you havent went down the left child already (if it exists):
          if (lastVisited == null) 
          {
             //move to the left child if exists
             if (currentNode.getLeftChild() != null) 
             {
                nodeStack.push(currentNode.getLeftChild());
             }
             //move to right child if it exists
             else if (currentNode.getRightChild() != null) 
             {
                nodeStack.push(currentNode.getRightChild());
             }
             //if (no children), return, move up a level, mark current as lastvisited
             else  
             {
                nodeStack.pop();
                lastVisited = currentNode;
                return currentNode.getData(); //return information
             }
          }
 
          // if you have already visited left child,move down the right side
          else if (currentNode.getLeftChild() == lastVisited)
          {
             //clear last visited, and keep on going right until you hit leaf
             if (currentNode.getRightChild() != null)
             {
                nodeStack.push(currentNode.getRightChild());
                lastVisited = null;
             }
             //mark current node as last visited, move back up a step
             else
             {
                nodeStack.pop();
                lastVisited = currentNode;
                return currentNode.getData();
             }
          }
 
          //after you have already visted left and right childs,
          //mark current as last visited and go up a step
          else
          {
             nodeStack.pop();
             lastVisited = currentNode;
             return currentNode.getData();            
          }
         }
          throw new NoSuchElementException();
       }
    }
    
    @Override
    public Iterator<T> getInorderIterator()
    {
       return new InorderIterator();
    } // end getInorderIterator
    private class InorderIterator implements Iterator<T>
    {
       private StackInterface<BinaryNode<T>> nodeStack;
       private BinaryNode<T> currentNode;
    
       public InorderIterator()
       {
          nodeStack = new LinkedStack<>();
          currentNode = root;
       } // end default constructor
    
       public boolean hasNext() 
       {
          return !nodeStack.isEmpty() || (currentNode != null);
       } // end hasNext
    
       public T next()
       {
          BinaryNode<T> nextNode = null;
    
          // Find leftmost node with no left child
          while (currentNode != null)
          {
             nodeStack.push(currentNode);
             currentNode = currentNode.getLeftChild();
          } // end while
    
          // Get leftmost node, then move to its right subtree
          if (!nodeStack.isEmpty())
          {
             nextNode = nodeStack.pop();
             // Assertion: nextNode != null, since nodeStack was not empty
             // before the pop
             currentNode = nextNode.getRightChild();
          }
          else
             throw new NoSuchElementException();
    
          return nextNode.getData(); 
       } // end next
    
       public void remove()
       {
          throw new UnsupportedOperationException();
       } // end remove
    } // end InorderIterator
    
    @Override
    public Iterator<T> getLevelOrderIterator() 
    {
       return new LevelOrderIterator(root);
    }
    private class LevelOrderIterator implements Iterator<T>
    {
 
       private QueueInterface<BinaryNode<T>> nodeQueue; 
       private BinaryNode<T> currentNode;
 
       public LevelOrderIterator(BinaryNode<T> root)
       {
          if (root != null)
          {
             nodeQueue = new LinkedQueue<>();
             nodeQueue.enqueue(root);
          }
       }
       @Override
       public boolean hasNext() 
       {
        return !nodeQueue.isEmpty(); 
       }
 
       @Override
       public T next() 
       {
          while (hasNext())
          {
             currentNode = nodeQueue.dequeue();
 
             if (currentNode.getLeftChild() != null)
             {
                nodeQueue.enqueue(currentNode.getLeftChild());
             }
             if (currentNode.getRightChild() != null)
             {
                nodeQueue.enqueue(currentNode.getRightChild());
             }
 
             return currentNode.getData();
          }
          //else
          {
             throw new NoSuchElementException();
          }
 
       }
    }
 
    
}
