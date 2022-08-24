package io.github.scrumboot.graphql.type;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Bing D. Yee
 * @since 2022/08/24
 */
@Data
public class Paper {

    private Long id;

    private String title;

    private String abbr;

    private String factor;

    private String publishDate;

}
