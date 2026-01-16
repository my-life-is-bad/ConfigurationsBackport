package my_life_is_bad.configurationsbackport.mixins;

import com.google.common.collect.BiMap;
import my_life_is_bad.configurationsbackport.fml.client.DefaultGuiFactory;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.ModContainer;
import my_life_is_bad.configurationsbackport.common.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraftforge.fml.client.FMLClientHandler")
public class MixinFMLClientHandler {

    @Shadow private BiMap<ModContainer, IModGuiFactory> guiFactories;

    @Redirect(
        method = "finishMinecraftLoading",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/fml/common/ModContainer;getGuiClassName()Ljava/lang/String;"
        )
    )
    private String injectGuiClassName(ModContainer mc) {
        String original = mc.getGuiClassName();
        if (original == null || original.isEmpty()) {
            if (ConfigManager.hasConfigForMod(mc.getModId())) {
                guiFactories.put(mc, new DefaultGuiFactory());
            }
        }
        return original;
    }

}
