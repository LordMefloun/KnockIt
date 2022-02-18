package me.lordmefloun.kek.knockit;

import me.lordmefloun.kek.knockit.Commands.MainCommand;
import me.lordmefloun.kek.knockit.Listeners.Block;
import me.lordmefloun.kek.knockit.Listeners.Damage;
import me.lordmefloun.kek.knockit.Listeners.Hunger;
import me.lordmefloun.kek.knockit.Objects.Arena;
import me.lordmefloun.kek.knockit.Runnables.VoidCheck;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public final class KnockIt extends JavaPlugin {


    public static final String prefix = "&6&lKnockit &e|&r";

    public static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        Arena.lobby = Bukkit.getServer().getWorld("world").getBlockAt(-20, 94, 211).getLocation();
        HashSet<Location> spawnpoints = new HashSet<Location>();

        spawnpoints.add(Bukkit.getServer().getWorld("world").getBlockAt(-19, 94, 203).getLocation());
        spawnpoints.add(Bukkit.getServer().getWorld("world").getBlockAt(-1, 94, 203).getLocation());
        spawnpoints.add(Bukkit.getServer().getWorld("world").getBlockAt(-10, 94, 212).getLocation());
        spawnpoints.add(Bukkit.getServer().getWorld("world").getBlockAt(-10, 94, 194).getLocation());

        new Arena("Wool", spawnpoints,
                Bukkit.getServer().getWorld("world").getBlockAt(-10, 104, 203).getLocation());

        this.getCommand("knockit").setExecutor(new MainCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new Block(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Damage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Hunger(), this);
        new VoidCheck().runTaskTimer(this, 0, 10);

    }
}
