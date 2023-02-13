package com.learning.binarytree;

import java.security.InvalidParameterException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable> {
    private Node<T> root;

    public void add(T value) {
        Node current = root;
        if (current == null) {
            root = new Node<>(value);
        } else {
            while (true) {
                int sub = value.compareTo(current.getData());
                if (sub < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(new Node(value));
                        break;
                    } else {
                        current = current.getLeft();
                    }
                } else if (sub > 0) {
                    if (current.getRight() == null) {
                        current.setRight(new Node(value));
                        break;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    throw new InvalidParameterException("不能有重复值");
                }
            }
        }
    }

    public T find(T value) {
        Node<T> current = root;
        while (true) {
            if (current == null)
                return null;
            int sub = value.compareTo(current.getLeft());
            if (sub == 0) {
                return current.getData();
            } else if (sub < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (true) {
            if (current == null)
                return false;
            int sub = value.compareTo(current.getLeft());
            if (sub == 0) {
                return true;
            } else if (sub < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
    }

    public void preorder() {
        preorderPrint(root);
    }

    public void inorder() {
        inorderPrint(root);
    }

    public void postorder() {
        postorderPrint(root);
    }

    private void preorderPrint(Node root) {
        if (root == null)
            return;
        System.out.print(root.getData() + " ");
        preorderPrint(root.getLeft());
        preorderPrint(root.getRight());
    }

    private void inorderPrint(Node root) {
        if (root == null)
            return;
        inorderPrint(root.getLeft());
        System.out.print(root.getData() + " ");
        inorderPrint(root.getRight());
    }

    private void postorderPrint(Node root) {
        if (root == null)
            return;
        postorderPrint(root.getLeft());
        postorderPrint(root.getRight());
        System.out.print(root.getData() + " ");
    }

    private void preorderNoRecursion() {
        Stack<Node> stack = new Stack<>();
        if (root == null) return;
        Node current=root;
        while (!stack.isEmpty() || current!=null) {
            while (current != null) {

                System.out.print(current.getData()+" ");
                stack.push(current);
                current=current.getLeft();
            }
            current=stack.pop();
            current=current.getRight();

        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(3);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(5);
        bst.add(6);
        System.out.println(bst.toString());
        bst.preorder();
        System.out.println();
        bst.preorderNoRecursion();
        System.out.println();
        bst.inorder();
        System.out.println();
        bst.postorder();
    }
}
