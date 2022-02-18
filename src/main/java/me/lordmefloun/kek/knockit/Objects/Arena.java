package me.lordmefloun.kek.knockit.Objects;

import me.lordmefloun.kek.knockit.KnockIt;
import me.lordmefloun.kek.knockit.Utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class Arena {

    public static HashSet<Arena> arenas;
    public static HashMap<Player, Arena> players;
    public int voidY;
    public String Name;
    public static HashSet<Location> spawnpoints;

    public Arena(String name)
    {
        this.Name = name;
        voidY = 89;

        arenas.add(this);
    }


    public void Join(Player p)
    {
        if (!IsJoined(p)) players.put(p, this);
    }

    public void Leave(Player p)
    {
        if (IsJoined(p)) players.remove(p, this);
    }


    public void BroadcastMessage(String text)
    {
        for (Player p: players.keySet()) {
            Message.SendMessage(p, text, true);
        }
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
