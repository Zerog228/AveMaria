package me.zink.avemaria;

import io.github.zerog228.usefless.UseflessLibrary;
import io.github.zerog228.usefless.structure.CStructure;
import io.github.zerog228.usefless.util.CFileUtils;
import me.zink.avemaria.item.CustomItemRegistries;
import me.zink.avemaria.structure.MegaCrafterLogic;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public final class AveMaria extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        UseflessLibrary.setPlugin(plugin);

        //Registering items
        CustomItemRegistries.init();

        //This line of code automatically copies all files from "resources/structures/" to "server/plugins/your_plugin/structures/"
        CFileUtils.copyResources(plugin, "structures", "structures", 1, false);
        //This line of code automatically initializes all structures from "server/plugins/your_plugin/structures/"
        CStructure.initAllFromPath(Path.of(plugin.getDataPath().toString(), "structures"), true, true);

        //Registering our custom event listener from MegaCrafter
        plugin.getServer().getPluginManager().registerEvents(new MegaCrafterLogic(), plugin);
    }

    @Override
    public void onDisable() {}
}
