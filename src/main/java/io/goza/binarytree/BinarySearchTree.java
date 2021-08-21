package io.goza.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable> {

    private BinaryTreeNode<T> root;

    public List<T> traverse(){
        return traverse(root);
    }

    private List<T> traverse(BinaryTreeNode<T> node){
        if (node == null){
            return null;
        }
        ArrayList<T> items = new ArrayList<T>();
        var leftItems = traverse(node.getLeft());
        if (leftItems != null){
            items.addAll(leftItems);
        }
        items.add(node.getValue());
        var rightItems = traverse(node.getRight());
        if (rightItems != null){
            items.addAll(rightItems);
        }
        return items;
    }

    public BinaryTreeNode<T> search(T value) {
        return search(root, value);
    }

    public void insert(T value) {
        if (root == null) {
            root = new BinaryTreeNode<>(value);
            return;
        }
        insert(root, value);
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    private BinaryTreeNode<T> search(BinaryTreeNode<T> tree, T value) {
        if (tree == null) {
            return null;
        }

        if (tree.getValue().equals(value)) {
            return tree;
        }

        var targetNode = tree.searchNode(value);
        return search(targetNode, value);
    }

    private void insert(BinaryTreeNode<T> tree, T newValue) {
        var targetNode = tree.searchNode(newValue);
        if (targetNode != null) {
            insert(targetNode, newValue);
            return;
        }

        var node = new BinaryTreeNode<>(newValue);
        switch (tree.getValue().compareTo(newValue)) {
            case 1, 0 -> tree.setLeft(node);
            case -1 -> tree.setRight(node);
        }
    }
}
