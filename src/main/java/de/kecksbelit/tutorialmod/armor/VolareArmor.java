package de.kecksbelit.tutorialmod.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolareArmor extends ArmorItem {

    private boolean armorBroke = false;
    private boolean playerKnowsToFlyOnLaunch = false;

    public VolareArmor(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    ActionListener taskFlyingStateOfPlayer = new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        {
            setFlyingStateOfPlayer();
        }
    };

    @Override
    public void setDamage(ItemStack stack, int damage) {

        if(Minecraft.getInstance().player == null)
        {
            playerKnowsToFlyOnLaunch = false;
        }
        if(!playerKnowsToFlyOnLaunch)
        {
            setFlyingStateOfPlayer();
        }
        if(damage >= stack.getMaxDamage() && damage > 0)
        {
            armorBroke = true;
            setFlyingStateOfPlayer();
        }
        else
        {
            armorBroke = false;
        }
        super.setDamage(stack, damage);
    }

    private void setFlyingStateOfPlayer()
    {
        Player player = Minecraft.getInstance().player;
        if(player == null || player.getArmorSlots() == null)
        {
            return;
        }
        if(player.isCreative() || player.isSpectator())
        {
            return;
        }
        if(armorBroke)
        {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            return;
        }
        if(!playerKnowsToFlyOnLaunch)
        {
            playerKnowsToFlyOnLaunch = true;
        }

        boolean wearingArmor = player.getInventory().getArmor(0).getItem().equals(this.asItem());
        player.getAbilities().mayfly = wearingArmor;
        player.getAbilities().flying = wearingArmor;
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        Timer countdown = new Timer(50 ,taskFlyingStateOfPlayer);
        countdown.setRepeats(false);
        countdown.start();
        return super.getEquipmentSlot(stack);
    }
}

