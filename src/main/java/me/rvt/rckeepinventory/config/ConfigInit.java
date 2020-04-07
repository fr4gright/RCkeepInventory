package me.rvt.rckeepinventory.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigInit {
    private File conf;
    private FileConfiguration config;

    public ConfigInit(Plugin plugin)
    {
        loadConfig(plugin);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void loadConfig(Plugin plugin) {

        if (conf == null) {
            conf = new File(plugin.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(conf);

        if (!config.contains("settings")) {

            init();

            try {
                config.save(conf);
            } catch (IOException var3) {
                System.out.println("[RCkeepInventory] Unable to save config!");
            }
        }
    }
    private void init() {
        List<String> worlds = new ArrayList<>();

        worlds.add("world");

        config.set("settings.worlds", worlds);
        config.set("settings.hotbar.keep", true);
        //config.set("settings.inventory.keep", false);
        //config.set("settings.armor.keep", false);
        //config.set("settings.exp.keep", false);

    }
}
