package io.goza.binarytree;

import io.goza.binarytree.exception.NodeNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private BinaryTreeNode<T> root;

    public List<T> traverse() {
        return traverse(root);
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

    public void delete(T value) throws NodeNotFoundException {
        root = delete(root, value);
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    private BinaryTreeNode<T> delete(BinaryTreeNode<T> node, T value) throws NodeNotFoundException {
        if (node == null) {
            throw new NodeNotFoundException(value);
        }

        if (node.getValue() == null){
            return null;
        }

        return switch (node.getValue().compareTo(value)) {
            case 1 -> {
                node.setLeft(delete(node.getLeft(), value));
                yield node;
            }
            case -1 -> {
                node.setRight(delete(node.getRight(), value));
                yield node;
            }
            case 0 -> {
                if (node.getLeft() == null) {
                    yield node.getRight();
                }
                if (node.getRight() == null) {
                    yield node.getLeft();
                }
                var min = min(node.getRight());
                yield new BinaryTreeNode<>(min, node.getLeft(), delete(node.getRight(), min));
            }
            default -> node;
        };
    }

    private T min(BinaryTreeNode<T> node) {
        if (node.getLeft() != null) {
            return min(node.getLeft());
        }
        return node.getValue();
    }

    private List<T> traverse(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        ArrayList<T> items = new ArrayList<>();
        var leftItems = traverse(node.getLeft());
        if (leftItems != null) {
            items.addAll(leftItems);
        }
        items.add(node.getValue());
        var rightItems = traverse(node.getRight());
        if (rightItems != null) {
            items.addAll(rightItems);
        }
        return items;
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
