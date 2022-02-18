package me.lordmefloun.kek.knockit.Runnables;

import me.lordmefloun.kek.knockit.Objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VoidCheck extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p: Arena.players.keySet()) {
            Arena arena = Arena.players.get(p);
            if (p.getLocation().getY() <= arena.voidY)
            {
                arena.Death(p, null);
            }
        }
    }
}
