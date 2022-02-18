package me.lordmefloun.kek.knockit.Utils;

import me.lordmefloun.kek.knockit.KnockIt;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {

    public static void SendMessage(Player p, String text)
    {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', text ));
    }


    public static void SendMessage(Player p, String text, boolean prefix)
    {
        if (prefix) p.sendMessage(ChatColor.translateAlternateColorCodes('&', KnockIt.prefix + "&r " + text ));
    }
}
