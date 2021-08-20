package com.sj.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto implements java.io.Serializable{

    private String keyword;
    private String word;
    private String startDate;
    private String endDate;
}
