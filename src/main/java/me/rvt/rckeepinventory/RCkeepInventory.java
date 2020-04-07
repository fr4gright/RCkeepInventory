package me.rvt.rckeepinventory;

import me.rvt.rckeepinventory.config.ConfigInit;
import me.rvt.rckeepinventory.inventories.Hotbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class RCkeepInventory extends JavaPlugin implements Listener {
    FileConfiguration config;
    private List<Object> deathPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        config = new ConfigInit(this).getConfig();
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent e){
        if(config.getStringList("settings.worlds").contains(
                e.getEntity().getWorld().getName()))
        {
            if(config.getBoolean("settings.hotbar.keep")){
                deathPlayers.add(new Hotbar(e));
            }
        }
    }

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        for(Object o: deathPlayers){
            if(o instanceof Hotbar && ((Hotbar) o).getPlayerName().equals(p.getName())){
                ((Hotbar) o).giveItemsBack();
                deathPlayers.remove(o);
                return;
            }
        }
    }
}
