package my_life_is_bad.configurationsbackport.mixins;

import my_life_is_bad.configurationsbackport.interfaces.IMixinProperty;
import my_life_is_bad.configurationsbackport.interfaces.IMixinConfigElement;
import net.minecraftforge.common.config.Property;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(targets = "net.minecraftforge.common.config.ConfigElement")
public class MixinConfigElement implements IMixinConfigElement {

    @Shadow
    private Property prop;

    @Override
    public boolean hasSlidingControl()
    {
        return ((IMixinProperty)prop).hasSlidingControl();
    }
}
