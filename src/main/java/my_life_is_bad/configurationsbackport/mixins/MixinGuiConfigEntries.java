package my_life_is_bad.configurationsbackport.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Property;
import my_life_is_bad.configurationsbackport.interfaces.IMixinConfigElement;

import org.spongepowered.asm.mixin.Final;

@Mixin(targets = "net.minecraftforge.fml.client.config.GuiConfigEntries")
public class MixinGuiConfigEntries {

    //     @Redirect(
    //     method = "<init>(Lnet/minecraftforge/fml/client/config/GuiConfig;Lnet/minecraft/client/Minecraft;)V",
    //     at = @At(
    //         value = "NEW",
    //         target = "Lnet/minecraftforge/fml/client/config/GuiConfigEntries$IntegerEntry;<init>(Lnet/minecraftforge/fml/client/config/GuiConfig;Lnet/minecraftforge/fml/client/config/GuiConfigEntries;Lnet/minecraftforge/fml/client/config/IConfigElement;)V",
    //         remap = false
    //     )
    // )
    // private GuiConfigEntries.IConfigEntry redirectIntegerEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
    //     IMixinProperty ext = (IMixinProperty)(Object) configElement;

    //     if (ext.hasSlidingControl()) {
    //         return new GuiConfigEntries.NumberSliderEntry(owningScreen, (GuiConfigEntries)(Object)this, configElement);
    //     } else {
    //         return new GuiConfigEntries.IntegerEntry(owningScreen, (GuiConfigEntries)(Object)this, configElement);
    //     }
    // }


    // @Redirect(
    //     method = "<init>(Lnet/minecraftforge/fml/client/config/GuiConfig;Lnet/minecraft/client/Minecraft;)V",
    //     at = @At(
    //         value = "NEW",
    //         target = "Lnet/minecraftforge/fml/client/config/GuiConfigEntries$DoubleEntry;<init>(Lnet/minecraftforge/fml/client/config/GuiConfig;Lnet/minecraftforge/fml/client/config/GuiConfigEntries;Lnet/minecraftforge/fml/client/config/IConfigElement;)V",
    //         remap = false
    //     )
    // )
    // private GuiConfigEntries.IConfigEntry redirectDoubleEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
    //     IMixinProperty ext = (IMixinProperty)(Object) configElement;

    //     if (ext.hasSlidingControl()) {
    //         return new GuiConfigEntries.NumberSliderEntry(owningScreen, (GuiConfigEntries)(Object)this, configElement);
    //     } else {
    //         return new GuiConfigEntries.DoubleEntry(owningScreen, (GuiConfigEntries)(Object)this, configElement);
    //     }
    // }


    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 3))
    private boolean redirectIntegerAdd(List<Object> list, Object entry) {
        GuiConfigEntries self = (GuiConfigEntries)(Object)this;
        GuiConfig owningScreen = self.owningScreen;

        IConfigElement element = ((GuiConfigEntries.IConfigEntry) entry).getConfigElement();
        IMixinConfigElement ext = (IMixinConfigElement)(Object) element;

        if (ext.hasSlidingControl()) {
            entry = new GuiConfigEntries.NumberSliderEntry(owningScreen, self, element);
        }

        return list.add(entry);
    }


    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4))
    private boolean redirectDoubleAdd(List<Object> list, Object entry) {
        GuiConfigEntries self = (GuiConfigEntries)(Object)this;
        GuiConfig owningScreen = self.owningScreen;

        IConfigElement element = ((GuiConfigEntries.IConfigEntry) entry).getConfigElement();
        IMixinConfigElement ext = (IMixinConfigElement)(Object) element;

        if (ext.hasSlidingControl()) {
            entry = new GuiConfigEntries.NumberSliderEntry(owningScreen, self, element);
        }

        return list.add(entry);
    }
}
