package my_life_is_bad.configurationsbackport.fml.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import my_life_is_bad.configurationsbackport.common.config.ConfigManager;

import my_life_is_bad.configurationsbackport.mixins.MixinGuiModList;

public class DefaultConfigGui extends GuiConfig {

    public DefaultConfigGui(GuiScreen parent) {
        super(
            parent,
            DefaultGuiFactory.collectConfigElements(
                ConfigManager.getModConfigClasses(((MixinGuiModList)(Object) parent).getSelectedMod().getModId())
            ),
            ((MixinGuiModList)(Object) parent).getSelectedMod().getModId(),
            false,
            false,
            ((MixinGuiModList)(Object) parent).getSelectedMod().getName()
        );
    }
}
