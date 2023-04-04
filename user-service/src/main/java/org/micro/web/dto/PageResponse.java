package org.micro.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResponse<T> {

    private MetaModal meta = new MetaModal();

    private List<T> data;

    public PageResponse(Page<T> page) {
        meta.setTotalRecords(page.getTotalElements());
        meta.setTotalFoundRecords(page.getNumberOfElements());
        meta.setPageIndex(page.getNumber());
        meta.setPageSize(page.getSize());
        setData(page.getContent());
    }
}
