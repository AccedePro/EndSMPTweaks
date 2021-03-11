package me.AccedePro;

import org.bukkit.*;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnderDragonDeathListener implements Listener {

	private static String allUUID;
	private static Boolean foundPlayer = false;
	public static Player player;
	public static Location dragonDeathLoc = null;

	@EventHandler(priority = EventPriority.HIGH)
	public void onEnderDragonDeath(EntityDeathEvent e) {

		final LivingEntity dragonEntity = e.getEntity();
		final World theEnd = dragonEntity.getWorld();

		if (e.getEntityType() != null && EntityType.ENDER_DRAGON.compareTo(e.getEntityType()) == 0) {
			e.setDroppedExp(0);

			Location enderDragonLocation = e.getEntity().getLocation();
			dragonDeathLoc = e.getEntity().getLocation();

			List<String> playerList = Collections.singletonList(" ");
			try {
				playerList = Arrays.asList(EndSMPTweaks.playerConfigManager.getPlayerConfig().get("players").toString().split(","));
			} catch (Exception exception) {

			}
			List<Player> playerListOnline = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
			for (int j = 0; j < playerListOnline.size(); j++) {
				foundPlayer = false;
				for (int i = 0; i < playerList.size(); i++) {
					if (playerListOnline.get(j).getUniqueId().toString().contains(playerList.get(i))) {
						foundPlayer = true;
						player = playerListOnline.get(j).getPlayer();
						if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
							if (enderDragonLocation.distance(player.getLocation()) < 350) {
								System.out.println("[EndSMPTweaks] Found that " + player.getDisplayName() + " has defeated the dragon before!");
								giveXP20(dragonEntity, theEnd); //give 20L XP
							}
						}
					}
				}
				if (foundPlayer == false) {
					player = playerListOnline.get(j).getPlayer();
					if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
						if (enderDragonLocation.distance(player.getLocation()) < 350) {
							System.out.println("[EndSMPTweaks] Found that " + player.getDisplayName() + " has not defeated the dragon before!");
							giveXP68(dragonEntity, theEnd); //give 68L XP
							awardAchievement(theEnd);
							onDefeat();
						}
					}
				}
			}
		}
	}

	public void onDefeat() {
		if (EndSMPTweaks.playerConfigManager.getPlayerConfig().get("players") == null) {
			EndSMPTweaks.playerConfigManager.getPlayerConfig().set("players", player.getUniqueId().toString() + ",");
		} else {
			allUUID = (String) EndSMPTweaks.playerConfigManager.getPlayerConfig().get("players");
			EndSMPTweaks.playerConfigManager.getPlayerConfig().set("players", allUUID + player.getUniqueId().toString() + ",");
		}
		EndSMPTweaks.playerConfigManager.savePlayerConfig();
	}

	private void giveXP68(LivingEntity dragonEntity, World theEnd) {
		if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
			player.giveExp(12000);

			Location playerLocation = player.getEyeLocation();
			theEnd.spawnParticle(Particle.PORTAL, playerLocation, 100, 0.3f, 0.3f, 0.3f, 0.5f);
			System.out.println("[EndSMPTweaks] " + player.getDisplayName() + " has been given 68 levels for defeating the ender dragon for the first time!");
		}
	}

	private void giveXP20(LivingEntity dragonEntity, World theEnd) {
		if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
			player.giveExp(500);

			Location playerLocation = player.getEyeLocation();
			theEnd.spawnParticle(Particle.PORTAL, playerLocation, 100, 0.3f, 0.3f, 0.3f, 0.5f);
			System.out.println("[EndSMPTweaks] " + player.getDisplayName() + " has been given 20 levels for defeating the ender dragon again!");
		}
	}

	private void awardAchievement(World theEnd) {
		if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
			NamespacedKey key = NamespacedKey.minecraft("end/kill_dragon");
			AdvancementProgress progress = player.getAdvancementProgress(Bukkit.getAdvancement(key));
			for (String criteria : progress.getRemainingCriteria())
				progress.awardCriteria(criteria);
				System.out.println("[EndSMPTweaks] " + player.getDisplayName() + " was awarded advancement [Free the End]");
		}
	}
}