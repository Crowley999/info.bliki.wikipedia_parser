package info.bliki.api;

import org.junit.Test;

import java.util.List;

import static info.bliki.api.Fixtures.xml;
import static org.fest.assertions.api.Assertions.assertThat;

public class XMLPagesParserTest {
    @Test public void testParsePage() throws Exception {
        XMLPagesParser parser = new XMLPagesParser(xml("fooPage"));
        parser.parse();

        List<Page> pages = parser.getPagesList();

        assertThat(pages).hasSize(1);
        Page page = pages.get(0);
        assertThat(page.getTitle()).isEqualTo("foo");
    }
}
