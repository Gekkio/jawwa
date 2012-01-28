package fi.jawsy.jawwa.zk.raphaeljs;

import java.io.IOException;

import lombok.Getter;
import lombok.val;

import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zk.ui.sys.JavaScriptValue;

import com.google.common.base.Objects;

public class Raphael extends HtmlBasedComponent {

    private static final long serialVersionUID = -984469519140425936L;

    @Getter
    private Renderer renderer;
    @Getter
    private boolean renderOnSize;

    public static interface Renderer {
        void render(JsPaper paper);
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);

        if (this.renderer != null)
            renderer.render("jsRenderer", toJavascript(this.renderer));

        if (renderOnSize)
            renderer.render("renderOnSize", renderOnSize);
    }

    public void setRenderer(Renderer renderer) {
        if (!Objects.equal(this.renderer, renderer)) {
            smartUpdate("jsRenderer", toJavascript(renderer));
        }
        this.renderer = renderer;
    }

    private JavaScriptValue toJavascript(Renderer renderer) {
        if (renderer == null)
            return null;

        val paper = new JsPaper();
        renderer.render(paper);

        val sb = new StringBuilder();
        sb.append("function(paper){");
        sb.append(paper.toJavascript());
        sb.append('}');

        return new JavaScriptValue(sb.toString());
    }

    public void setRenderOnSize(boolean renderOnSize) {
        if (this.renderOnSize != renderOnSize) {
            this.renderOnSize = renderOnSize;
            smartUpdate("renderOnSize", renderOnSize);
        }
    }

}
