package org.allivilsey.voidheight;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public final class VoidHeight extends JavaPlugin implements CommandExecutor {

    private static int voidHeight;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getConfig().getDouble("Height", 0);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if ((player.getLocation().getY() <= voidHeight) && player.getHealth() > 0) {
                    player.setHealth(0);
                    player.sendMessage("你落入了虚空");
                }
            }
        }, 0L, 1L);
    }

    @Override
    public void onDisable() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("voidHeight.config")) {
            if (args.length != 1) {
                sender.sendMessage("你需要输入你想要设置的高度");
            } else {
                try {
                    voidHeight = Integer.parseInt(args[0]);
                    getConfig().set("Height", args[0]);
                    saveConfig();
                    returnMessage(sender, "虚空高度已变更为" + args[0]);
                } catch (Exception e) {
                    returnMessage(sender, Color.RED + "错误：" + e);
                }
            }
            return true;
        }
        return false;
    }

    public static void returnMessage(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage("[VoidHeight] " + message);
        } else {
            Bukkit.getLogger().info("[VoidHeight] " + message);
        }
    }
}
