package me.zink.avemaria.item;

import io.github.zerog228.usefless.item.CItem;
import io.github.zerog228.usefless.item.CStackCreator;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys;
import me.zink.avemaria.util.MCStackCreator;
import net.kyori.adventure.util.TriState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class MegaSword extends CItem {

    public MegaSword(String itemKey) {
        super(itemKey);
    }

    @Override
    public ItemStack initItem(String itemKey) {
        return CStackCreator.builder(Material.NETHERITE_SWORD)
                .maxStackSize(1)
                .name("<red>Fiery Sword")
                .tool(Tool.tool().defaultMiningSpeed(3f).damagePerBlock(3).addRule(
                        Tool.rule(
                                RegistryAccess.registryAccess()
                                        .getRegistry(RegistryKey.BLOCK)
                                        .getTag(BlockTypeTagKeys.SWORD_EFFICIENT),
                                25F,
                                TriState.TRUE
                        )
                ).build())
                .damage(0)
                .attackRange(6)
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
                        if(Tag.SWORD_EFFICIENT.isTagged(block.getType())){
                            block.breakNaturally(stack);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void entityHitEvent(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player player && equals(player.getInventory().getItemInMainHand())){
            Location central = e.getEntity().getLocation();
            for(Entity entity : central.getNearbyEntities(3, 3, 3)){
                if(entity != player){
                    entity.setFireTicks(80);
                }
            }
        }
    }
}
