package com.learning.binarytree;

public class Node<T> {
    public static void main(String[] args) {
        System.out.println("sss");
    }
    private T Data;
    private  Node left;
    private Node right;

    public Node(T value){
        Data=value;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
