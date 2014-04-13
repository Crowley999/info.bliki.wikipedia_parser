package info.bliki.wiki.filter;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TemplateSubstParserTest extends FilterTestSupport {

    /**
     * See <a
     * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
     * Substitution</a>
     */
    @Test public void testSubst001() {
        assertThat(wikiModel.parseTemplates("{{subst:}}", false)).isEqualTo("");
        assertThat(wikiModel.parseTemplates("{{subst:Nested}}", false)).isEqualTo("a nested template text");
    }

    /**
     * See <a
     * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
     * Substitution</a>
     */
    @Test public void testSubst002() {
        assertThat(wikiModel.parseTemplates("{{subst:ns:{{subst:#expr:2*3}}}}", false)).isEqualTo("File");
    }

    /**
     * See <a
     * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
     * Substitution</a>
     */
    @Test public void testSubst003() {
        assertThat(wikiModel.parseTemplates("{{ns:{{subst:#expr:2*3}}}}", false)).isEqualTo("File");
    }

    /**
     * See <a
     * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
     * Substitution</a>
     */
    @Test public void testSubst004() {
        assertThat(wikiModel.parseTemplates("{{subst:LC:{{subst:#expr:1/100000}}}}", false)).isEqualTo("1.0e-5");
    }

    /**
     * See <a
     * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
     * Substitution</a>
     */
    @Test public void testSubst005() {
        assertThat(wikiModel.parseTemplates("{{subst:UC:{{subst:tc}}}}", false)).isEqualTo("IN");
    }

    @Test public void testSubst006() {
        // http://www.mediawiki.org/wiki/Manual:Substitution#Predefined_templates
        wikiModel.setNamespaceName("help");
        assertThat(wikiModel.parseTemplates("{{subst:t1|{{subst:NAMESPACE}}}}", true)).isEqualTo("startHelpend");
        assertThat(wikiModel.parseTemplates("{{subst:t1|{{subst:NAMESPACE}}}}", false)).isEqualTo("startHelpend");
        wikiModel.setNamespaceName("");
    }

    @Test public void testSubst007() {
        assertThat(wikiModel.parseTemplates("{{subst:#if:{{x0}}|yes|no}}")).isEqualTo("yes");
    }

    @Test public void testSubst008() {
        assertThat(wikiModel.parseTemplates("{{subst:#if:{{subst:x0}}|yes|no}}")).isEqualTo("no");
    }

    @Test public void testSubst009() {
        assertThat(wikiModel
                .parseTemplates("{{subst:#expr:2*{{{p|3}}}}}")).isEqualTo("6");
    }
}
