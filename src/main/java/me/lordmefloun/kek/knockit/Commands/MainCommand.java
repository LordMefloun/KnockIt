package me.lordmefloun.kek.knockit.Commands;

import me.lordmefloun.kek.knockit.Objects.Arena;
import me.lordmefloun.kek.knockit.Utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player))
        {
            System.out.println("This command can be accessed only as a player");
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 1)
        {
            switch (args[0].toLowerCase())
            {
                case "leave":
                    if (Arena.IsJoined(p))
                    {
                        Message.SendMessage(p, "&aSuccessfully left arena &e" + Arena.GetArenaByPlayer(p).Name, true);
                        Arena.GetArenaByPlayer(p).Leave(p);
                    }
                    else Message.SendMessage(p, "&cYou are not joined in any arena", true );
                    break;
                default:
                    Message.SendMessage(p, "&cUnknown usage", true);
                    break;
            }
        }
        else if (args.length == 2)
        {
            switch (args[0].toLowerCase())
            {
                case "join":
                    if (Arena.ArenaExists(args[1]))
                    {
                        if (!Arena.IsJoined(p))
                        {
                            Message.SendMessage(p, "&aSuccessfully joined to arena &e" + args[1], true);
                            Arena.GetArenaByName(args[1]).Join(p);
                        }
                        else Message.SendMessage(p, "&cAlready joined to some arena", true);
                    } else Message.SendMessage(p, "&cThis arena doesn't exist", true);
                    break;
                default:
                    Message.SendMessage(p, "&cUnknown usage", true);
                    break;
            }
        }
        else
        {
            Message.SendMessage(p, "&cUnknown usage", true);
        }
        return false;
    }
}
