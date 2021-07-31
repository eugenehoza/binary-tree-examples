package io.goza.binarytree;

public class BST<T extends Comparable> {

    private BSTNode<T> root;


    public BSTNode<T> search(T value) {
        return search(root, value);
    }

    protected BSTNode<T> search(BSTNode<T> tree, T value) {
        if (tree == null) {
            return null;
        }

        if (tree.getValue().equals(value)) {
            return tree;
        }

        var targetNode = tree.searchNode(value);
        return search(targetNode, value);
    }

    public void insert(T value) {
        if (root == null) {
            root = new BSTNode<>(value);
            return;
        }
        insert(root, value);
    }


    protected void insert(BSTNode<T> tree, T newValue) {
        var targetNode = tree.searchNode(newValue);
        if (targetNode != null) {
            insert(targetNode, newValue);
            return;
        }

        var node = new BSTNode<>(newValue);
        switch (tree.getValue().compareTo(newValue)) {
            case 1, 0 -> tree.setLeft(node);
            case -1 -> tree.setRight(node);
        }
    }

    public BSTNode<T> getRoot() {
        return root;
    }
}
