package io.goza.binarytree;

import io.goza.binarytree.exception.NodeNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class BSTOperationTest {

    static class IntBST extends BinarySearchTree<Integer> {
    }

    @Test
    void whenInsertOneValue_thenBTSIsCreated() {
        var result = new IntBST();
        var expected = new BinaryTreeNode<>(5, null, null);

        result.insert(5);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertLeftRightNodes_thenBTCreated() {
        var result = new IntBST();
        var expected = new BinaryTreeNode<>(10, new BinaryTreeNode<>(3), new BinaryTreeNode<>(20));

        result.insert(10);
        result.insert(20);
        result.insert(3);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertOrderedItems_thenBTCreated() {
        var result = new IntBST();
        var expected = new BinaryTreeNode<>(0, null,
                new BinaryTreeNode<>(1, null,
                        new BinaryTreeNode<>(2, null,
                                new BinaryTreeNode<>(3, null, new BinaryTreeNode<>(4)))));

        IntStream.range(0, 5).forEach(result::insert);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertUnOrderedItems_thenBTCreated() {
        var result = new IntBST();
        var expected = new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, new BinaryTreeNode<>(2)),
                new BinaryTreeNode<>(4, null, new BinaryTreeNode<>(7)));

        Arrays.stream("3,4,1,7,2".split(",")).map(Integer::valueOf).forEach(result::insert);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenSearchByExisting_thenFound() {
        var tree = fromCommaSeparated("5,4,7,3,9,0,2,6");

        var result = tree.search(3);

        assertNotNull(result);
    }

    @Test
    void whenSearchByNotExisting_thenNotFound() {
        var tree = generateTree();

        var result = tree.search(8);

        assertNull(result);
    }

    @Test
    void whenTraverse_thenOrderedList() {
        var tree = generateTree();

        var result = tree.traverse();

        assertArrayEquals(List.of(0, 2, 3, 4, 5, 6, 7, 9, 10).toArray(), result.toArray());
    }

    @Test
    void whenDeleteExistingFromTreeWithOne_thenGotEmptyTree() {
        var tree = fromCommaSeparated("10");

        assertDoesNotThrow(() -> tree.delete(10));

        assertNull(tree.getRoot());
    }

    @Test
    void whenDeleteNonExistent_thenGetException() {
        var tree = generateTree();

        assertThrows(NodeNotFoundException.class, () -> tree.delete(8));
    }

    @Test
    void whenDeleteLeftLeaf_thenSuccessful() {
        var tree = generateTree();

        assertDoesNotThrow(() -> tree.delete(6));

        assertArrayEquals(List.of(0, 2, 3, 4, 5, 7, 9, 10).toArray(), tree.traverse().toArray());
    }

    @Test
    void whenDeleteRightLeaf_thenSuccessful() {
        var tree = generateTree();

        assertDoesNotThrow(() -> tree.delete(10));

        assertArrayEquals(List.of(0, 2, 3, 4, 5, 6, 7, 9).toArray(), tree.traverse().toArray());
    }

    @Test
    void whenDeleteNodeWithLeftChild_thenSuccessful() {
        var tree = generateTree();

        assertDoesNotThrow(() -> tree.delete(3));

        assertArrayEquals(List.of(0, 2, 4, 5, 6, 7, 9, 10).toArray(), tree.traverse().toArray());
    }

    @Test
    void whenDeleteNodeWithRightChild_thenSuccessful() {
        var tree = generateTree();

        assertDoesNotThrow(() -> tree.delete(9));

        assertArrayEquals(List.of(0, 2, 3, 4, 5, 6, 7, 10).toArray(), tree.traverse().toArray());
    }

    @Test
    void whenDeleteNodeWithRightAndLeftChildren_thenSuccessful() {
        var tree = generateTree();

        assertDoesNotThrow(() -> tree.delete(7));

        assertArrayEquals(List.of(0, 2, 3, 4, 5, 6, 9, 10).toArray(), tree.traverse().toArray());
    }

    @Test
    void whenDeleteComplicatedNode_thenSuccessful() {
        var tree = fromCommaSeparated("50,70,40,65,90,100,80,64,67");

        assertDoesNotThrow(() -> tree.delete(70));

        assertArrayEquals(List.of(40, 50, 64, 65, 67, 80, 90, 100).toArray(), tree.traverse().toArray());
    }

    private IntBST generateTree() {
        return fromCommaSeparated("5,4,7,3,9,0,2,6,10");
    }

    private IntBST fromCommaSeparated(String numbers) {
        var result = new IntBST();
        Arrays.stream(numbers.split(",")).map(Integer::valueOf).forEach(result::insert);
        return result;
    }
}
