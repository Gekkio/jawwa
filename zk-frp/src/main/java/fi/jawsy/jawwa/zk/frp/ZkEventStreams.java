package fi.jawsy.jawwa.zk.frp;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.ErrorEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.MaximizeEvent;
import org.zkoss.zk.ui.event.MinimizeEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.MoveEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.ScrollEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.SelectionEvent;
import org.zkoss.zk.ui.event.SizeEvent;
import org.zkoss.zk.ui.event.SortEvent;
import org.zkoss.zk.ui.event.URIEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.event.ZIndexEvent;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Combobutton;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Group;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.LayoutRegion;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Slider;
import org.zkoss.zul.Splitter;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.ColSizeEvent;
import org.zkoss.zul.event.PageSizeEvent;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.event.ZulEvents;
import org.zkoss.zul.impl.HeadersElement;
import org.zkoss.zul.impl.InputElement;

public final class ZkEventStreams {

    public static ZkEventStream<Event> onAfterRender(Combobox component) {
        return new ZkEventStream<Event>(component, ZulEvents.ON_AFTER_RENDER);
    }

    public static ZkEventStream<Event> onAfterRender(Grid component) {
        return new ZkEventStream<Event>(component, ZulEvents.ON_AFTER_RENDER);
    }

    public static ZkEventStream<Event> onAfterRender(Listbox component) {
        return new ZkEventStream<Event>(component, ZulEvents.ON_AFTER_RENDER);
    }

    public static ZkEventStream<Event> onAfterRender(Tree component) {
        return new ZkEventStream<Event>(component, ZulEvents.ON_AFTER_RENDER);
    }

    public static ZkEventStream<Event> onBlur(Button component) {
        return new ZkEventStream<Event>(component, Events.ON_BLUR);
    }

    public static ZkEventStream<Event> onBlur(Checkbox component) {
        return new ZkEventStream<Event>(component, Events.ON_BLUR);
    }

    public static ZkEventStream<Event> onBlur(InputElement component) {
        return new ZkEventStream<Event>(component, Events.ON_BLUR);
    }

    public static ZkEventStream<Event> onBlur(Listbox component) {
        return new ZkEventStream<Event>(component, Events.ON_BLUR);
    }

    public static ZkEventStream<Event> onBlur(Tree component) {
        return new ZkEventStream<Event>(component, Events.ON_BLUR);
    }

    public static ZkEventStream<BookmarkEvent> onBookmarkChange(Component component) {
        return new ZkEventStream<BookmarkEvent>(component, Events.ON_BOOKMARK_CHANGE);
    }

    public static ZkEventStream<KeyEvent> onCancel(HtmlBasedComponent component) {
        return new ZkEventStream<KeyEvent>(component, Events.ON_CANCEL);
    }

    public static ZkEventStream<InputEvent> onChange(InputElement component) {
        return new ZkEventStream<InputEvent>(component, Events.ON_CHANGE);
    }

    public static ZkEventStream<InputEvent> onChanging(InputElement component) {
        return new ZkEventStream<InputEvent>(component, Events.ON_CHANGING);
    }

    public static ZkEventStream<CheckEvent> onCheck(Checkbox component) {
        return new ZkEventStream<CheckEvent>(component, Events.ON_CHECK);
    }

    public static ZkEventStream<CheckEvent> onCheck(Menuitem component) {
        return new ZkEventStream<CheckEvent>(component, Events.ON_CHECK);
    }

    public static ZkEventStream<CheckEvent> onCheck(Radiogroup component) {
        return new ZkEventStream<CheckEvent>(component, Events.ON_CHECK);
    }

    public static ZkEventStream<CheckEvent> onCheck(Toolbarbutton component) {
        return new ZkEventStream<CheckEvent>(component, Events.ON_CHECK);
    }

    public static ZkEventStream<MouseEvent> onClick(HtmlBasedComponent component) {
        return new ZkEventStream<MouseEvent>(component, Events.ON_CLICK);
    }

    public static ZkEventStream<ClientInfoEvent> onClientInfo(Component component) {
        return new ZkEventStream<ClientInfoEvent>(component, Events.ON_CLIENT_INFO);
    }

    public static ZkEventStream<OpenEvent> onClose(Panel component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_CLOSE);
    }

    public static ZkEventStream<Event> onClose(Tab component) {
        return new ZkEventStream<Event>(component, Events.ON_CLOSE);
    }

    public static ZkEventStream<Event> onClose(Window component) {
        return new ZkEventStream<Event>(component, Events.ON_CLOSE);
    }

    public static ZkEventStream<ColSizeEvent> onColSize(HeadersElement component) {
        return new ZkEventStream<ColSizeEvent>(component, ZulEvents.ON_COL_SIZE);
    }

    public static ZkEventStream<KeyEvent> onCtrlKey(HtmlBasedComponent component) {
        return new ZkEventStream<KeyEvent>(component, Events.ON_CTRL_KEY);
    }

    public static ZkEventStream<MouseEvent> onDoubleClick(HtmlBasedComponent component) {
        return new ZkEventStream<MouseEvent>(component, Events.ON_DOUBLE_CLICK);
    }

    public static ZkEventStream<DropEvent> onDrop(HtmlBasedComponent component) {
        return new ZkEventStream<DropEvent>(component, Events.ON_DROP);
    }

    public static ZkEventStream<ErrorEvent> onError(InputElement component) {
        return new ZkEventStream<ErrorEvent>(component, Events.ON_ERROR);
    }

    public static ZkEventStream<Event> onFocus(Button component) {
        return new ZkEventStream<Event>(component, Events.ON_FOCUS);
    }

    public static ZkEventStream<Event> onFocus(Checkbox component) {
        return new ZkEventStream<Event>(component, Events.ON_FOCUS);
    }

    public static ZkEventStream<Event> onFocus(InputElement component) {
        return new ZkEventStream<Event>(component, Events.ON_FOCUS);
    }

    public static ZkEventStream<Event> onFocus(Listbox component) {
        return new ZkEventStream<Event>(component, Events.ON_FOCUS);
    }

    public static ZkEventStream<Event> onFocus(Tree component) {
        return new ZkEventStream<Event>(component, Events.ON_FOCUS);
    }

    public static ZkEventStream<Event> onGroup(Column component) {
        return new ZkEventStream<Event>(component, Events.ON_GROUP);
    }

    public static ZkEventStream<MaximizeEvent> onMaximize(Panel component) {
        return new ZkEventStream<MaximizeEvent>(component, Events.ON_MAXIMIZE);
    }

    public static ZkEventStream<MaximizeEvent> onMaximize(Window component) {
        return new ZkEventStream<MaximizeEvent>(component, Events.ON_MAXIMIZE);
    }

    public static ZkEventStream<MinimizeEvent> onMinimize(Panel component) {
        return new ZkEventStream<MinimizeEvent>(component, Events.ON_MINIMIZE);
    }

    public static ZkEventStream<MinimizeEvent> onMinimize(Window component) {
        return new ZkEventStream<MinimizeEvent>(component, Events.ON_MINIMIZE);
    }

    public static ZkEventStream<MoveEvent> onMouseOut(HtmlBasedComponent component) {
        return new ZkEventStream<MoveEvent>(component, Events.ON_MOUSE_OUT);
    }

    public static ZkEventStream<MoveEvent> onMouseOver(HtmlBasedComponent component) {
        return new ZkEventStream<MoveEvent>(component, Events.ON_MOUSE_OVER);
    }

    public static ZkEventStream<MoveEvent> onMove(Panel component) {
        return new ZkEventStream<MoveEvent>(component, Events.ON_MOVE);
    }

    public static ZkEventStream<MoveEvent> onMove(Window component) {
        return new ZkEventStream<MoveEvent>(component, Events.ON_MOVE);
    }

    public static ZkEventStream<KeyEvent> onOk(HtmlBasedComponent component) {
        return new ZkEventStream<KeyEvent>(component, Events.ON_OK);
    }

    public static ZkEventStream<OpenEvent> onOpen(Bandbox component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Combobox component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Combobutton component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Detail component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Group component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Groupbox component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(LayoutRegion component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Listgroup component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Panel component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Splitter component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Treeitem component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<OpenEvent> onOpen(Window component) {
        return new ZkEventStream<OpenEvent>(component, Events.ON_OPEN);
    }

    public static ZkEventStream<PageSizeEvent> onPageSize(Grid component) {
        return new ZkEventStream<PageSizeEvent>(component, ZulEvents.ON_PAGE_SIZE);
    }

    public static ZkEventStream<PageSizeEvent> onPageSize(Listbox component) {
        return new ZkEventStream<PageSizeEvent>(component, ZulEvents.ON_PAGE_SIZE);
    }

    public static ZkEventStream<PageSizeEvent> onPageSize(Tree component) {
        return new ZkEventStream<PageSizeEvent>(component, ZulEvents.ON_PAGE_SIZE);
    }

    public static ZkEventStream<PagingEvent> onPaging(Paging component) {
        return new ZkEventStream<PagingEvent>(component, ZulEvents.ON_PAGING);
    }

    public static ZkEventStream<MouseEvent> onRightClick(HtmlBasedComponent component) {
        return new ZkEventStream<MouseEvent>(component, Events.ON_RIGHT_CLICK);
    }

    public static ZkEventStream<ScrollEvent> onScroll(Slider component) {
        return new ZkEventStream<ScrollEvent>(component, Events.ON_SCROLL);
    }

    public static ZkEventStream<ScrollEvent> onScrolling(Slider component) {
        return new ZkEventStream<ScrollEvent>(component, Events.ON_SCROLLING);
    }

    public static <T> ZkEventStream<SelectEvent<Comboitem, T>> onSelect(Combobox component) {
        return new ZkEventStream<SelectEvent<Comboitem, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Comboitem, T>> onSelect(Combobox component, Class<T> dataClass) {
        return new ZkEventStream<SelectEvent<Comboitem, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Listitem, T>> onSelect(Listbox component) {
        return new ZkEventStream<SelectEvent<Listitem, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Listitem, T>> onSelect(Listbox component, Class<T> dataClass) {
        return new ZkEventStream<SelectEvent<Listitem, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Component, T>> onSelect(Selectbox component) {
        return new ZkEventStream<SelectEvent<Component, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Component, T>> onSelect(Selectbox component, Class<T> dataClass) {
        return new ZkEventStream<SelectEvent<Component, T>>(component, Events.ON_SELECT);
    }

    public static ZkEventStream<SelectEvent<Tab, Object>> onSelect(Tab component) {
        return new ZkEventStream<SelectEvent<Tab, Object>>(component, Events.ON_SELECT);
    }

    public static ZkEventStream<SelectEvent<Tab, Object>> onSelect(Tabbox component) {
        return new ZkEventStream<SelectEvent<Tab, Object>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Treeitem, T>> onSelect(Tree component) {
        return new ZkEventStream<SelectEvent<Treeitem, T>>(component, Events.ON_SELECT);
    }

    public static <T> ZkEventStream<SelectEvent<Treeitem, T>> onSelect(Tree component, Class<T> dataClass) {
        return new ZkEventStream<SelectEvent<Treeitem, T>>(component, Events.ON_SELECT);
    }

    public static ZkEventStream<SelectionEvent> onSelection(InputElement component) {
        return new ZkEventStream<SelectionEvent>(component, Events.ON_SELECTION);
    }

    public static ZkEventStream<SizeEvent> onSize(Panel component) {
        return new ZkEventStream<SizeEvent>(component, Events.ON_SIZE);
    }

    public static ZkEventStream<SizeEvent> onSize(Window component) {
        return new ZkEventStream<SizeEvent>(component, Events.ON_SIZE);
    }

    public static ZkEventStream<SortEvent> onSort(Column component) {
        return new ZkEventStream<SortEvent>(component, Events.ON_SORT);
    }

    public static ZkEventStream<Event> onSort(Listheader component) {
        return new ZkEventStream<Event>(component, Events.ON_SORT);
    }

    public static ZkEventStream<Event> onSort(Treecol component) {
        return new ZkEventStream<Event>(component, Events.ON_SORT);
    }

    public static ZkEventStream<Event> onTimer(Timer component) {
        return new ZkEventStream<Event>(component, Events.ON_TIMER);
    }

    public static ZkEventStream<Event> onTimeZoneChange(Datebox component) {
        return new ZkEventStream<Event>(component, "onTimeZoneChange");
    }

    public static ZkEventStream<UploadEvent> onUpload(Button component) {
        return new ZkEventStream<UploadEvent>(component, Events.ON_UPLOAD);
    }

    public static ZkEventStream<UploadEvent> onUpload(Fileupload component) {
        return new ZkEventStream<UploadEvent>(component, Events.ON_UPLOAD);
    }

    public static ZkEventStream<UploadEvent> onUpload(Menuitem component) {
        return new ZkEventStream<UploadEvent>(component, Events.ON_UPLOAD);
    }

    public static ZkEventStream<UploadEvent> onUpload(Toolbarbutton component) {
        return new ZkEventStream<UploadEvent>(component, Events.ON_UPLOAD);
    }

    public static ZkEventStream<URIEvent> onUriChange(Iframe component) {
        return new ZkEventStream<URIEvent>(component, Events.ON_URI_CHANGE);
    }

    public static ZkEventStream<ZIndexEvent> onZIndex(Panel component) {
        return new ZkEventStream<ZIndexEvent>(component, Events.ON_Z_INDEX);
    }

    public static ZkEventStream<ZIndexEvent> onZIndex(Window component) {
        return new ZkEventStream<ZIndexEvent>(component, Events.ON_Z_INDEX);
    }

    /**
     * Private constructor that should never be called.
     */
    private ZkEventStreams() {
        throw new UnsupportedOperationException("Private constructor called in a utility class");
    }

}