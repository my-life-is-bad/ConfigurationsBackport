package my_life_is_bad.configurationsbackport.mixins;

import java.lang.reflect.Field;


import net.minecraftforge.fml.common.FMLModContainer;
import my_life_is_bad.configurationsbackport.common.config.ConfigManager;
import my_life_is_bad.configurationsbackport.common.config.Config;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;

@Mixin(targets = "net.minecraftforge.fml.common.FMLModContainer")
public class MixinFMLModContainer {

    @Inject(method = "constructMod(Lnet/minecraftforge/fml/common/event/FMLConstructionEvent;)V", at = @At("HEAD"))
    private void onConstructMod(FMLConstructionEvent event, CallbackInfo ci) {
        
         try {
            Loader loader = Loader.instance();
            Field field = Loader.class.getDeclaredField("discoverer");
            field.setAccessible(true);
            Object value = field.get(loader);

            ModDiscoverer discoverer = (ModDiscoverer) field.get(loader);//this.getDiscoverer();
            ASMDataTable asmTable = discoverer.getASMTable();

            ConfigManager.loadData(asmTable);
            ConfigManager.sync(((FMLModContainer)(Object)this).getModId(), Config.Type.INSTANCE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
