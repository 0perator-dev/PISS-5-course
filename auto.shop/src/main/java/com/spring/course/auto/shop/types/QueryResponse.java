package com.spring.course.auto.shop.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> {
    private int pageSize;
    private int page;
    private int totalPages;
    private List<T> values;
}
