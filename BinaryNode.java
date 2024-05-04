package project6tree;

public class BinaryNode <T>
{
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode()
    {
        this(null); //call next constructor
    }

    public BinaryNode(T dataPortion)
    {
        this(dataPortion,null,null); //call next constructor
    }

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild)
    {
        data= dataPortion;
        leftChild = newLeftChild;
        rightChild = newRightChild;
    }

    /** Retrieves the data portion of this node.
    @return  The object in the data portion of the node. */
    public T getData()
    {
        return data;
    }

    /** Sets the data portion of this node.
    @param newData  The data object. */
    public void setData(T newData)
    {
        data = newData;
    }

    /** Retrieves the left child of this node.
    @return  A reference to this node's left child. */
    public BinaryNode<T> getLeftChild()
    {
        return leftChild;
    }

    /** Sets this node's left child to a given node.
    @param newLeftChild  A node that will be the left child. */
    public void setLeftChild(BinaryNode<T> newLeftChild)
    {
        leftChild = newLeftChild;
    }

    /** Detects whether this node has a left child.
    @return  True if the node has a left child. */
    public boolean hasLeftChild()
    {
        return leftChild != null;
    }

    public BinaryNode<T> getRightChild()
    {
        return rightChild;
    }


    public void setRightChild(BinaryNode<T> newRightChild)
    {
        rightChild = newRightChild;
    }


    public boolean hasRightChild()
    {
        return rightChild != null;
    }

    
    /** Detects whether this node is a leaf.
    @return  True if the node is a leaf. */
    public boolean isLeaf()
    {
        return (leftChild == null) && (rightChild == null);
    }

    /** Counts the nodes in the subtree rooted at this node.
    @return  The number of nodes in the subtree rooted at this node. */
    public int getNumberOfNodes()
    {
        int leftNumber  =0;
        int rightNumber =0;

        if (leftChild != null)
        {
            leftNumber =leftChild.getNumberOfNodes();
        }
        if (rightChild != null)
        {
            rightNumber =  rightChild.getNumberOfNodes();
        }
        return 1 + leftNumber + rightNumber;
    }

    /** Computes the height of the subtree rooted at this node.
    @return  The height of the subtree rooted at this node. */
    public int getHeight()
    {
        return getHeight(this); // Call private getHeight
    }

    private int getHeight(BinaryNode<T> node)
    {
        int height =0;
        if (node != null)
        {
            height = 1 + Math.max(getHeight(node.getLeftChild()),
                                getHeight(node.getRightChild()));
        }
        return height;
    }

    /** Copies the subtree rooted at this node.
    @return  The root of a copy of the subtree rooted at this node. */
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if (leftChild != null)
        {
            newRoot.setLeftChild(leftChild.copy());
        }
        if (rightChild != null)
        {
            newRoot.setRightChild(rightChild.copy());
        }
        return newRoot;
    }

}
