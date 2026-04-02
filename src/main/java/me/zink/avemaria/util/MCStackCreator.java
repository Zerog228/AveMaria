package me.zink.avemaria.util;

import io.github.zerog228.usefless.item.CStackCreator;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.*;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.tags.BlockTypeTagKeys;
import io.papermc.paper.registry.tag.TagKey;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.TriState;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.BlockType;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MCStackCreator extends CStackCreator {

    public static Builder builder(ItemStack stack){
        return new Builder(stack);
    }

    public static Builder builder(Material material){
        return new Builder(material);
    }

    public static class Builder extends CStackCreator.Builder{

        protected ItemStack itemStack;

        public Builder(ItemStack itemStack) {
            super(itemStack);
        }

        public Builder(Material material) {
            super(material);
        }

        //NEW METHODS
        public Builder itemModel(String model){
            itemStack.setData(DataComponentTypes.ITEM_MODEL, Key.key(model));
            return this;
        }

        public Builder dyedColor(Color color){
            itemStack.setData(DataComponentTypes.DYED_COLOR, DyedItemColor.dyedItemColor(color));
            return this;
        }

        public Builder tool(Tool tool){
            itemStack.setData(DataComponentTypes.TOOL, tool);
            return this;
        }

        public Builder tool(DefaultTool tool, int damage_per_block, float mining_speed){
            itemStack.setData(DataComponentTypes.TOOL, tool.getTool(damage_per_block, mining_speed));
            return this;
        }

        public Builder damage(int damage){
            itemStack.setData(DataComponentTypes.DAMAGE, damage);
            return this;
        }

        public Builder maxDamage(int max_damage){
            itemStack.setData(DataComponentTypes.MAX_DAMAGE, max_damage);
            return this;
        }

        public Builder attackRange(AttackRange range){
            itemStack.setData(DataComponentTypes.ATTACK_RANGE, range);
            return this;
        }

        public Builder attackRange(float range){
            itemStack.setData(DataComponentTypes.ATTACK_RANGE, AttackRange.attackRange().maxReach(range).build());
            return this;
        }

        public Builder remove(DataComponentType dataComponentType){
            itemStack.unsetData(dataComponentType);
            return this;
        }

        public Builder remove(DataComponentType ... dataComponentTypes){
            for(DataComponentType type : dataComponentTypes){
                itemStack.unsetData(type);
            }
            return this;
        }

        //OVERRIDEN
        @Override
        public Builder item(ItemStack stack) {
            itemStack = super.item(stack).build();
            return this;
        }

        @Override
        public Builder material(Material material, boolean preserve) {
            itemStack = super.material(material, preserve).build();
            return this;
        }

        @Override
        public Builder name(Component name) {
            itemStack = super.name(name).build();
            return this;
        }

        @Override
        public Builder name(String name) {
            itemStack = super.name(name).build();
            return this;
        }

        @Override
        public Builder lore(List<Component> lore, boolean preserve) {
            itemStack = super.lore(lore, preserve).build();
            return this;
        }

        @Override
        public Builder loreS(List<String> lore, boolean preserve) {
            itemStack = super.loreS(lore, preserve).build();
            return this;
        }

        @Override
        public Builder rarity(ItemRarity rarity) {
            itemStack = super.rarity(rarity).build();
            return this;
        }

        @Override
        public Builder maxStackSize(int maxStackSize) {
            itemStack = super.maxStackSize(maxStackSize).build();
            return this;
        }

        @Override
        public Builder amount(int amount) {
            itemStack = super.amount(amount).build();
            return this;
        }

        @Override
        public Builder tooltip(TooltipDisplay display) {
            itemStack = super.tooltip(display).build();
            return this;
        }

        @Override
        public Builder data(String key, boolean value) {
            itemStack = super.data(key, value).build();
            return this;
        }

        @Override
        public Builder data(String... data) {
            itemStack = super.data(data).build();
            return this;
        }

        @Override
        public Builder customModelData(String model) {
            itemStack = super.customModelData(model).build();
            return this;
        }
    }

    public enum DefaultTool{
        PICKAXE(BlockTypeTagKeys.MINEABLE_PICKAXE),
        AXE(BlockTypeTagKeys.MINEABLE_AXE),
        HOE(BlockTypeTagKeys.MINEABLE_HOE),
        SHOVEL(BlockTypeTagKeys.MINEABLE_SHOVEL),
        SWORD(BlockTypeTagKeys.SWORD_EFFICIENT);

        private TagKey<BlockType> tagKey;
        DefaultTool(TagKey<BlockType> tagKey){
            this.tagKey = tagKey;
        }

        public Tool getTool(int damage_per_block, float speed){
            return Tool.tool().damagePerBlock(damage_per_block).addRule(
                    Tool.rule(
                            RegistryAccess.registryAccess()
                                    .getRegistry(RegistryKey.BLOCK)
                                    .getTag(tagKey),
                            speed,
                            TriState.TRUE
                    )
            ).build();
        }
    }
}
