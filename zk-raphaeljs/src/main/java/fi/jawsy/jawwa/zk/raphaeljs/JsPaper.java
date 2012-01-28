package fi.jawsy.jawwa.zk.raphaeljs;

import java.util.List;

import lombok.val;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class JsPaper {

    private static final JsNumber WIDTH = new JsNumber.NumberExpression("paper.width");
    private static final JsNumber HEIGHT = new JsNumber.NumberExpression("paper.height");

    private int varCounter = 1;

    private final List<JsExpression> statements = Lists.newArrayList();

    JsPaper() {
    }

    void addStatement(JsExpression statement) {
        statements.add(statement);
    }

    public JsNumber getWidth() {
        return WIDTH;
    }

    public JsNumber getHeight() {
        return HEIGHT;
    }

    public void clear() {
        addStatement(JsExp.raw("paper.clear()"));
    }

    public JsCircle circle(int x, int y, int r) {
        return new JsCircle(this, JsExp.wrap(x), JsExp.wrap(y), JsExp.wrap(r));
    }

    public JsCircle circle(JsNumber x, int y, int r) {
        return new JsCircle(this, x, JsExp.wrap(y), JsExp.wrap(r));
    }

    public JsCircle circle(JsNumber x, JsNumber y, int r) {
        return new JsCircle(this, x, y, JsExp.wrap(r));
    }

    public JsCircle circle(JsNumber x, JsNumber y, JsNumber r) {
        return new JsCircle(this, x, y, r);
    }

    public JsCircle circle(int x, JsNumber y, int r) {
        return new JsCircle(this, JsExp.wrap(x), y, JsExp.wrap(r));
    }

    public JsCircle circle(int x, JsNumber y, JsNumber r) {
        return new JsCircle(this, JsExp.wrap(x), y, r);
    }

    public JsCircle circle(int x, int y, JsNumber r) {
        return new JsCircle(this, JsExp.wrap(x), JsExp.wrap(y), r);
    }

    String nextVariableName() {
        val name = "va" + varCounter;
        varCounter += 1;
        return name;
    }

    String toJavascript() {
        return Joiner.on(';').join(Iterables.transform(statements, JsExp.PRINT));
    }

}
