package com.franc.gateway.filter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
