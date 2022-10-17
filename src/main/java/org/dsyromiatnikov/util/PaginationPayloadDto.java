package org.dsyromiatnikov.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationPayloadDto {

    private final Integer page;

    private final Integer pageSize;

    private final Integer totalElements;

    private final List<?> content;


}