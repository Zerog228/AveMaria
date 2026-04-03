package me.zink.avemaria.item;

import io.github.zerog228.usefless.item.CItem;
import io.github.zerog228.usefless.item.CStackCreator;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItem extends CItem {

    public CustomItem(String namespaceKey) {
        super(namespaceKey); //'namespace' is the tag that will be used in pair with your item. It should be lowercase, english latters and numbers, without spaces
    }

    //Here you should create your item instance.
    @Override
    public ItemStack initItem(String itemKey) {
        // CStackCreator will come in handy for it. I will discuss its functionality later
        return CStackCreator.builder(Material.ARROW)
                .maxStackSize(1)
                .name("<red>Undo")
                .build();
    }

    @Override
    public void initRecipe() {}

    //Event handler for our test item
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        /*
         *This event fires every time player interacts with something
         *To narrow it down to only interactions with our item we can use 'equals()'.
         *This is a built-in method for comparing provided item with our custom item instance by checking itemKey
         */
        if(equals(e.getItem()) && e.getAction() == Action.RIGHT_CLICK_AIR){
            e.getPlayer().sendMessage("Clicked!");
        }
    }
}
