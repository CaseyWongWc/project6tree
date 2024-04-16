package project6tree;
import java.util.Iterator;
import java.util.NoSuchElementException;
import project3queue.*; // Needed by tree iterators
import project2stack.*; // Needed by tree iterators

/**
   A class that implements the ADT binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
   private BinaryNode<T> root;

   public BinaryTree()
   {
      root = null;
   } // end default constructor

   public BinaryTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end constructor

   public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
   {
      initializeTree(rootData, leftTree, rightTree);
   } // end constructor

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      initializeTree(rootData, (BinaryTree<T>)leftTree,
                               (BinaryTree<T>)rightTree);
   } // end setTree

	/*private void initializeTree(T rootData, BinaryTree<T> leftTree,
	                                        BinaryTree<T> rightTree)
	{
      // < FIRST DRAFT - See Segments 25.4 - 25.7 for improvements. >
      root = new BinaryNode<T>(rootData);
      
      if (leftTree != null)
         root.setLeftChild(leftTree.root);
      
      if (rightTree != null)
         root.setRightChild(rightTree.root);
	} // end initializeTree
   */
  private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
     root = new BinaryNode<>(rootData);
  
     if ((leftTree != null) && !leftTree.isEmpty())
        root.setLeftChild(leftTree.root);
  
     if ((rightTree != null) && !rightTree.isEmpty())
     {
        if (rightTree != leftTree)
           root.setRightChild(rightTree.root);
        else
           root.setRightChild(rightTree.root.copy());
     } // end if
  
     if ((leftTree != null) && (leftTree != this))
        leftTree.clear();
  
     if ((rightTree != null) && (rightTree != this))
        rightTree.clear();
  } // end initializeTree

  
/* Implementations of setRootData, getRootData, getHeight, getNumberOfNodes,
   isEmpty, clear, and the methods specified in TreeIteratorInterface are here.
   . . . */

   public void setRootData(T rootData)
   {
      root.setData(rootData);
   } // end setRootData

   public T getRootData()
   {
      if (isEmpty())
         //throw new EmptyTreeException();
         return null;
      else
         return root.getData();
   } // end getRootData
   
   public int getHeight()
   {
      int height = 0;
      if (root != null)
         height = root.getHeight();
      return height;
   } // end getHeight
   
   public int getNumberOfNodes()
   {
      int numberOfNodes = 0;
      if (root != null)
         numberOfNodes = root.getNumberOfNodes();
      return numberOfNodes;
   } // end getNumberOfNodes

   public boolean isEmpty()
   {
      return root == null;
   } // end isEmpty

   public void clear()
   {
      root = null;
   } // end clear

   protected void setRootNode(BinaryNode<T> rootNode)
   {
      root = rootNode;
   } // end setRootNode

   protected BinaryNode<T> getRootNode()
   {
      return root;
   } // end getRootNode
   
   public void inorderTraverse()
   {
      inorderTraverse(root);
   } // end inorderTraverse

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



   private void inorderTraverse(BinaryNode<T> node)
   {
      if (node != null)
      {
         inorderTraverse(node.getLeftChild());
         System.out.println(node.getData());
         inorderTraverse(node.getRightChild());
      } // end if
   } // end inorderTraverse

   public void iterativeInorderTraverse()
   {
      StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>(); //stack of generic binary nodes
      BinaryNode<T> currentNode = root;   //current node is a generic type binary node,set first to the root node

      while (!nodeStack.isEmpty() || (currentNode != null))
      {
         // Find leftmost node with no left child.
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while

         // Visit leftmost node, then traverse its right subtree
         if (!nodeStack.isEmpty())
         {
            BinaryNode<T> nextNode = nodeStack.pop();
            // Assertion: nextNode != null, since nodeStack was not empty
            // before the pop
            System.out.println(nextNode.getData());
            currentNode = nextNode.getRightChild();
         } // end if
      } // end while
   } // end iterativeInorderTraverse







   public void preorderTraverse()
   {
      preorderTraverse(root);
   }

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


   /*@Override
   private Iterator<T> getPreorderIterator() {
       return new Iterator<T>() {
           private StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
   
           {
               if (root != null) nodeStack.push(root);
           }
   
           public boolean hasNext() {
               return !nodeStack.isEmpty();
           }
   
           public T next() {
               if (!hasNext()) throw new NoSuchElementException();
               BinaryNode<T> currentNode = nodeStack.pop();
               if (currentNode.getRightChild() != null) nodeStack.push(currentNode.getRightChild());
               if (currentNode.getLeftChild() != null) nodeStack.push(currentNode.getLeftChild());
               return currentNode.getData();
           }
       };
   }*/
   

   private void preorderTraverse(BinaryNode<T> node)
   {
      if(node !=null)
      {
         System.out.println(node.getData());
         preorderTraverse(node.getLeftChild());
         preorderTraverse(node.getRightChild());
      }
   }


   public void postorderTraverse()
   {
      postorderTraverse(root);
   }

   public Iterator<T> getPostorderIterator()
   {
      return new PostorderIterator();
   }

   private class PostorderIterator implements Iterator<T>
   {
      private StackInterface<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;

      public PostorderIterator()
      {
         nodeStack = new LinkedStack<>();
         currentNode = root;
      } // end default constructor

      public boolean hasNext() 
      {
         return ((currentNode.getLeftChild() != null && currentNode.getRightChild() != null) || !nodeStack.isEmpty());
      }

      
      public T next() 
      {
         BinaryNode<T> nextNode = null;

         while (currentNode.getLeftChild() != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild(); 
         }
         while (currentNode.getRightChild() != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         }

         if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null)
         {
         nextNode = currentNode;
         currentNode = nodeStack.pop().getRightChild();
         return nextNode.getData();
         }

         return null;
        /*
         if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null)
         {
            nextNode = currentNode;
            currentNode = nodeStack.pop().getRightChild();
            return nextNode.getData();
         }
         if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null)
         {
            nextNode = currentNode;
            currentNode = nodeStack.pop().getRightChild();
            return nextNode.getData();
         }*/
         /* 
         if (currentNode != null && currentNode.getLeftChild() != null & currentNode.getRightChild() != null)
         {
            nextNode = nodeStack.pop();
            currentNode = nodeStack.peek().getRightChild();
            return nextNode.getData();
         }
         return null;*/
      }
      
   }
      private void postorderTraverse(BinaryNode<T> node)
   {
      if(node !=null)
      {
         
         postorderTraverse(node.getLeftChild());
         postorderTraverse(node.getRightChild());
         System.out.println(node.getData());
      }
   }

   @Override
   public Iterator<T> getLevelOrderIterator() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getLevelOrderIterator'");
   }

   @Override
   public void setTree(T rootData) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setTree'");
   }
} // end BinaryTree
