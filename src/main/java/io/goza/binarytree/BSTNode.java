package io.goza.binarytree;

import java.util.Objects;

class BSTNode<T extends Comparable> implements Comparable<T> {

    private T value;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(T value, BSTNode left, BSTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BSTNode(T value) {
        this.value = value;
    }

    public BSTNode<T> searchNode(T value) {
        return switch (this.compareTo(value)) {
            case 1, 0 -> this.left;
            case -1 -> this.right;
            default -> null;
        };
    }

    public T getValue() {
        return value;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
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
        if (!(o instanceof BSTNode)) return false;
        BSTNode<?> bstNode = (BSTNode<?>) o;
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
