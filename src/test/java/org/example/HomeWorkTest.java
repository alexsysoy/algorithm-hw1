package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkTest {

    private final HomeWork homeWork = new HomeWork();

    @ParameterizedTest(name = "integers {0} predicate {1} exp {2}")
    @CsvSource(value = {"3,4,5,7,8,11:7:3",
            "1,2,3,4,5,6,72:3:2",
            "5,20,1,2000,45:1:0",
            "1,2,3,4,5:0:0",
            "1,2,3,4,5:4:3",
            "1:1:0",
            "2:1:0",
            "0,0,0:0:0",
            "0,0,0,0,0:1:5"}
            , delimiter = ':')
    void partitionBy(String integers, int predicate, int expected) {
        assertEquals(expected, homeWork.partitionBy(createList(integers), ((x) -> x < predicate)));
    }

    @ParameterizedTest(name = "integers {0} pos {1} exp {2}")
    @CsvSource(value = {"3,4,5,7,8,11:3:7",
            "1,2,3,4,5,6,72:3:4",
            "5,20,1,2000,45:1:20",
            "1,2,3,4,5:0:1",
            "1,2,3,4,5:4:5",
            "1:1:",
            "0,0,0:0:0",
            "0,0,0,0,0:1:0",
            "3,4,5:10:"}
            , delimiter = ':')
    void findNthElement(String integers, int pos, Integer expected) {
        assertEquals(expected, homeWork.findNthElement(createList(integers), pos));
    }

    @Test
    void notValidParametersPartitionBy() {
        assertThrows(IllegalArgumentException.class, () -> homeWork.partitionBy(null, Objects::isNull));
    }

    @Test
    void notValidParametersFindNthElement() {
        assertThrows(IllegalArgumentException.class, () -> homeWork.findNthElement(null, 1));
    }

    private Node<Integer> createList(String integers) {
        return new Node<>(Arrays.stream(integers.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList()));
    }
}