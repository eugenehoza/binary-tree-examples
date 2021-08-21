package io.goza.binarytree;

import java.util.Objects;

class BinaryTreeNode<T extends Comparable> implements Comparable<T> {

    private T value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(T value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> searchNode(T value) {
        return switch (this.compareTo(value)) {
            case 1, 0 -> this.left;
            case -1 -> this.right;
            default -> null;
        };
    }

    public T getValue() {
        return value;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(T o) {
        if (value == null) {
            return 0;
        }
        return value.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryTreeNode)) return false;
        BinaryTreeNode<?> bstNode = (BinaryTreeNode<?>) o;
        return Objects.equals(value, bstNode.value) && Objects.equals(left, bstNode.left) && Objects.equals(right, bstNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
