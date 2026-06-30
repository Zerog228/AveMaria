package me.zink.avemaria.item;

import io.github.zerog228.usefless.item.CItem;
import io.github.zerog228.usefless.item.CStackCreator;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys;
import net.kyori.adventure.util.TriState;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MegaAxe extends CItem {

    public MegaAxe(String itemKey) {
        super(itemKey);
    }

    @Override
    public ItemStack initItem(String itemKey) {
        return CStackCreator.builder(Material.NETHERITE_AXE)
                .maxStackSize(1)
                .name("<red>Big Axe")
                .tool(Tool.tool().defaultMiningSpeed(3f).damagePerBlock(3).addRule(
                        Tool.rule(
                                RegistryAccess.registryAccess()
                                        .getRegistry(RegistryKey.BLOCK)
                                        .getTag(BlockTypeTagKeys.MINEABLE_AXE),
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
                        if(Tag.MINEABLE_AXE.isTagged(block.getType())){
                            block.breakNaturally(stack);
                        }
                    }
                }
            }
        }
    }
}
