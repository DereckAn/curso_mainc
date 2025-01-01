package net.dereckan.cursopago.item.custom;

import net.dereckan.cursopago.item.ModArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * Custom armor item implementation that provides status effects when a complete set is worn.
 * Extends Minecraft's base ArmorItem class to add additional functionality.
 */
public class ModArmorItem extends ArmorItem {
    /**
     * Maps armor materials to their corresponding status effects.
     * Currently configured to give Haste and Jump Boost effects for Fluorite armor.
     * Effects are set to:
     * - Haste (Level 2, 400 ticks duration)
     * - Jump Boost (Level 2, 400 ticks duration)
     * Both effects are configured to not show particles and not be ambient.
     */
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterial.FLUORITE_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 1, false, false),
                                    new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 1, false, false))).build();

    /**
     * Constructor for the custom armor item.
     *
     * @param material The armor material to use
     * @param type     The armor piece type (helmet, chestplate, leggings, boots)
     * @param settings Additional item settings
     */
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    /**
     * Called every tick while the armor is in an inventory.
     * Checks if the player is wearing a full set and applies effects accordingly.
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {  // Only execute on server side
            if (entity instanceof PlayerEntity player) {
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /**
     * Evaluates which effects should be applied based on the armor material.
     *
     * @param player The player wearing the armor
     */
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    /**
     * Applies the status effects to the player if they're wearing the correct armor set.
     * Only applies effects if the player doesn't already have them.
     *
     * @param player           The player to receive the effects
     * @param mapArmorMaterial The armor material being checked
     * @param mapStatusEffect  The list of effects to apply
     */
    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial,
                                            List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream()
                .allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(),
                        instance.shouldShowParticles()));
            }
        }
    }

    /**
     * Checks if the player is wearing a complete set of armor (all slots filled).
     *
     * @param player The player to check
     * @return true if wearing a complete set, false otherwise
     */
    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    /**
     * Verifies if the player is wearing a complete set of the specified armor material.
     *
     * @param material The armor material to check for
     * @param player   The player to check
     * @return true if wearing a complete set of the specified material, false otherwise
     */
    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}