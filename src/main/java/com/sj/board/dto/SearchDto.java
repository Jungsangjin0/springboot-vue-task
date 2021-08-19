package com.sj.board.dto;

import lombok.Data;

@Data
public class SearchDto implements java.io.Serializable{

    private String keyword;
    private String word;
    private String startDate;
    private String endDate;
}
