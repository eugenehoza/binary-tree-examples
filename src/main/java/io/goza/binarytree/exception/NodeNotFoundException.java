package io.goza.binarytree.exception;

public class NodeNotFoundException extends Exception {
    private Object value;

    public NodeNotFoundException(Object value) {
        this.value = value;
    }

    public Object getNode() {
        return value;
    }
}
