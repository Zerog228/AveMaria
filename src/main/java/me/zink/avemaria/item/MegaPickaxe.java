package me.zink.avemaria.item;

import io.github.zerog228.usefless.item.CItem;
import io.github.zerog228.usefless.item.CStackCreator;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys;
import me.zink.avemaria.AveMaria;
import me.zink.avemaria.util.MCStackCreator;
import net.kyori.adventure.util.TriState;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class MegaPickaxe extends CItem {

    public MegaPickaxe(String itemKey) {
        super(itemKey);
    }

    //leather_horse_armor[dyed_color={rgb:3355443,show_in_tooltip:false},minecraft:item_model=bone] 1
    @Override
    public ItemStack initItem(String itemKey) {
        return CStackCreator.builder(Material.NETHERITE_PICKAXE)
                .maxStackSize(1)
                .name("<red>Big Pickaxe")
                //RegistrySet.keySet(RegistryKey.BLOCK, TypedKey.create(RegistryKey.BLOCK, Tag.MINEABLE_PICKAXE.key())),
                .tool(Tool.tool().defaultMiningSpeed(3f).damagePerBlock(3).addRule(
                        Tool.rule(
                                RegistryAccess.registryAccess()
                                        .getRegistry(RegistryKey.BLOCK)
                                        .getTag(BlockTypeTagKeys.MINEABLE_PICKAXE),
                                25F,
                                TriState.TRUE
                        )
                ).build())
                .damage(0)
                .maxDamage(6666)
                .build();
    }

    @Override
    public void initRecipe() {}

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e){
        if(equals(e.getPlayer().getInventory().getItemInMainHand())){
            Block central = e.getBlock();
            for(int x = -1; x < 2; x++){
                for(int z = -1; z < 2; z++){
                    for(int y = -1; y < 2; y++){
                        Block block = central.getRelative(x, y, z);
                        //If can be broken by pickaxe
                        if(Tag.MINEABLE_PICKAXE.isTagged(block.getType())){
                            block.breakNaturally(stack);
                        }
                    }
                }
            }
        }
    }
}
