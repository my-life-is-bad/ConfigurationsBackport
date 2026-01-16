package my_life_is_bad.configurationsbackport.extentions;

import net.minecraftforge.common.config.Property;

import java.util.Map;
import java.util.WeakHashMap;

public final class PropertyExt {

    private static final Map<Property, Data> DATA = new WeakHashMap<>();

    private static Data data(Property prop) {
        return DATA.computeIfAbsent(prop, p -> new Data());
    }

    public static boolean hasSlidingControl(Property prop) {
        return data(prop).hasSlidingControl;
    }

    public static void setHasSlidingControl(Property prop, boolean value) {
        data(prop).hasSlidingControl = value;
    }

    private static final class Data {
        boolean hasSlidingControl;
    }
}
