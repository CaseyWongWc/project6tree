package project6tree;

import java.util.Iterator;

public class BinaryTreeDemo 
{
 public static void main(String[] args) 
 {
    // @author Frank M. Carrano, Timothy M. Henry
   // @version 5.0

   // Represent each leaf as a one-node tree
   BinaryTreeInterface<String> dTree = new BinaryTree<>();
   dTree.setTree("D", null, null);

   BinaryTreeInterface<String> fTree = new BinaryTree<>();
   fTree.setTree("F", null, null);

   BinaryTreeInterface<String> gTree = new BinaryTree<>();
   gTree.setTree("G", null, null);

   BinaryTreeInterface<String> hTree = new BinaryTree<>();
   hTree.setTree("H", null, null);

   BinaryTreeInterface<String> emptyTree = new BinaryTree<>();

   // Form larger subtrees
   BinaryTreeInterface<String> eTree = new BinaryTree<>();
   eTree.setTree("E", fTree, gTree); // Subtree rooted at E

   BinaryTreeInterface<String> bTree = new BinaryTree<>();
   bTree.setTree("B", dTree, eTree); // Subtree rooted at B

   BinaryTreeInterface<String> cTree = new BinaryTree<>();
   cTree.setTree("C", emptyTree, hTree); // Subtree rooted at C

   BinaryTreeInterface<String> aTree = new BinaryTree<>();
   aTree.setTree("A", bTree, cTree); // Desired tree rooted at A

   // Display root, height, number of nodes
   System.out.println("Root of tree contains " + aTree.getRootData());
   System.out.println("Height of tree is " + aTree.getHeight());
   System.out.println("Tree has " + aTree.getNumberOfNodes() + " nodes");

 
   // Display nodes in preorder
   System.out.println("A preorder traversal visits nodes in this order:");
   Iterator<String> preorder = aTree.getPreorderIterator();
   while (preorder.hasNext())
      System.out.print(preorder.next() + " ");
   System.out.println();

   
   //Display nodes in inorder
   System.out.println("An inorder traversal visits nodes in this order:");
   Iterator<String> inOrder = aTree.getInorderIterator();
   while (inOrder.hasNext())
   {
      System.out.print(inOrder.next()+" ");
   }
   System.out.println();
   
   // Display nodes post postorder
   System.out.println("An postorder traversal visits nodes in this order:");
   Iterator<String> postOrder = aTree.getPostorderIterator();
   while (postOrder.hasNext()) 
   {
      System.out.print(postOrder.next() + " ");
   }
   System.out.println();

  // Display nodes post levelorder
  System.out.println("A levelorder traversal visits nodes in this order:");
  Iterator<String> levelorder = aTree.getLevelOrderIterator();
  while (levelorder.hasNext()) 
  {
     System.out.print(levelorder.next() + " ");
  }
  System.out.println();  
 }
 
 /* 
 private void preorderTraverse(BinaryNode<String> node)
 {
    if(node !=null)
    {
       System.out.println(node.getData());
       preorderTraverse(node.getLeftChild());
       preorderTraverse(node.getRightChild());
    }
 }*/
}


