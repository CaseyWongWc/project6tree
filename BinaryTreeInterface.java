package project6tree;

public interface BinaryTreeInterface <T> extends TreeInterface <T>, TreeIteratorInterface <T>
{
    /** sets this binary tree to a new one-node binary tree.
     * @param rootData The object that is the data for the new tree's root.
     */

     public void setTree(T rootData);


     /**sets this binary tree to a new binary tree.
      @param rootData the object that is the data for the new tree's root.
      @param leftTree the left subtree of the new tree.
      @param rightTree the right subtree of the new tree
      */
      public void setTree(T rootData, BinaryTreeInterface<T> leftTree,BinaryTreeInterface<T> rightTree);      

      
}
