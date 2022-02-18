package me.lordmefloun.kek.knockit.Listeners;

import me.lordmefloun.kek.knockit.Objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Hunger implements Listener {

    @EventHandler
    public void OnHunger(FoodLevelChangeEvent e)
    {
        if (e.getEntity() instanceof Player)
        {
            Player p = (Player) e.getEntity();
            if (Arena.IsJoined(p)) e.setCancelled(true);
        }
    }
}
