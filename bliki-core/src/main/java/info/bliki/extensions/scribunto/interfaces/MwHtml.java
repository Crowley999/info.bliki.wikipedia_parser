package info.bliki.extensions.scribunto.interfaces;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class MwHtml extends MwInterface {
    @Override
    public String name() {
        return "mw.html";
    }

    @Override
    public LuaTable getInterface() {
        LuaTable iface = new LuaTable();
        return iface;

    }

    @Override
    public LuaValue getSetupOptions() {
        return null;
    }
}
