package com.spring.course.auto.shop.types;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> {
    private int pageSize;
    private int page;
    private int totalPages;
    private List<T> values;
}
