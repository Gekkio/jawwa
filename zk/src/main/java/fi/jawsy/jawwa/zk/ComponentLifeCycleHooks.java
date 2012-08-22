package fi.jawsy.jawwa.zk;

import lombok.val;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.UiLifeCycle;

import com.google.common.collect.ImmutableSet;

import fi.jawsy.jawwa.lang.Effect;

public class ComponentLifeCycleHooks implements UiLifeCycle {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    static ScopeAttribute<ImmutableSet<Effect<? extends Component>>> COMPONENT_ATTACH_ATTRIBUTE = ScopeAttribute.createUnchecked(ComponentLifeCycleHooks.class,
            "ComponentAttach", ImmutableSet.class);

    static ScopeAttribute<ImmutableSet<Effect<? extends Component>>> COMPONENT_DETACH_ATTRIBUTE = ScopeAttribute.createUnchecked(ComponentLifeCycleHooks.class,
            "ComponentDetach", ImmutableSet.class);

    public static <C extends Component> void onComponentAttach(C component, Effect<C> effect) {
        register(component, COMPONENT_ATTACH_ATTRIBUTE, effect);
    }

    public static <C extends Component> void onComponentDetach(C component, Effect<C> effect) {
        register(component, COMPONENT_ATTACH_ATTRIBUTE, effect);
    }

    private static <C extends Component> void register(C component, ScopeAttribute<ImmutableSet<Effect<? extends Component>>> attribute, Effect<C> effect) {
        ImmutableSet.Builder<Effect<? extends Component>> effects = ImmutableSet.builder();
        for (val existing : attribute.getValue(component)) {
            effects.addAll(existing);
        }
        effects.add(effect);
        attribute.setValue(component, effects.build());
    }

    @Override
    public void afterComponentAttached(Component comp, Page page) {
        executeAttachListeners(comp);

        for (val child : comp.getChildren()) {
            afterComponentAttached(child, page);
        }
    }

    @Override
    public void afterComponentDetached(Component comp, Page prevpage) {
        executeDetachListeners(comp);

        for (val child : comp.getChildren()) {
            afterComponentDetached(child, prevpage);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void executeAttachListeners(Component comp) {
        for (ImmutableSet<Effect<? extends Component>> effects : COMPONENT_ATTACH_ATTRIBUTE.getValue(comp)) {
            for (Effect effect : effects) {
                try {
                    effect.apply(comp);
                } catch (RuntimeException e) {
                    log.error("Error in component attach listener", e);
                }
            }
        }
        return;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void executeDetachListeners(Component comp) {
        for (ImmutableSet<Effect<? extends Component>> effects : COMPONENT_DETACH_ATTRIBUTE.getValue(comp)) {
            for (Effect effect : effects) {
                try {
                    effect.apply(comp);
                } catch (RuntimeException e) {
                    log.error("Error in component attach listener", e);
                }
            }
        }
        return;
    }

    @Override
    public void afterComponentMoved(Component parent, Component child, Component prevparent) {
    }

    @Override
    public void afterPageAttached(Page page, Desktop desktop) {
    }

    @Override
    public void afterPageDetached(Page page, Desktop prevdesktop) {
    }

}