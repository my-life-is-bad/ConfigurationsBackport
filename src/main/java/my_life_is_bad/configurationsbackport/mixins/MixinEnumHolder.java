package my_life_is_bad.configurationsbackport.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraftforge.fml.common.discovery.asm.ModAnnotation$EnumHolder")
public interface MixinEnumHolder {

    @Accessor("desc")
    String getDesc();

    @Accessor("value")
    String getValue();
}
