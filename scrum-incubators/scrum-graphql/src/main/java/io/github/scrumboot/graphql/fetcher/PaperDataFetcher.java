package io.github.scrumboot.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.github.scrumboot.graphql.type.Paper;
import io.github.scrumboot.graphql.type.PaperInput;
import io.github.scrumboot.langs.BeanConverter;
import io.github.scrumboot.langs.SnowflakeWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bing D. Yee
 * @since 2022/08/24
 */
@DgsComponent
public class PaperDataFetcher {

    public static final List<Paper> PAPER_LIST = new ArrayList<>();

    @DgsQuery
    public List<Paper> papers() {
        return PAPER_LIST;
    }

    @DgsMutation
    public Paper createPaper(@InputArgument PaperInput paperInput) {
        final Paper paper = BeanConverter.convert(paperInput, Paper.class);
        paper.setId(SnowflakeWorker.nextId());
        PAPER_LIST.add(paper);
        return paper;
    }

}
