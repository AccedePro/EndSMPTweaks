package me.AccedePro;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//public class DebugCommandListener implements CommandExecutor {
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
//
//
//        if (cmd.getName().equalsIgnoreCase("edtlist")) {
//            List<String> playerList = Collections.singletonList(" ");
//            try {
//                playerList = Arrays.asList(EndSMPTweaks.playerConfigManager.getPlayerConfig().get("players").toString().split(","));
//            } catch (Exception exception) {
//
//            }
//            for (int i = 0; i < playerList.size(); i++) {
//                System.out.println(playerList.get(i));
//            }
//        }
//
//        if (cmd.getName().equalsIgnoreCase("edtplayers")) {
//            List<Player> playerListOnline = new ArrayList<>(sender.getServer().getOnlinePlayers());
//            for (int i = 0; i < playerListOnline.size(); i++) {
//                System.out.println(playerListOnline.get(i).getUniqueId().toString());
//            }
//        }
//
//        if (cmd.getName().equalsIgnoreCase("edtworld")) {
//            List<World> worlds = sender.getServer().getWorlds();
//            for (int i = 0; i < worlds.size(); i++) {
//                System.out.println(worlds.get(i).toString());
//            }
//        }
//
//        return false;
//
//    }
//}
