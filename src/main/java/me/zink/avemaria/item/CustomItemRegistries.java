package me.zink.avemaria.item;

import io.github.zerog228.usefless.item.ICItem;

public class CustomItemRegistries {

    public static ICItem customItem, megaPickaxe, megaAxe, megaHoe, megaShovel, megaSword;

    public static void init(){
        customItem = new CustomItem("custom_item");
        megaPickaxe = new MegaPickaxe("mega_pickaxe");
        megaAxe = new MegaAxe("mega_axe");
        megaHoe = new MegaHoe("mega_hoe");
        megaShovel = new MegaShovel("mega_shovel");
        megaSword = new MegaSword("mega_sword");
    }
}
