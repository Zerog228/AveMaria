package me.zink.avemaria.structure;

import io.github.zerog228.usefless.structure.CStructureHelper;
import io.github.zerog228.usefless.util.Zone;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MegaCrafterLogic implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.BEACON){
            //Checking if it's our structure
            if(checkStructure(e.getClickedBlock())){
                e.setCancelled(true); //Cancel block clock event
                e.getPlayer().sendMessage("Clicked on structure!");
            }
        }
    }

    private static boolean checkStructure(Block block){
        //Creating zone where structure could be located
        Zone zone = new Zone(block.getRelative(-2, -1, -2), block.getRelative(2, 2, 2));

        //Comparing our zone with reference
        return CStructureHelper.compare(block, zone, "mega_crafter.nbt", true, true);
    }
}
