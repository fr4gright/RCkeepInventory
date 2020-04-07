package me.rvt.rckeepinventory.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Hotbar {
    private Player p;
    ItemStack[] hotbarItems = new ItemStack[9];

    public Hotbar(PlayerDeathEvent e){
        p = e.getEntity();

        for(int i = 0; i<9; i++){
            ItemStack currentItem = new ItemStack(Material.AIR, 1);

            if(p.getInventory().getItem(i) != null)
                currentItem = p.getInventory().getItem(i);

            hotbarItems[i] = currentItem;

            if(currentItem.getType() != Material.AIR)
                e.getDrops().remove(currentItem);
        }
    }

    public String getPlayerName(){
        return p.getName();
    }

    public void giveItemsBack(){
        for(int i = 0; i<9; i++)
            p.getInventory().setItem(i, hotbarItems[i]);
    }
}
