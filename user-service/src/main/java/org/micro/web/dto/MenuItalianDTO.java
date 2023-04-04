package org.micro.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.micro.entity.ItalianMenu;
import org.micro.entity.Menu;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MenuItalianDTO {
    private Menu title;
    private List<ItalianMenu> items;
}
