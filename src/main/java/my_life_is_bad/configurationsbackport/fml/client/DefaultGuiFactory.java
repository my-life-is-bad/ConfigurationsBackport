package my_life_is_bad.configurationsbackport.fml.client;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigElement;
import my_life_is_bad.configurationsbackport.common.config.ConfigManager;
import my_life_is_bad.configurationsbackport.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class DefaultGuiFactory implements IModGuiFactory
{
    protected String modid, title;
    protected Minecraft minecraft;

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return DefaultConfigGui.class;
    }

    @Override
    public void initialize(Minecraft minecraftInstance)
    {
        this.minecraft = minecraftInstance;
    }
    
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement runtimeOptionCategoryElement)
    {
        return null;
    }

    

    public static List<IConfigElement> collectConfigElements(Class<?>[] configClasses)
    {
        List<IConfigElement> toReturn;
        if (configClasses.length == 1)
        {
            toReturn = from(configClasses[0]).getChildElements();
        }
        else
        {
            toReturn = new ArrayList<>();
            for (Class<?> clazz : configClasses)
            {
                toReturn.add(from(clazz));
            }
        }
        toReturn.sort(Comparator.comparing(e -> I18n.format(e.getLanguageKey())));
        return toReturn;
    }


    private static IConfigElement from(Class<?> configClass)
    {
        Config annotation = configClass.getAnnotation(Config.class);
        if (annotation == null)
            throw new RuntimeException(String.format("The class '%s' has no @Config annotation!", configClass.getName()));
        
        Configuration config = ConfigManager.getConfiguration(annotation.modid(), annotation.name());
        if (config == null)
        {
            String error = String.format("The configuration '%s' of mod '%s' isn't loaded with the ConfigManager!", annotation.name(), annotation.modid());
            throw new RuntimeException(error);
        }
        
        String name = Strings.isNullOrEmpty(annotation.name()) ? annotation.modid() : annotation.name();
        String langKey = name;
        Config.LangKey langKeyAnnotation = configClass.getAnnotation(Config.LangKey.class);
        if (langKeyAnnotation != null)
        {
            langKey = langKeyAnnotation.value();
        }
         
        if (annotation.category().isEmpty())
        {
            List<IConfigElement> elements = Lists.newArrayList();
            Set<String> catNames = config.getCategoryNames();
            for (String catName : catNames)
            {
                if (catName.isEmpty())
                    continue;
                ConfigCategory category = config.getCategory(catName);
                if (category.isChild())
                    continue;
                DummyCategoryElement element = new DummyCategoryElement(category.getName(), category.getLanguagekey(), new ConfigElement(category).getChildElements());
                element.setRequiresMcRestart(category.requiresMcRestart());
                element.setRequiresWorldRestart(category.requiresWorldRestart());
                elements.add(element);
            }
                
            return new DummyCategoryElement(name, langKey, elements);
        }
        else
        {
            ConfigCategory category = config.getCategory(annotation.category());
            DummyCategoryElement element = new DummyCategoryElement(name, langKey, new ConfigElement(category).getChildElements());   
            element.setRequiresMcRestart(category.requiresMcRestart());
            element.setRequiresWorldRestart(category.requiresWorldRestart());
            return element;
        } 
    }
}
