package me.lordmefloun.kek.knockit.Objects;

import me.lordmefloun.kek.knockit.KnockIt;
import me.lordmefloun.kek.knockit.Utils.Message;
import me.lordmefloun.kek.knockit.Utils.Random;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

public class Arena {

    public static HashSet<Arena> arenas = new HashSet<>();
    public static HashMap<Player, Arena> players = new HashMap<>();
    public static Location lobby;
    public int voidY;
    public String Name;
    public HashSet<Location> spawnpoints;
    public Location spectator;

    public Arena(String name, HashSet<Location> spawnpoints, Location spectator)
    {
        this.Name = name;
        voidY = 89;
        this.spawnpoints = spawnpoints;
        this.spectator = spectator;

        arenas.add(this);
    }

    public void Join(Player p)
    {
        if (!IsJoined(p))
        {
            players.put(p, this);
            p.teleport(GetRandomLocation());
            p.getInventory().clear();
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 20));
        }
    }

    public void Leave(Player p)
    {
        if (IsJoined(p))
        {
            players.remove(p, this);
            p.teleport(lobby);
            p.getInventory().clear();
        }
    }

    public void BroadcastMessage(String text)
    {
        for (Player p: players.keySet()) {
            Message.SendMessage(p, text, true);
        }
    }
    public void Death(Player victim, Player attacker)
    {
        victim.teleport(spectator);
        victim.setGameMode(GameMode.SPECTATOR);
        BroadcastMessage("&6&n" + victim.getName() + "&a was killed by &6&n" + (attacker == null ? "Void" : attacker.getName()));
        for (int i = 0; i < 11; i++) {
            if (i != 10)
            {
                Bukkit.getScheduler().runTaskLater(KnockIt.plugin, () ->
                {
                    victim.sendTitle("Respawn", "In 10 seconds");
                }, 10 * i);
            }
        }
        Bukkit.getScheduler().runTaskLater(KnockIt.plugin, () ->
        {
            victim.teleport(GetRandomLocation());
            victim.setGameMode(GameMode.SURVIVAL);
        }, 10 * 10);
    }

    private Location GetRandomLocation()
    {
        return  spawnpoints.toArray(new Location[spawnpoints.size()])[Random.getRandomNumber(0, spawnpoints.size())];
    }

    public static Arena GetArenaByPlayer(Player p)
    {
        for (Player player : players.keySet()) {
            Arena arena = players.get(player);
            if (player.equals(p)) return arena;
        }
        return null;
    }

    public static Arena GetArenaByName(String name)
    {
        for (Arena a : arenas)
        {
            if (a.Name.equals(name)) return a;
        }
        return null;
    }

    public static boolean IsJoined(Player player)
    {
        for (Player p : players.keySet()) {
            if (player.equals(p)) return true;
        }
        return false;
    }

    public static boolean ArenaExists(String name)
    {
        for (Arena a: arenas) {
            if (a.Name.equals(name)) return true;
        }
        return false;
    }


}
