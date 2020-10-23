import com.cenmo.mogu.search.SearchApplication;
import com.cenmo.mogu.search.mapper.SolrItemMapper;
import com.cenmo.mogu.search.pojo.SolrItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Cenmo
 * @Date 2020-10-23 2020/10/23
 */
@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class testMybatis {

    @Autowired
    private SolrItemMapper solrItemMapper;

    @Test
    public void test(){
        List<SolrItem> itemList = solrItemMapper.getItemList();
        System.out.println(itemList);
    }

    @Test
    public void test2(){
        long id = 847276;
        SolrItem item = solrItemMapper.getItemById(id);
        System.out.println(item);
    }
}
