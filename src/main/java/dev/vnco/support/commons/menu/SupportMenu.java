package dev.vnco.support.commons.menu;

import dev.vnco.support.Supports;
import dev.vnco.support.commons.Support;
import dev.vnco.support.config.ConfigHandler;
import dev.vnco.support.config.Language;
import dev.vnco.support.data.PlayerData;
import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.inventory.vItemMaker;
import dev.vnco.support.utils.inventory.vMenu;
import dev.vnco.support.utils.system.Software;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class SupportMenu extends vMenu implements Listener {

    private final Supports plugin;

    public SupportMenu(Supports plugin) {
        super(ConfigHandler.MENU_TITLE, ConfigHandler.MENU_SLOTS, ConfigHandler.FILL_ENABLED);
        this.plugin = plugin;

        this.setupButtons();
    }

    public void setupButtons(){
        for (Support support : this.plugin.getSupportManager().getSupports()){
            ItemStack itemStack = new ItemStack(vItemMaker.makeItem(Material.SKULL_ITEM, 1, (short) 3, Color.translate(support.getDisplayName()), Color.translateFromArrayList(support.getLore())));
            vItemMaker.setSkullOwner(itemStack, support.getSkullPlayer());
            this.inventory.setItem(support.getSlot(), itemStack);
        }
    }

    @EventHandler public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = this.plugin.getPlayerDataManager().get(player);

        int slot = event.getSlot();

        if (event.getInventory().getTitle().equals(Color.translate(ConfigHandler.MENU_TITLE))){
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getClickedInventory() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }

            for (Support support : this.plugin.getSupportManager().getSupports()) {
                if (slot == support.getSlot()) {
                    if (playerData.isUsedSupport()) {
                        player.closeInventory();
                        Color.toPlayer(player, Language.PREFIX + Language.CANT_OPEN);
                    } else {
                        for (String commands : support.getRewardsCommand()) {
                            Software.dispatch((commands)
                                    .replace("<player>", player.getName())
                                    .replace("<supporterName>", support.getPartnerName()));
                        }

                        playerData.setUsedSupport(true);

                        player.closeInventory();
                        Color.toPlayer(player, Language.PREFIX + Language.SUCCESSFULLY
                                .replace("<supporterName>", support.getPartnerName())
                                .replace("<supporterDisplayName>", support.getDisplayName()));
                    }
                }
            }
        }
    }

    @EventHandler public void onInventoryDrag(InventoryDragEvent event){
        if (event.getInventory().getTitle().equals(Color.translate(ConfigHandler.MENU_TITLE))){
            event.setCancelled(true);
        }
    }

}
