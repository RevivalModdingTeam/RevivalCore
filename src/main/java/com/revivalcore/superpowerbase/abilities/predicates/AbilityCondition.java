package com.revivalcore.superpowerbase.abilities.predicates;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.RevivalCore.revivalcore.RevivalCore;
import com.revivalcore.superpowerbase.Superpowerbase;
import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.util.handlers.SuperpowerHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class AbilityCondition {

    protected Predicate<PowerBase> predicate;
    protected ITextComponent displayText;

    public AbilityCondition(Predicate<PowerBase> predicate, ITextComponent displayText) {
        this.predicate = predicate;
        this.displayText = displayText;
    }

    public boolean test(PowerBase ability) {
        return this.predicate.test(ability);
    }

    public ITextComponent getDisplayText() {
        return displayText;
    }

    public static class ConditionFactory {

        private static Map<ResourceLocation, IAbilityConditionFactory> FACTORIES = new HashMap<>();

        public static void register(ResourceLocation key, IAbilityConditionFactory factory) {
            if (FACTORIES.containsKey(key))
                throw new IllegalStateException("Duplicate ability condition factory: " + key);
            FACTORIES.put(key, factory);
        }

        public static AbilityCondition parseCondition(JsonObject json, PowerBase ability, PowerBase.AbilityMap abilities) {
            IAbilityConditionFactory factory = FACTORIES.get(new ResourceLocation(JsonUtils.getString(json, "type")));
            if (factory != null) {
                return factory.parse(json, ability, abilities);
            }
            return null;
        }

    }

    @Mod.EventBusSubscriber
    public static class ConditionEntry extends IForgeRegistryEntry.Impl<ConditionEntry> {

        public static IForgeRegistry<ConditionEntry> CONDITION_REGISTRY;

        private Class<? extends AbilityCondition> clazz;

        public ConditionEntry(Class<? extends AbilityCondition> clazz, ResourceLocation registryName) {
            this.clazz = clazz;
            this.setRegistryName(registryName);
        }

        public Class<? extends AbilityCondition> getConditionClass() {
            return clazz;
        }

        @SubscribeEvent
        public static void onNewRegistry(RegistryEvent.NewRegistry event) {
            CONDITION_REGISTRY = new RegistryBuilder<ConditionEntry>().setName(new ResourceLocation(RevivalCore.MODID, "ability_condition"))
                    .setType(ConditionEntry.class).setIDRange(0, 512).create();
        }

        @SubscribeEvent
        public static void registerConditions(RegistryEvent.Register<ConditionEntry> e) {
            register(e.getRegistry(), AbilityConditionAbility.class, new ResourceLocation(RevivalCore.MODID, "ability"), (j, a, l) -> {
                PowerBase ab = l.get(JsonUtils.getString(j, "ability"));
                return ab != null ? new AbilityConditionAbility(ab) : null;
            });

            register(e.getRegistry(), AbilityConditionSuperpower.class, new ResourceLocation(RevivalCore.MODID, "has_superpower"), (j, a, l) -> {
                Superpowerbase superpower = SuperpowerHandler.SUPERPOWER_REGISTRY.getValue(new ResourceLocation(JsonUtils.getString(j, "superpower")));
                return superpower != null ? new AbilityConditionSuperpower(superpower) : null;
            });

            register(e.getRegistry(), AbilityConditionLevel.class, new ResourceLocation(RevivalCore.MODID, "level"), (j, a, l) -> new AbilityConditionLevel(JsonUtils.getInt(j, "level")));

            register(e.getRegistry(), AbilityConditionOr.class, new ResourceLocation(RevivalCore.MODID, "or"), (j, a, l) -> {
                JsonArray jsonArray = JsonUtils.getJsonArray(j, "conditions");
                AbilityCondition[] conditions = new AbilityCondition[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    conditions[i] = ConditionFactory.parseCondition(jsonArray.get(i).getAsJsonObject(), a, l);
                }
                return new AbilityConditionOr(conditions);
            });

            register(e.getRegistry(), AbilityConditionNot.class, new ResourceLocation(RevivalCore.MODID, "not"), (j, a, l) -> {
                JsonArray jsonArray = JsonUtils.getJsonArray(j, "conditions");
                AbilityCondition[] conditions = new AbilityCondition[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    conditions[i] = ConditionFactory.parseCondition(jsonArray.get(i).getAsJsonObject(), a, l);
                }
                return new AbilityConditionNot(conditions);
            });

            register(e.getRegistry(), AbilityConditionHeldItem.class, new ResourceLocation(RevivalCore.MODID, "held_item"), (j, a, l) -> {
                Item item = Item.REGISTRY.getObject(new ResourceLocation(JsonUtils.getString(j, "item")));
                String handString = JsonUtils.getString(j, "hand");
                EnumHand hand = handString.equals("main_hand") ? EnumHand.MAIN_HAND : (handString.equals("off_hand") ? EnumHand.OFF_HAND : null);
                return item == null || hand == null ? null : new AbilityConditionHeldItem(item, hand);
            });

            register(e.getRegistry(), AbilityConditionOpenArmor.class, new ResourceLocation(RevivalCore.MODID, "open_armor"), (j, a, l) -> {
                EntityEquipmentSlot slot = EntityEquipmentSlot.fromString(JsonUtils.getString(j, "slot"));
                return slot == null ? null : new AbilityConditionOpenArmor(slot);
            });

            register(e.getRegistry(), AbilityConditionPotionWeakness.class, new ResourceLocation(RevivalCore.MODID, "potion_weakness"), (j, a, l) -> {
                Potion potion = Potion.REGISTRY.getObject(new ResourceLocation(JsonUtils.getString(j, "potion")));
                return potion == null ? null : new AbilityConditionPotionWeakness(potion);
            });
        }

        public static void register(IForgeRegistry<ConditionEntry> registry, Class<? extends AbilityCondition> clazz, ResourceLocation loc, IAbilityConditionFactory factory) {
            registry.register(new ConditionEntry(clazz, loc));
            ConditionFactory.register(loc, factory);
        }

    }
}

