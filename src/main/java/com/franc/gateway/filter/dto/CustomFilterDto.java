package com.franc.gateway.filter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class CustomFilterDto {
    private String requestHeader;
    private String responseHeader;
    private boolean canRequestHeader;
    private boolean canResponseHeader;
}
