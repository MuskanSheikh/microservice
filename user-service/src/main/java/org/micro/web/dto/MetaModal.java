package org.micro.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetaModal {
    private long totalRecords;
    private long totalFoundRecords;
    private long pageIndex;
    private long pageSize;
    private String pageTitle;
}
