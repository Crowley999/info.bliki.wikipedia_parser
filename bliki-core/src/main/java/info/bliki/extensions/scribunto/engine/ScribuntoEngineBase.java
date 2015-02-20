package info.bliki.extensions.scribunto.engine;

import info.bliki.wiki.filter.ParsedPageName;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.model.WikiModelContentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public abstract class ScribuntoEngineBase implements ScribuntoEngine {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final IWikiModel model;

    protected ScribuntoEngineBase(IWikiModel model) {
        this.model = model;
    }

    public ScribuntoModule fetchModuleFromParser(ParsedPageName parsedPageName) {
        try {
            String content = model.getRawWikiContent(parsedPageName, null);
            if (content == null) {
                logger.warn("no content for "+parsedPageName);
                return null;
            } else {
                return newModule(content, parsedPageName.fullPagename());
            }
        } catch (WikiModelContentException e) {
            logger.warn("error fetching content for "+parsedPageName);
            return null;
        }
    }

    /**
     * Creates a new module object within this engine
     */
    protected abstract ScribuntoModule newModule(@Nonnull String text, String chunkName);
}
