package elbrunanzo.cobble.fish;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

public class swimmingstoneenchantment extends Enchantment {


    public swimmingstoneenchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON , new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return true;
    }

    @Override
    public String getTranslationKey() {
        return "enchantment.cobblefish.stone";
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof cobblefishEntity) {
            target.damage(DamageSource.GENERIC, 200f);
            target.dropStack(new ItemStack(cobblefish.better_stone_fish, 1),0); // it drops 2 for some reason
            // it drops 2 so I nerfed the item
        }
    }
}
