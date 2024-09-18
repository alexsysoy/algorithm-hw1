package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Реализация базовой структуры односвязного списка
 * При необходимости, можно доработать реализацию
 *
 * @param <T>
 */
@Getter
@Setter
public class Node<T> {

    private T value;

    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node(List<T> list) {
        if (list.size() == 1) {
            this.value = list.get(0);
        }
        if (list.size() > 1) {
            this.value = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                this.add(list.get(i));
            }
        }
    }

    public Node<T> add(T value) {
        Node<T> emptyNode = this;
        while (emptyNode.getNext() != null) {
            emptyNode = emptyNode.getNext();
        }
        Node<T> newNode = new Node<>(value);
        emptyNode.setNext(newNode);
        return newNode;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("node values: ");
        result.append(this.getValue());
        Node<T> node = this;
        while (node.getNext() != null) {
            node = node.getNext();
            result.append(" ").append(node.getValue());
        }
        return result.toString();
    }
}
