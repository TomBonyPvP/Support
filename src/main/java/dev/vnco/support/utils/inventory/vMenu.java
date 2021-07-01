package dev.vnco.support.utils.inventory;

import dev.vnco.support.config.ConfigHandler;
import dev.vnco.support.utils.Color;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class vMenu {

    @Getter private final String title;
    @Getter private final int slots;
    @Getter protected Inventory inventory;

    public vMenu(String title, int slots, boolean fill){
        this.title = title;
        this.slots = slots;

        this.inventory = Bukkit.createInventory(null, slots, Color.translate(title));

        if (fill){
            this.fillInventory();
        }
    }

    public void open(Player player){
        player.openInventory(this.inventory);
    }

    public void fillInventory(){
        ItemStack itemStack = new ItemStack(vItemMaker.makeItem(Material.getMaterial(ConfigHandler.FILL_MATERIAL), 1, ConfigHandler.FILL_DATA, ConfigHandler.FILL_DISPLAY_NAME, ConfigHandler.FILL_LORE));
        for (int i = 0; i < this.slots; ++i) {
            this.inventory.setItem(i, itemStack);
        }
    }

}
