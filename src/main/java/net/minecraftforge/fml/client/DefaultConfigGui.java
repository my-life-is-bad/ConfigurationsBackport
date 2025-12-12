package net.minecraftforge.fml.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigManager;

import my_life_is_bad.configurationsbackport.mixins.MixinGuiModList;

import java.util.List;

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
