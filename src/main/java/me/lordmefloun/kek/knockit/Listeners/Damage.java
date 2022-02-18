package me.lordmefloun.kek.knockit.Listeners;

import me.lordmefloun.kek.knockit.KnockIt;
import me.lordmefloun.kek.knockit.Objects.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

    @EventHandler
    public void OnDamage(EntityDamageEvent e)
    {
        if (e.getEntity().getType().equals(EntityType.PLAYER))
        {
            Player p = (Player) e.getEntity();
            if (Arena.IsJoined(p))
            {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) e.setCancelled(true);
                else
                {
                    Bukkit.getScheduler().runTaskLater(KnockIt.plugin, ()-> {
                        p.setHealth(20);
                    }, 1);
                }
            }
        }
    }

}
