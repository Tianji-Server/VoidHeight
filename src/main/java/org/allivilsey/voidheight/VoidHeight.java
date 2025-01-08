package org.allivilsey.voidheight;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public final class VoidHeight extends JavaPlugin {

    private final static int voidHeight = 0;

    @Override
    public void onEnable() {

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
}
