package fencingparry4.bukkit.PvpParadise;

        import net.minecraft.server.v1_9_R2.RandomPositionGenerator;
        import org.apache.logging.log4j.core.config.plugins.Plugin;
        import org.bukkit.Bukkit;
        import org.bukkit.ChatColor;
        import org.bukkit.Location;
        import org.bukkit.block.Chest;
        import org.bukkit.block.Sign;
        import org.bukkit.entity.Player;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.Action;
        import org.bukkit.event.block.BlockPlaceEvent;
        import org.bukkit.event.block.SignChangeEvent;
        import org.bukkit.event.entity.PlayerDeathEvent;
        import org.bukkit.event.player.PlayerBedEnterEvent;
        import org.bukkit.event.player.PlayerInteractEvent;
        import org.bukkit.event.player.PlayerTeleportEvent;
        import org.bukkit.event.player.PlayerUnleashEntityEvent;

        import java.util.ArrayList;
        import java.util.Random;

        import static org.bukkit.Bukkit.getPluginManager;
        import static org.bukkit.Bukkit.getServer;

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
        if (e.getLine(1).equalsIgnoreCase("[teleport]")) {
            e.setLine(0, "-=*=-");
            e.setLine(1, "§4RANDOM TELEPORT");
            e.setLine(2, "-=*=-");
            e.getPlayer().sendMessage(ChatColor.BLUE + "PvpParadise>" + ChatColor.GOLD + " Random Teleport Sign Created.");
            e.setLine(3, "§3Right Click");
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(1).equalsIgnoreCase("§4RANDOM TELEPORT")) {
                e.getPlayer().setFoodLevel(20);
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GREEN + "Processing teleport...");


                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getX()));
                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getY()));
                e.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(TeleportLocations.getZ()));

                /*
                ArrayList<String> TPArray = new ArrayList<String>();
                String TeleportLocations = "" + plugin.getConfig().get("Teleport Locations").toString();
                e.getPlayer().sendMessage(ChatColor.GOLD + TeleportLocations);
                TPArray.add(TeleportLocations);
                String TeleportAmount = "" +  plugin.getConfig().get("Teleport Amount");
                int TeleportAmountInt = Integer.parseInt(TeleportAmount);
                int randomNum = (int) (Math.random() * TeleportAmountInt);
                e.getPlayer().sendMessage(ChatColor.BLUE + ">" + ChatColor.GOLD + "Random number: " + ChatColor.AQUA + randomNum + ChatColor.GOLD + ". Location selected: " + ChatColor.AQUA + "Ope you dont have that yet.");
                */


                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.DARK_GREEN + "You were teleported!");
            } else if (s.getLine(1).equalsIgnoreCase("God Sign")) {
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setMaxHealth(1000);
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GOLD + "GOD MODE ACTIVATED");
                e.getPlayer().setHealth(1000);
            } else if (s.getLine(1).equalsIgnoreCase("No God")) {
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setMaxHealth(20);
                e.getPlayer().sendMessage(ChatColor.BLUE + "> " + ChatColor.GOLD + "GOD MODE DEACTIVATED");
                e.getPlayer().setHealth(20);
            }
        }
    }
}
