package fencingparry4.bukkit.PvpParadise;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emerson on 7/7/2016.
 */

public class PvpParadise extends JavaPlugin {
    private static Plugin plugin;
    int chestAmount = (int) getConfig().get("Chest Amount");
    int teleportAmount = (int) getConfig().get("Teleport Amount");
    ArrayList<Location> teleportLocations; //= (List<Location>) getConfig().getList("Teleport Locations");
    ArrayList<Location> RandomTPLocationArray = new ArrayList<Location>();
    ArrayList<String> RandomChestLocationArray = new ArrayList<String>();

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("PvpParadise is Enabled and Running...yay");
        Bukkit.getServer().getPluginManager().registerEvents(new SignListeners(), this);
        //getConfig().options().copyDefaults(true);
        //saveConfig();
        chestAmount = (int) getConfig().get("Chest Amount");
        teleportAmount = (int) getConfig().get("Teleport Amount");
        teleportLocations = (ArrayList<Location>) getConfig().getList("Teleport Locations");
        System.out.println(ChatColor.GOLD + "-------------------------------------------------------------=*=-------------------------------------------------------------");
        System.out.println(teleportLocations);
        System.out.println(ChatColor.AQUA + "-------------------------------------------------------------=*=-------------------------------------------------------------");
        //SignListeners.setTeleportLocations(teleportLocations.get("PvpParadise"));
    }


    @Override
    public void onDisable() {
        System.out.println("PvpParadise is disabled");
        teleportLocations.addAll(RandomTPLocationArray);
        //---------------------------
        //Write array to the config and save
        //---------------------------
        getConfig().set("Teleport Locations", teleportLocations);
        getConfig().set("Chest Coords", RandomChestLocationArray);
        getConfig().set("Chest Amount", chestAmount);
        getConfig().set("Teleport Amount", teleportAmount);
        saveDefaultConfig();
        saveConfig();
        plugin = null;
    }


    //------------------------------------CREATE CONFIG------------------------------

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

/*
    //---------------------------------------COMMANDS ARE HERE----------------------------------------------------------\\
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("Hello") && sender instanceof Player) {

            Player player = (Player) sender;

            player.sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GREEN + "Hello, " + player.getName() + "!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("chestHelp") && sender instanceof Player) {

            Player player = (Player) sender;
            player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
            player.sendMessage(ChatColor.BLUE + "> " + ChatColor.DARK_GREEN + "The chest values are as follows: 1: ALL IRON. 2: IRON GOLD MIX. 3: ALL GOLD. 4: GOLD AND DIAMOND MIX. 5: ALL DIAMOND");
            player.sendMessage(ChatColor.BLUE + "> " + ChatColor.DARK_GREEN + "You can destroy the chest created to turn it. Only the coords are stored in the config not the orientation and it isn't given any special IDS");
            player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("createChest") && sender instanceof Player) {
            Player player = (Player) sender;

            Location pl = player.getLocation();
            World world = player.getWorld();
            Location chestLocation = new Location(world, pl.getX(), pl.getY(), pl.getZ());
            chestLocation.getBlock().setType(Material.CHEST);
            ++chestAmount;
            String ChestLocationToBeInArray = "" + chestLocation;
            RandomChestLocationArray.add(ChestLocationToBeInArray);
            player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "Chest " + ChatColor.AQUA + chestAmount + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + chestLocation + ChatColor.GREEN + ".");
            player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + "You must restart the server for the locations to load");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("RandomTp") && sender instanceof Player) {
            Player player = (Player) sender;
            Location pll = player.getLocation();
            World worldd = player.getWorld();
            Location RandomTPLocation = new Location(worldd, pll.getX(), pll.getY(), pll.getZ());
            RandomTPLocationArray.add(RandomTPLocation);
            teleportAmount++;
            player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "Teleport location " + ChatColor.AQUA + teleportAmount + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + RandomTPLocation + ChatColor.GREEN + ".");
            player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + "You must restart the server for the locations to load");
            return true;
        }
        return false;
    }
    */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(commandLabel.equalsIgnoreCase("pvpparadise")) {

            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GREEN + "Do /help pvpparadise for more information");
                return true;
            }
            else if (args[0].equalsIgnoreCase("chestHelp")) {
                player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
                player.sendMessage(ChatColor.BLUE + "> " + ChatColor.DARK_GREEN + "The chest values are as follows: 1: ALL IRON. 2: IRON GOLD MIX. 3: ALL GOLD. 4: GOLD AND DIAMOND MIX. 5: ALL DIAMOND");
                player.sendMessage(ChatColor.BLUE + "> " + ChatColor.DARK_GREEN + "You can destroy the chest created to turn it. Only the coords are stored in the config not the orientation and it isn't given any special IDS");
                player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
                return true;
            }
            else if (args[0].equalsIgnoreCase("createChest")) {
                Location pl = player.getLocation();
                World world = player.getWorld();
                Location chestLocation = new Location(world, pl.getX(), pl.getY(), pl.getZ());
                chestLocation.getBlock().setType(Material.CHEST);
                ++chestAmount;
                String ChestLocationToBeInArray = "" + chestLocation;
                RandomChestLocationArray.add(ChestLocationToBeInArray);
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "Chest " + ChatColor.AQUA + chestAmount + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + chestLocation + ChatColor.GREEN + ".");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + "You must restart the server for the locations to load");
                return true;
            }
            else if (args[0].equalsIgnoreCase("randomTP")) {
                Location pll = player.getLocation();
                World worldd = player.getWorld();
                Location RandomTPLocation = new Location(worldd, pll.getX(), pll.getY(), pll.getZ());
                RandomTPLocationArray.add(RandomTPLocation);
                teleportAmount++;
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "Teleport location " + ChatColor.AQUA + teleportAmount + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + RandomTPLocation + ChatColor.GREEN + ".");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + "You must restart the server for the locations to load");
                return true;
            }
            else if (args[0].equalsIgnoreCase("hello")) {
                player.sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GREEN + "Hello" + ChatColor.AQUA + player.getName());
                return true;
            }
        }
        return false;
    }
}