package com.example.PlayList.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    private Music data;
    private Node next;
    private Node previous;

}
