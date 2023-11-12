package de.kecksbelit.tutorialmod.base;

import de.kecksbelit.tutorialmod.Tutorialmod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public record ModArmorMaterial(String name, int durability, int[] protection, int enchantAbility, SoundEvent equipSound,
                               float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) implements ArmorMaterial
{
    private static final int[] DURABILITY_PER_SLOT = new int[] {10,15,16,11};

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return DURABILITY_PER_SLOT[pSlot.getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.protection[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantAbility;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return Tutorialmod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
