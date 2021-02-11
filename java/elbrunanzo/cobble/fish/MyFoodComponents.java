package elbrunanzo.cobble.fish;

import net.minecraft.item.FoodComponent;


public class MyFoodComponents {
    public static final FoodComponent better_stone_fish = (new FoodComponent.Builder().saturationModifier(0.6f).hunger(4).snack().build());
    public static final FoodComponent  stone_fish = (new FoodComponent.Builder().saturationModifier(0.5f).hunger(7).build());
    public static final FoodComponent  cobblestone_fish_item = (new FoodComponent.Builder().saturationModifier(0.2f).hunger(2).build());

}
