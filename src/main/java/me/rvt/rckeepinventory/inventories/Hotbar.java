package me.rvt.rckeepinventory.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Hotbar extends Thread{
    private PlayerDeathEvent e;
    ItemStack[] hotbarItems = new ItemStack[9];

    public Hotbar(PlayerDeathEvent e){
        this.e = e;
    }

    public void run(){
        Player p = e.getEntity().getPlayer();

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
        return e.getEntity().getPlayer().getName();
    }

    public void giveItemsBack(){
        for(int i = 0; i<9; i++)
            e.getEntity().getPlayer().getInventory().setItem(i, hotbarItems[i]);
    }
}
