package io.goza.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class BSTOperationTest {

    class IntBST extends BST<Integer> {
    }

    @Test
    void whenInsertOneValue_thenBTSIsCreated() {
        var result = new IntBST();
        var expected = new BSTNode<Integer>(5, null, null);

        result.insert(5);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertLeftRightNodes_thenBTCreated() {
        var result = new IntBST();
        var expected = new BSTNode<>(10, new BSTNode<>(3), new BSTNode<>(20));

        result.insert(10);
        result.insert(20);
        result.insert(3);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertOrderedItems_thenBTCreated() {
        var result = new IntBST();
        var expected = new BSTNode<>(0, null,
                new BSTNode<>(1, null,
                        new BSTNode<>(2, null,
                                new BSTNode<>(3, null, new BSTNode<>(4)))));

        IntStream.range(0, 5).forEach(result::insert);

        assertEquals(expected, result.getRoot());
    }

    @Test
    void whenInsertUnOrderedItems_thenBTCreated() {
        var result = new IntBST();
        var expected = new BSTNode<>(3,
                new BSTNode<>(1, null, new BSTNode<>(2)),
                new BSTNode<>(4, null, new BSTNode<>(7)));

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
        var tree = fromCommaSeparated("5,4,7,3,9,0,2,6");

        var result = tree.search(8);

        assertNull(result);
    }


    private IntBST fromCommaSeparated(String numbers) {
        var result = new IntBST();
        Arrays.stream(numbers.split(",")).map(Integer::valueOf).forEach(result::insert);
        return result;
    }
}
