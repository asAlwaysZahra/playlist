package com.example.PlayList.model;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Node {

    private Music data;
    private Node next;
    private Node previous;

}
