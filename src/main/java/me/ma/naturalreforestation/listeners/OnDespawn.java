package me.ma.naturalreforestation.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

public class OnDespawn implements Listener {
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        if (!Tag.SAPLINGS.isTagged(event.getEntity().getItemStack().getType())) {
            return;
        }
        Block highest = event.getLocation().getBlock();
        Block below = event.getLocation().subtract(0, 1, 0).getBlock();
        if (!Tag.DIRT.isTagged(below.getType()) && below.getType() != Material.GRASS_BLOCK){
            return;
        }
        event.setCancelled(true);
        event.getEntity().remove();
        highest.setType(event.getEntity().getItemStack().getType());
    }

}
