package com.mpowered.assessmentservice.pojo;

import lombok.Data;

@Data
public class Pageable {

    private int count;
    private int offset;
    private int total;

}

