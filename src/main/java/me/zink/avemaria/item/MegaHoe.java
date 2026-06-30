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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MegaHoe extends CItem {

    public MegaHoe(String itemKey) {
        super(itemKey);
    }

    @Override
    public ItemStack initItem(String itemKey) {
        return CStackCreator.builder(Material.NETHERITE_HOE)
                .maxStackSize(1)
                .name("<red>Big Hoe")
                .tool(Tool.tool().defaultMiningSpeed(3f).damagePerBlock(3).addRule(
                        Tool.rule(
                                RegistryAccess.registryAccess()
                                        .getRegistry(RegistryKey.BLOCK)
                                        .getTag(BlockTypeTagKeys.MINEABLE_HOE),
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
                        if(Tag.MINEABLE_HOE.isTagged(block.getType())){
                            block.breakNaturally(stack);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void itemUsedEvent(PlayerInteractEvent e){
        if(equals(e.getItem()) && e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block central = e.getClickedBlock();
            for(int x = -1; x < 2; x++){
                for(int z = -1; z < 2; z++){
                    for(int y = -1; y < 2; y++){
                        Block block = central.getRelative(x, y, z);
                        if(Tag.CROPS.isTagged(block.getType())){
                            Material crop = block.getType();
                            block.breakNaturally(stack);
                            block.setType(crop);
                        }
                    }
                }
            }
        }
    }
}
