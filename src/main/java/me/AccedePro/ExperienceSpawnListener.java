package me.AccedePro;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import java.util.List;

public class ExperienceSpawnListener implements Listener {

    @EventHandler
    private void onXPTarget(EntityTargetLivingEntityEvent event) {
        if (event.getEntity().getType().equals(EntityType.EXPERIENCE_ORB)) {
            if (event.getEntity().getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                try {
                    if (event.getEntity().getLocation().distance(EnderDragonDeathListener.dragonDeathLoc) < 150) {
                        event.getEntity().remove();
                    }
                } catch (Exception e) {
                    return;
                }
            }
        }
    }

    @EventHandler
    private void onXPBottleEvent(ExpBottleEvent event) {
        int XPForPlayer;
        double distance = 10000;
        int playerKey = 0;
        Location breakLoc;
        List<Player> endPlayers;
        if (event.getEntity().getWorld().getEnvironment().equals(World.Environment.THE_END)) {
            try {
                if (event.getEntity().getLocation().distance(EnderDragonDeathListener.dragonDeathLoc) < 150) {
                    breakLoc = event.getEntity().getLocation();
                    XPForPlayer = event.getExperience();
                    event.setExperience(0);
                    endPlayers = breakLoc.getWorld().getPlayers();
                    for (int i = 0; i < endPlayers.size(); i++) {
                        if (distance > breakLoc.distance(endPlayers.get(i).getLocation())) {
                            distance = breakLoc.distance(endPlayers.get(i).getLocation());
                            playerKey = i;
                        }
                    }
                    endPlayers.get(playerKey).getPlayer().giveExp(XPForPlayer);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    @EventHandler
    private void onPortalInteract(EntityPortalEnterEvent event) {
        if (event.getEntity().getType().equals(EntityType.EXPERIENCE_ORB)) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    private void onEndMobDeath(EntityDeathEvent event) {
        int XPForPlayer;
        if (event.getEntity().getType().equals(EntityType.ENDERMAN)) {
            try {
                if (event.getEntity().getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                    if (event.getEntity().getLocation().distance(EnderDragonDeathListener.dragonDeathLoc) < 150) {
                        XPForPlayer = event.getDroppedExp();
                        event.setDroppedExp(0);
                        event.getEntity().getKiller().giveExp(XPForPlayer);
                    }
                }
            } catch (Exception e) {

            }
        }
        if (event.getEntity().getType().equals(EntityType.ENDERMITE)) {
            try {
                if (event.getEntity().getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                    if (event.getEntity().getLocation().distance(EnderDragonDeathListener.dragonDeathLoc) < 150) {
                        XPForPlayer = event.getDroppedExp();
                        event.setDroppedExp(0);
                        event.getEntity().getKiller().giveExp(XPForPlayer);
                    }
                }
            } catch (Exception e) {

            }
        }
        if (event.getEntity().getType().equals(EntityType.SHULKER)) {
            try {
                if (event.getEntity().getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                    if (event.getEntity().getLocation().distance(EnderDragonDeathListener.dragonDeathLoc) < 150) {
                        XPForPlayer = event.getDroppedExp();
                        event.setDroppedExp(0);
                        event.getEntity().getKiller().giveExp(XPForPlayer);
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}

