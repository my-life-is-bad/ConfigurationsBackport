package my_life_is_bad.configurationsbackport.mixins;

import org.spongepowered.asm.mixin.Mixin;
import my_life_is_bad.configurationsbackport.interfaces.IMixinProperty;


@Mixin(targets = "net.minecraftforge.common.config.Property")
public class MixinProperty implements IMixinProperty {
    
    private boolean hasSlidingControl;

    @Override
    public boolean hasSlidingControl()
    {
        return hasSlidingControl;
    }

    @Override
    public void setHasSlidingControl(boolean b)
    {
        hasSlidingControl=b;
    }
}
