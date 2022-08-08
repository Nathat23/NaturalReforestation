package me.ma.naturalreforestation.listeners;

import me.ma.naturalreforestation.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;


public class OnDespawn implements Listener {
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        if (Saplings.isSapling(event.getEntity().getItemStack().getType())) {
            Location location = event.getEntity().getLocation();
            World world = location.getWorld();

            boolean freeSpace = true;
            for (int i = location.getBlockY() + 1; i < 328; i++) {
                if (!(Air.isAir(world.getBlockAt(location.getBlockX(), i, location.getBlockZ()).getType()))) {
                    freeSpace = false;
                    break;
                }
            }

            if (freeSpace &&
                    (Dirt.isDirt(world.getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ()).getType()))) {
                event.setCancelled(true);
                event.getEntity().remove();
                if (world.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ()).getType() == Material.FARMLAND) {
                    world.getBlockAt(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ()).setType(event.getEntity().getItemStack().getType());
                } else {
                    location.getBlock().setType(event.getEntity().getItemStack().getType());
                }
            }
        }
    }

   private enum Saplings {
        BIRCH(Material.BIRCH_SAPLING),
        DARK_OAK(Material.DARK_OAK_SAPLING),
        JUNGLE(Material.JUNGLE_SAPLING),
        ACACIA(Material.ACACIA_SAPLING),
        OAK(Material.OAK_SAPLING),
        BAMBOO(Material.BAMBOO),
        SPRUCE(Material.SPRUCE_SAPLING);
        private final Material material;
        Saplings(Material material) {
            this.material = material;
        }
        public Material getMaterial() {
            return material;
        }

        public static boolean isSapling(Material material) {
            for (Saplings sapling : Saplings.values()) {
                if (sapling.getMaterial().equals(material)) {
                    return true;
                }
            }
            return false;
        }
   }

   private enum Dirt {
        DIRT(Material.DIRT),
        GRASS_BLOCK(Material.GRASS_BLOCK),
        COARSE_DIRT(Material.COARSE_DIRT),
        PODZOL(Material.PODZOL),
        FARMLAND(Material.FARMLAND),
        ROOTED_DIRT(Material.ROOTED_DIRT);
        private final Material material;
        Dirt(Material material) {
            this.material = material;
        }
        public Material getMaterial() {
            return material;
        }
        // check if provided material is a dirt
        public static boolean isDirt(Material material) {
            for (Dirt dirt : Dirt.values()) {
                if (dirt.getMaterial().equals(material)) {
                    return true;
                }
            }
            return false;
        }
   }

   private enum Air {
        AIR(Material.AIR),
        VOID_AIR(Material.VOID_AIR),
        CAVE_AIR(Material.CAVE_AIR);
        private final Material material;
        Air(Material material) {
            this.material = material;
        }
        public Material getMaterial() {
            return material;
        }
        // check if provided material is air
        public static boolean isAir(Material material) {
            for (Air air : Air.values()) {
                if (air.getMaterial().equals(material)) {
                    return true;
                }
            }
            return false;
        }
   }
}
