package fencingparry4.bukkit.PvpParadise;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emerson on 7/7/2016.
 */

public class PvpParadise extends JavaPlugin implements Listener {
    private static int chestAmount = 0;
    private static int chestLocation =0;


    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new SignListeners(this), this);
        this.saveDefaultConfig();
    }
/*
    private void createFiles() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File config = new File(getDataFolder(), "config.yml");
            if (!config.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
*/

    @Override
    public void onDisable() {

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
        if(commandLabel.equalsIgnoreCase("pp") && ((Player)sender).isOp()) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(this.getConfig().getString("message"));
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.YELLOW + "--------------------------------------");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN+ "Welcome to PvpParadise");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN  + "To create a random tp location do " + ChatColor.AQUA + "/pp createTP" + ChatColor.GRAY + " (or /pp CT)");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "To create a chest do " + ChatColor.AQUA + "/pp createChest" + ChatColor.GRAY + " (or /pp CC)");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + "To view help on chests and values do " + ChatColor.AQUA + "/pp chestHelp" + ChatColor.GRAY + " (or /pp CH)");
                return true;
            }
            else if (args[0].equalsIgnoreCase("chestHelp") || args[0].equalsIgnoreCase("CH")) {
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_GREEN + " The chest values are as follows: 1: ALL IRON. 2: IRON GOLD MIX. 3: ALL GOLD. 4: GOLD AND DIAMOND MIX. 5: ALL DIAMOND");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_GREEN + " You can destroy the chest created to turn it. Only the coords are stored in the config not the orientation and it isn't given any special IDS");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GRAY + "----------------" + ChatColor.BLUE + "<");
                return true;
            }
            else if (args[0].equalsIgnoreCase("createChest") || args[0].equalsIgnoreCase("CC")) {
                String location = Integer.toString((int)player.getLocation().getX()) + "," + Integer.toString((int)player.getLocation().getY()) + "," + Integer.toString((int)player.getLocation().getZ());
                List list = this.getConfig().getList("Chest Locations and Value");
                System.out.println(list);
                int value;
                try {
                    value = Integer.parseInt(args[1]);
                    if (value > 0 && value < 6) {
                        list.add(location + "," + value);
                        if (list.get(0) == null) {
                            list.remove(0);
                        }
                        this.getConfig().set("Chest Locations and Value", list);
                        this.getConfig().set("Chest Amount", list.size());
                        this.saveConfig();
                        this.reloadConfig();
                        player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + ChatColor.BOLD + "Place a chest where you were at the desired orientation");
                        player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + " Chest " + ChatColor.AQUA + this.getConfig().get("Chest Amount") + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + location + ChatColor.GREEN + ".");
                        player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + " You must restart the server for the locations to load");
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + ChatColor.BOLD + "ERROR: " +  ChatColor.RESET + ChatColor.GREEN +"You must use a number less than or equal to 5 and greater than or equal to 1");
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("createTP") || args[0].equalsIgnoreCase("CT")) {
                String location = Double.toString(player.getLocation().getX()) + "," + Double.toString(player.getLocation().getY()) + "," + Double.toString(player.getLocation().getZ()) ;
                List list = this.getConfig().getList("Teleport Locations");
                list.add(location);
                if (list.get(0) == null) {
                    list.remove(0);
                }
                this.getConfig().set("Teleport Locations", list);
                int teleportAmount = list.size();
                this.getConfig().set("Teleport Amount", teleportAmount);
                this.saveConfig();
                this.reloadConfig();
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.GREEN + " Teleport location " + ChatColor.AQUA + this.getConfig().get("Teleport Amount") + ChatColor.GREEN + " has been created at " + ChatColor.AQUA + location + ChatColor.GREEN + ".");
                player.sendMessage(ChatColor.BLUE + "PvpParadise> " + ChatColor.DARK_RED + " You must restart the server for the locations to load");
                return true;
            }
            else if (args[0].equalsIgnoreCase("hello")) {
                player.sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GREEN + " Hello " + ChatColor.AQUA + player.getName() + ChatColor.GREEN + "!");
                return true;
            }
        }
        else {
            return true;
        }
        return false;
    }
}