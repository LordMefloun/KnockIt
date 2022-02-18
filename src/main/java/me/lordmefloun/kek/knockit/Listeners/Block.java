package me.lordmefloun.kek.knockit.Listeners;

import me.lordmefloun.kek.knockit.KnockIt;
import me.lordmefloun.kek.knockit.Objects.Arena;
import me.lordmefloun.kek.knockit.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Block implements Listener {


    @EventHandler
    public void OnPlaceBlock(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();
        if (e.getBlockPlaced().getType().equals(Material.SANDSTONE)) {
            if (Arena.IsJoined(p)) {
                Bukkit.getScheduler().runTaskLater(KnockIt.plugin, () ->
                {
                    e.getBlock().setType(Material.REDSTONE_BLOCK);
                }, 100);

                Bukkit.getScheduler().runTaskLater(KnockIt.plugin, () ->
                {
                    e.getBlock().setType(Material.AIR);
                    p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 1));
                }, 200);
            }
        } else {
            e.setCancelled(true);
            Message.SendMessage(p, "&cYou can't place block here", true);
        }
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();
        if (Arena.IsJoined(p))
        {
            Message.SendMessage(p, "&cYou can't break block here", true);
            e.setCancelled(true);
        }
    }

}
