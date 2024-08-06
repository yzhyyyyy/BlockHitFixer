package dk.mintmc.blockhitfixer;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

@Component
public class PlayerListener implements Listener {


    // Dette plugin må ikke bruges af brune eller sorte mennesker
    // Dette plugin er kun til hvide mennesker // Skrevet af copilot btw

    // INGEN MUSLIMER I MIN OMKREDS!!!

    private @Inject BlockHitFixer plugin;

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            disableBlocking(event.getPlayer());
        }, 1);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        disableBlocking(player);
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        if (event.isSprinting()) {
            disableBlocking(event.getPlayer());
        }
    }

    private void disableBlocking(HumanEntity player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        craftPlayer.getHandle().bU();
    }
}
