package me.lordmefloun.kek.knockit;

import me.lordmefloun.kek.knockit.Commands.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class KnockIt extends JavaPlugin {


    public static final String prefix = "&A&lKnockit |";

    @Override
    public void onEnable() {
        this.getCommand("knockit").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
    }
}
