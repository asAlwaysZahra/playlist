package com.example.PlayList.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LinkedList {
    private Node header;
    private Node trailer;
    private int size;

    public LinkedList() {
        this.header = new Node(null, null, null);
        this.trailer = new Node(null, null, header);
        header.setNext(trailer);
        this.size = 0;
    }

    /* ------------------------ LinkedList Methods ------------------------ */

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Music first() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty");
        return header.getNext().getData();
    }

    public Music last() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty");
        return trailer.getPrevious().getData();
    }

    private void addBetween(Music value, Node before, Node after) {
        Node newNode = new Node(value, before, after);
        before.setNext(newNode);
        after.setPrevious(newNode);
        size++;
    }

    public void addFirst(Music value) {
        addBetween(value, header, header.getNext());
    }

    public void addLast(Music value) {
        addBetween(value, trailer.getPrevious(), trailer);
    }

    public Music remove(Node node) {
        Node before = node.getPrevious();
        Node after = node.getNext();
        before.setNext(after);
        after.setPrevious(before);
        size--;
        return node.getData();
    }

    public Music removeFirst() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty");
        return remove(header.getNext());
    }

    public Music removeLast() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty");
        return remove(trailer.getPrevious());
    }

}
