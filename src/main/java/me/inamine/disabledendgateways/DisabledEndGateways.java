package me.inamine.disabledendgateways;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisabledEndGateways extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    this.saveDefaultConfig();
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }


  @EventHandler
  public void onPortalEnter(PlayerTeleportEvent event) {
    if (event.getCause() == (PlayerTeleportEvent.TeleportCause.END_GATEWAY)) {
      String command = this.getConfig().getString("command-to-run");
      String message = this.getConfig().getString("player-warning");
      Player player = event.getPlayer();
      if (command != null && !command.equals("")) {
        if (!command.startsWith("/")) command = "/" + command;
        event.getPlayer().chat(command);
      }
      if (message != null && !message.equals("")) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
      }
      event.setCancelled(true);
    }
  }
}
