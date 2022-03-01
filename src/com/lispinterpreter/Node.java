package com.lispinterpreter;

public class Node<E> {
    protected E data;   // Store the node data
    protected Node<E> nextElement; // Store a reference to the next element

    /**
     * Create a node with all the params.
     * @param data
     * @param nextElement
     */
    public Node(E data, Node<E> nextElement) {
        this.data = data;
        this.nextElement = nextElement;
    }

    /**
     * Create a Node with only the data (nextElement is null).
     * @param data
     */
    public Node(E data) {
        this(data, null);
    }

    /**
     * Get the next Node
     * @return
     */
    public Node<E> next() {return this.nextElement;}

    /**
     * Set the next node
     * @param next
     */
    public void setNext(Node<E> next) {
        this.nextElement = next;
    }

    /**
     * Get the current value for the data
     * @return
     */
    public E value() {
        return this.data;
    }

    /**
     * Set the new value for data
     * @param value
     */
    public void setValue(E value) {
        this.data = value;
    }

}
