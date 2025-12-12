package my_life_is_bad.configurationsbackport.mixins;

import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraftforge.fml.client.GuiModList")
public interface MixinGuiModList {

    @Accessor("selectedMod")
    ModContainer getSelectedMod();

}
