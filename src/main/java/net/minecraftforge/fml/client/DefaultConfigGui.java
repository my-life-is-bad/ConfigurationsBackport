package net.minecraftforge.fml.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigManager;

import java.util.List;

public class DefaultConfigGui extends GuiConfig {

    public DefaultConfigGui(GuiScreen parent) {
        super(
            parent,
            DefaultGuiFactory.collectConfigElements(
                ConfigManager.getModConfigClasses(DefaultGuiFactory.lastModid)
            ),
            DefaultGuiFactory.lastModid,
            false,
            false,
            DefaultGuiFactory.lastTitle
        );
    }
}
