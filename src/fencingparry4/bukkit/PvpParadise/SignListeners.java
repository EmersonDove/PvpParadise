package fencingparry4.bukkit.PvpParadise;

import net.minecraft.server.v1_12_R1.INamable;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Emerson on 7/23/2016.
 */

public class SignListeners implements Listener {
    Plugin plugin;
    String[][] chestData;
    //    public SignListeners(PvpParadise instance) {
//        plugin = instance;
//    }
    public SignListeners(PvpParadise main) {
        plugin = main;
        chestData = new String[1][1+(2*(plugin.getConfig().getList("Chest Locations and Value")).size())];
        for (int i = 0; i<chestData.length;i++) {
            for (int j = 0; j<chestData[0].length; j++) {
                chestData[i][j] = "";
            }
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        System.out.println(e.getEntity().getPlayer().getDisplayName());
        e.getEntity().getPlayer().getWorld().dropItemNaturally(e.getEntity().getPlayer().getLocation(), new ItemStack(Material.GOLD_INGOT, 3));
    }
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getLine(1).equalsIgnoreCase("[teleport]") && e.getLine(0).equalsIgnoreCase("[PvpParadise]")) {
            e.setLine(0, "-=*=-");
            e.setLine(1, "§4RANDOM TELEPORT");
            e.setLine(2, "-=*=-");
            e.getPlayer().sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GOLD + " Random Teleport Sign Created.");
            e.setLine(3, "§3Right Click");
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent d) {
        EquipmentSlot g = d.getHand();
        PlayerInteractEvent e = d;
        try {
            if (g.equals(EquipmentSlot.HAND)) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    try {
                        if (e.getClickedBlock().getType() == Material.PISTON_BASE) {
                            int blockCoordsX;
                            int blockCoordsZ;
                            if (e.getClickedBlock().getX() <= 0) {
                                blockCoordsX = e.getClickedBlock().getX() + 1;
                            } else {
                                blockCoordsX = e.getClickedBlock().getX();
                            }
                            if (e.getClickedBlock().getZ() <= 0) {
                                blockCoordsZ = e.getClickedBlock().getZ() + 1;
                            } else {
                                blockCoordsZ = e.getClickedBlock().getZ();
                            }
                            String blockCoords = blockCoordsX + "," + e.getClickedBlock().getY() + "," + blockCoordsZ;
                            List locations = plugin.getConfig().getList("Chest Locations and Value");
                            for (int i = 0; i < locations.size(); i++) {
                                if (locations.get(0) == null) {
                                    break;
                                }
                                //System.out.println("processing coords " + i);
                                String location = locations.get(i).toString().substring(0, locations.get(i).toString().length() - 2);
                                //System.out.println("Clicked " + blockCoords + "ChestCoords" + location);
                                if (location.equals(blockCoords) && checkAvailiablility(e.getPlayer(), location)) {
                                    Random random = new Random();
                                    int teir = Integer.parseInt(locations.get(i).toString().substring(locations.get(i).toString().length() - 1, locations.get(i).toString().length()));
                                    Inventory inv;
                                    ItemStack[] items = new ItemStack[18];
                                    int stacks;
                                    int switcher;
                                    switch (teir) {
                                        case 1:
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "Tier " + "I");
                                            //e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Tier " + "I");
                                            stacks = random.nextInt(5) + 3;
                                            for (int j = 0; j < stacks; j++) {
                                                items[random.nextInt(18)] = new ItemStack(Material.IRON_INGOT, random.nextInt(3));
                                            }
                                            inv.setContents(items);
                                            break;
                                        case 2:
                                            //e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Tier " + "II");
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "Tier " + "II");
                                            stacks = random.nextInt(5) + 3;
                                            for (int j = 0; j < stacks; j++) {
                                                switcher = random.nextInt(2);
                                                if (switcher == 1) {
                                                    items[random.nextInt(18)] = new ItemStack(Material.GOLD_INGOT, random.nextInt(3));
                                                } else {
                                                    items[random.nextInt(18)] = new ItemStack(Material.IRON_INGOT, random.nextInt(3));
                                                }
                                            }
                                            inv.setContents(items);
                                            break;
                                        case 3:
                                            //e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.YELLOW + "Tier " + "III");
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "Tier " + "III");
                                            stacks = random.nextInt(5) + 3;
                                            for (int j = 0; j < stacks; j++) {
                                                items[random.nextInt(18)] = new ItemStack(Material.GOLD_INGOT, random.nextInt(3));
                                            }
                                            inv.setContents(items);
                                            break;
                                        case 4:
                                            //e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.RED + "Tier " + "IV");
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "Tier " + "IV");
                                            stacks = random.nextInt(5) + 3;
                                            for (int j = 0; j < stacks; j++) {
                                                switcher = random.nextInt(2);
                                                if (switcher == 1) {
                                                    items[random.nextInt(18)] = new ItemStack(Material.GOLD_INGOT, random.nextInt(3));
                                                } else {
                                                    items[random.nextInt(18)] = new ItemStack(Material.DIAMOND, random.nextInt(3));
                                                }
                                            }
                                            inv.setContents(items);
                                            break;
                                        case 5:
                                            //e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.RED + ChatColor.BOLD + "Tier " + "V");
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "Tier " + "V");
                                            stacks = random.nextInt(5) + 3;
                                            for (int j = 0; j < stacks; j++) {
                                                items[random.nextInt(18)] = new ItemStack(Material.DIAMOND, random.nextInt(3));
                                            }
                                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 1);
                                            inv.setContents(items);
                                            break;
                                        default:
                                            inv = Bukkit.createInventory(e.getPlayer(), 18, "If you're seeing this something terrible happened");
                                    }
                                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 5, 1);
                                    e.getPlayer().openInventory(inv);
                                    break;
                                }
                            }
                        }
                    } catch (Exception f) {
                        e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.YELLOW + " Something changed, please restart or reload the server before using a chest to reset the plugin.");
                    }
                }
            }
        }
        catch (Exception h) {

        }
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(1).equalsIgnoreCase("§4RANDOM TELEPORT")) {
                List locations = plugin.getConfig().getList("Teleport Locations");
                if (locations.get(0) != null) {
                    e.getPlayer().setFoodLevel(20);
                    e.getPlayer().setMaxHealth(30);
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 3));
                    e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.YELLOW + "Processing teleport...");
                    Random random = new Random();
                    String location = (String) locations.get(random.nextInt(locations.size()));
                    Double x = Double.parseDouble(location.substring(0, location.indexOf(",")));
                    location = location.substring(location.indexOf(",") + 1);
                    Double y = Double.parseDouble(location.substring(0, location.indexOf(",")));
                    location = location.substring(location.indexOf(",") + 1);
                    Double z = Double.parseDouble(location);
                    e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z));
                    e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Teleported!");
                }
                else {
                    for (int i = 0; i<locations.size(); i++) {
                        System.out.print("This is the list of locations, look for problems: " + locations.get(i) + " ");
                    }
                    e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.YELLOW + " No teleport locations found...");
                }
                /*
                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getX()));
                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getY()));
                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getZ()));


                ArrayList<String> TPArray = new ArrayList<String>();
                String TeleportLocations = "" + plugin.getConfig().get("Teleport Locations").toString();
                e.getPlayer().sendMessage(ChatColor.GOLD + TeleportLocations);
                TPArray.add(TeleportLocations);
                String TeleportAmount = "" +  plugin.getConfig().get("Teleport Amount");
                int TeleportAmountInt = Integer.parseInt(TeleportAmount);
                int randomNum = (int) (Math.random() * TeleportAmountInt);
                e.getPlayer().sendMessage(ChatColor.BLUE + ">" + ChatColor.GOLD + "Random number: " + ChatColor.AQUA + randomNum + ChatColor.GOLD + ". Location selected: " + ChatColor.AQUA + "Ope you dont have that yet.");
                */

            } else if (s.getLine(1).equalsIgnoreCase("God Sign") && s.getLine(0).equalsIgnoreCase("[PvpParadise]")) {
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setMaxHealth(1000);
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GOLD + "GOD MODE ACTIVATED");
                e.getPlayer().setHealth(1000);
            } else if (s.getLine(1).equalsIgnoreCase("No God") && s.getLine(0).equalsIgnoreCase("[PvpParadise]")) {
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setMaxHealth(20);
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GOLD + "GOD MODE DEACTIVATED");
                e.getPlayer().setHealth(20);
            }
        }
    }
    //fencingparry4,"10,2,65","52342124","25,2,5","21343444"
    private boolean checkAvailiablility(Player p, String location) {
        //printArray(chestData);
        long epoch = System.currentTimeMillis()/1000;
        for (int i = 0; i < chestData.length; i++) {
            if (chestData[i][0].equals(p.getDisplayName())) {
                String[] data = chestData[i];
                for (int j = 0; j<data.length; j++) {
                    if (data[j].equals(location)) {
                        int waitTime = Integer.parseInt(plugin.getConfig().get("Chest Wait Time").toString());
                        if (epoch - Long.parseLong(data[j+1]) > waitTime) {
                            data[j+1] = "" + epoch;
                            return true;
                        }
                        else {
                            p.sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Please wait another " + ChatColor.AQUA + (waitTime - (epoch - Long.parseLong(data[j+1]))) + ChatColor.GREEN + " seconds before opening!");
                            return false;
                        }
                    }
                }
                //System.out.println("Chest coordinate not found, creating...");
                int indexCreated = data.length-1;
                for (int j = 0; j<data.length; j++) {
                    if (data[j] == "" || data[j] == null) {
                        indexCreated = j;
                        break;
                    }
                }
                data[indexCreated] = location;
                data[indexCreated+1] = "" + epoch;
                chestData[i] = data;
                return true;
            }
        }
        //System.out.println("User not found, creating...");
        chestData = addColumn(chestData);
        String[] userArray = chestData[chestData.length-1];
        userArray[0] = p.getDisplayName();
        userArray[1] = location;
        userArray[2] = "" + epoch;
        chestData[chestData.length-1] = userArray;
        return true;
    }

    private String[][] addColumn(String[][] arr) {
        String[][] newArray = new String[arr.length+1][arr[0].length];
        for (int i = 0; i<arr.length; i++) {
            for (int j = 0; j<arr[0].length; j++) {
                newArray[i][j] = arr[i][j];
            }
        }
        int i = newArray.length-1;
        for (int j = 0; j<newArray[0].length; j++) {
            newArray[i][j] = "";
        }
        return newArray;
    }
    private void printArray(String[][] arr) {
        for (int i = 0; i<arr[0].length; i++) {
            String printString = "";
            for (int j = 0; j<arr.length; j++) {
                printString = printString + arr[j][i];
            }
            System.out.println(printString);
        }
    }

    //fencingparry4 | 35,2,7 5412543214231564 | 43,2,6  2354362345234 |
    /*
    private boolean checkAvailiablility(Player p, String location) {
        for (int i = 0; i<chestData.size(); i++) {
            System.out.println(i + " " + chestData.get(i));
        }
        long epoch = System.currentTimeMillis()/1000;
        for (int i = 0; i<chestData.size(); i++) {
            if (chestData.get(i).toString().contains(p.getDisplayName())) {
                String data = chestData.get(i).toString();
                if (data.contains(location)) {
                    if (Long.parseLong(data.substring(data.indexOf(location)).substring(data.indexOf(" ")+1,data.indexOf("|")-1))  -  epoch > 500) {
                        int indexOfEpoch = data.indexOf(data.substring(data.indexOf(location)).substring(data.indexOf(" ")+1,data.indexOf("|")-1));
                        data = data.substring(0,indexOfEpoch) + epoch + (data.substring(indexOfEpoch).indexOf("|")-1);
                        chestData.set(i,data);
                        System.out.println(data);
                        return true;
                    }
                    else {
                        p.sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + " Please wait an additional " + ChatColor.AQUA + (500-(Long.parseLong(data.substring(data.indexOf(location)).substring(data.indexOf(" ")+1,data.indexOf("|")-1))  -  epoch)) + ChatColor.GREEN + " seconds to open");
                        return false;
                    }
                }
                else {
                    data = data + location + " " + epoch + " | ";
                    chestData.set(i, data);
                    System.out.println(data);
                    return true;
                }
            }
            else {
                System.out.println(chestData.get(i));
                chestData.add(p.getDisplayName() + ": ");
                return true;
            }
        }
        return false;
    }
*/
}