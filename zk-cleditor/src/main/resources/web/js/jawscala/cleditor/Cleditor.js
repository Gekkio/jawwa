zk.$package('jawwa.cleditor');

jawwa.cleditor.Cleditor = zk.$extends(zk.Widget, {
  _inner: null,
  _editor: null,
  disabled: false,
  value: null,

  setDisabled: function(value) {
    this.disabled = value;
    if (this.desktop) {
      this._editor.disable(value);
    }
  },
  setValue: function(value) {
    this.value = value;
    if (this.desktop) {
      this._inner.val(this.value);
      this._editor.updateFrame();
    }
  },
  isDisabled: function() {
    return this.disabled;
  },
  getValue: function() {
    return this.value;
  },
  bind_: function(desktop, skipper, after) {
    this.$supers('bind_', arguments);
    this._inner = jq('<textarea></textarea>').appendTo(this.$n());
    this._inner.val(this.value);
    this._editor = this._inner.cleditor({
      width: 'auto',
      height: this.getHeight()
    })[0];
    this._editor.disable(this.disabled);

    var me = this;
    this._editor.change(function() {
      me.value = me._inner.val();
      me.fire('onChange', {
        value: me.value
      });
    });

    zWatch.listen({
      onShow: this
    });
  },
  onShow: function() {
    if (this._editor) {
      this._editor.refresh();
    }
  },
  unbind_: function(skipper, after) {
    this._editor.remove();
    this._editor = null;
    this._inner.remove();
    this._inner = null;
    zWatch.unlisten({
      onShow: this
    });
    this.$supers('unbind_', arguments);
  },

  redraw: function(out) {
    out.push('<div ', this.domAttrs_(), '>');
    out.push('</div>');
  }
});
