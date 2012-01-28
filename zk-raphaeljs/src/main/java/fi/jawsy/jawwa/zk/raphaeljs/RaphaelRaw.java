package fi.jawsy.jawwa.zk.raphaeljs;

import java.io.IOException;

import lombok.Getter;

import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zk.ui.sys.JavaScriptValue;

import com.google.common.base.Objects;

public class RaphaelRaw extends HtmlBasedComponent {

    private static final long serialVersionUID = -3165363063828804301L;

    @Getter
    private String jsRenderer;

    @Getter
    private boolean renderOnSize;

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);

        if (renderOnSize)
            renderer.render("renderOnSize", "renderOnSize");
        if (this.jsRenderer != null)
            renderer.render("jsRenderer", new JavaScriptValue(this.jsRenderer));
    }

    public void setJsRenderer(String jsRenderer) {
        if (!Objects.equal(this.jsRenderer, jsRenderer)) {
            this.jsRenderer = jsRenderer;
            if (jsRenderer == null) {
                smartUpdate("jsRenderer", null);
            } else {
                smartUpdate("jsRenderer", new JavaScriptValue(jsRenderer));
            }
        }
    }

    public void setRenderOnSize(boolean renderOnSize) {
        if (this.renderOnSize != renderOnSize) {
            this.renderOnSize = renderOnSize;
            smartUpdate("renderOnSize", renderOnSize);
        }
    }

}
