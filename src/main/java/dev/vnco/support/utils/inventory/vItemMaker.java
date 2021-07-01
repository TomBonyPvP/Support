package dev.vnco.support.utils.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class vItemMaker extends ItemStack {

    @Getter @Setter public ItemStack itemStack;

    public static ItemStack makeItem(Material material){
        return makeItem(material, 1, (short) 0, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount){
        return makeItem(material, amount, (short) 0, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data){
        return makeItem(material, amount, data, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display){
        return makeItem(material, amount, data, display, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display, String lore){
        List<String> realLore = new ArrayList<>();
        Collections.addAll(realLore, lore);
        return makeItem(material, amount, data, display, realLore);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (display != null) {
            itemMeta.setDisplayName(display);
        }
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setLore(ItemStack itemStack, String... lore) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, lore);
        return setLore(itemStack, list);
    }

    public static ItemStack setLore(ItemStack itemStack, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setDurability(ItemStack itemStack, short durability){
        itemStack.setDurability(durability);
        return itemStack;
    }

    public static ItemStack setUnbreakable(ItemStack itemStack, boolean unbreakable) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.spigot().setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void setSkullOwner(ItemStack itemStack, String skullOwner) {
        if (itemStack.getType() == Material.SKULL_ITEM) {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(skullOwner);
            itemStack.setItemMeta(skullMeta);
        }
    }

    public static void addUnbreaking(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null) {
                if (itemStack.getType() != Material.AIR) {
                    Material type = itemStack.getType();
                    if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD || type == Material.GOLD_SWORD || type == Material.IRON_SWORD || type == Material.DIAMOND_SWORD || type == Material.BOW) {
                        setUnbreakable(itemStack, true);
                    }
                }
            }
        }
    }

    public static void removeUnbreaking(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null) {
                if (itemStack.getType() != Material.AIR) {
                    Material type = itemStack.getType();
                    if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD || type == Material.GOLD_SWORD || type == Material.IRON_SWORD || type == Material.DIAMOND_SWORD || type == Material.BOW) {
                        setUnbreakable(itemStack, false);
                    }
                }
            }
        }
    }

    public static void addEnchant(ItemStack itemStack, Enchantment enchantment, int level){
        if (itemStack != null){
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(enchantment, level, true);
            itemStack.setItemMeta(itemMeta);
        }
    }

    public static ItemStack getEnchantedBook(Enchantment enchantment, int level){
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta)itemStack.getItemMeta();
        itemMeta.addStoredEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
