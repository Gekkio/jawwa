package fi.jawsy.jawwa.zk.cleditor;

import java.io.IOException;
import java.util.Map;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;

/**
 * Rich-text editor component.
 */
public class Cleditor extends HtmlBasedComponent {

    private static final long serialVersionUID = -4308135132232837280L;

    /**
     * Event that is posted when the editor value has changed.
     * 
     * Changes are detected when focus is changed on the client side.
     */
    public static class ChangeEvent extends Event {
        private static final long serialVersionUID = -3456210455157915302L;

        public static final String NAME = "onChange";

        public final String value;

        public ChangeEvent(Cleditor target, String value) {
            super(NAME, target, null);
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append(getClass().getSimpleName());
            sb.append(' ');
            sb.append(getName());
            sb.append(' ');
            sb.append(getTarget());
            sb.append(" {value='");
            sb.append(value);
            sb.append("}]");
            return sb.toString();
        }
    }

    static {
        addClientEvent(Cleditor.class, ChangeEvent.NAME, CE_IMPORTANT | CE_REPEAT_IGNORE);
    }

    private boolean disabled;
    private String value;

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        if (this.disabled != disabled) {
            this.disabled = disabled;
            smartUpdate("disabled", this.disabled);
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            smartUpdate("value", this.value);
        }
    }

    public void service(AuRequest request, boolean everError) {
        String command = request.getCommand();
        if (ChangeEvent.NAME.equals(command)) {
            Map<?, ?> data = request.getData();
            String value = (String) data.get("value");
            this.value = value;
            Events.postEvent(new ChangeEvent(this, this.value));
            return;
        }
        super.service(request, everError);
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);

        if (this.disabled)
            renderer.render("disabled", this.disabled);
        if (this.value != null)
            renderer.render("value", this.value);
    }

}
