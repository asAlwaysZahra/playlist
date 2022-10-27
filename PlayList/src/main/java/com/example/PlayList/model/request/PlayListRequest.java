package com.example.PlayList.model.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayListRequest {
    String name;
    int size;
}
