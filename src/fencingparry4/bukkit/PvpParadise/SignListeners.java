package fencingparry4.bukkit.PvpParadise;

        import org.bukkit.ChatColor;
        import org.bukkit.Location;
        import org.bukkit.Material;
        import org.bukkit.block.Block;
        import org.bukkit.block.Sign;
        import org.bukkit.entity.Entity;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.Action;
        import org.bukkit.event.block.SignChangeEvent;
        import org.bukkit.event.entity.PlayerDeathEvent;
        import org.bukkit.event.player.PlayerInteractEvent;
        import org.bukkit.potion.PotionEffect;
        import org.bukkit.potion.PotionEffectType;

/**
 * Created by Emerson on 7/23/2016.
 */

public class SignListeners implements Listener {
    private PvpParadise plugin;

    public static Location TeleportLocations;
    public static void setTeleportLocations(Location teleportLocations) {
        TeleportLocations = teleportLocations;
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
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(e.getClickedBlock().getType() == Material.CHEST) {
                //Add the chest stuff
            }
        }
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(1).equalsIgnoreCase("§4RANDOM TELEPORT")) {
                e.getPlayer().setFoodLevel(20);
                e.getPlayer().setMaxHealth(30);
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100,10));
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.YELLOW + "Processing teleport...");



                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Teleported!");






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


}
