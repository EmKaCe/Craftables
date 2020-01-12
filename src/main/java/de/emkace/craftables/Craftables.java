package de.emkace.craftables;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class Craftables extends JavaPlugin {
    private NamespacedKey[] keys = {new NamespacedKey(this, "nametag"), new NamespacedKey(this, "saddle")};

    @Override
    public void onEnable() {
        getLogger().fine("Craftables: Enabling...");
        ItemStack nameTag = new ItemStack(Material.NAME_TAG, 3);
        ShapedRecipe nameTagRecipe = new ShapedRecipe(keys[0], nameTag);
        nameTagRecipe.shape(
                "X  ",
                "@@@",
                "@@@");
        nameTagRecipe.setIngredient('X', Material.STRING);
        nameTagRecipe.setIngredient('@', Material.PAPER);

        ItemStack saddle = new ItemStack(Material.SADDLE, 1);
        ShapedRecipe saddleRecipe = new ShapedRecipe(keys[1], saddle);
        saddleRecipe.shape(
                "@@@",
                "@ @",
                "X X");
        saddleRecipe.setIngredient('@', Material.LEATHER);
        saddleRecipe.setIngredient('X', Material.IRON_INGOT);

        getServer().addRecipe(nameTagRecipe);
        getLogger().fine("Craftables: Name Tag recipe added!");
        getServer().addRecipe(saddleRecipe);
        getLogger().fine("Craftables: Saddle recipe added!");
        getLogger().fine("Craftables: ...Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().fine("Craftables: Disabling...");
        Iterator<Recipe> recipeIterator = getServer().recipeIterator();
        int removed = 0;
        while(recipeIterator.hasNext() && removed < 2) {
            Recipe tmp = recipeIterator.next();
            if(tmp instanceof ShapedRecipe) {
                for (NamespacedKey key : keys) {
                    if(tmp.equals(key)) {
                        recipeIterator.remove();
                        getLogger().fine("Craftables: Removed one recipe");
                        removed++;
                    }
                }
            }
        }
        getLogger().fine("Craftables: Disabled");
    }
}
