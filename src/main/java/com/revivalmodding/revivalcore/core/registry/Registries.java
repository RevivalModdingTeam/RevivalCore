package com.revivalmodding.revivalcore.core.registry;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.abilities.AbilityBuilder;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.client.models.ModelTrailEditorAdvanced;
import com.revivalmodding.revivalcore.core.client.models.ModelTrailEditorBasic;
import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderSuitMaker;
import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderTrailEditor;
import com.revivalmodding.revivalcore.core.common.events.RCRegistryEvent;
import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.revivalmodding.revivalcore.core.common.tileentity.BasicTileEntities;
import com.revivalmodding.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Registries {

    public static final HashSet<AbstractSuit> SUITS = new HashSet<>();
    public static final HashSet<RVRecipe> SUIT_RECIPES = new HashSet<>();
    public static final HashSet<Ability> ABILITIES = new HashSet<>();

    @EventBusSubscriber
    public static class Registry {

        @SubscribeEvent
        public static void onAbilityRegister(RCRegistryEvent.AbilityRegistryEvent e) {
            AbilityBuilder.create()
                    .name("vibrate", "Ability Vibrate")
                    .price(3)
                    .canActivate(p -> CoreCapabilityImpl.getInstance(p).getMetaPowerData().canVibrate())
                    .guiIcon(new ResourceLocation(RevivalCore.MODID + ":textures/icons/abilityvibrate.png"))
                    .onUse((ability, player) -> {
                        ability.toggle();
                        ICoreCapability cap = CoreCapabilityImpl.getInstance(player);
                        cap.getMetaPowerData().setVibratingState(ability.isToggled());
                        cap.sync();
                    })
                    .build()
                    .register(e);
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            ForgeRegistries.ITEMS.getValuesCollection().stream().filter(i -> i.getRegistryName().getNamespace().equals(RevivalCore.MODID))
                    .forEach(i -> ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory")));
            for (Block block : ForgeRegistries.BLOCKS.getValuesCollection().stream().filter(b -> b.getRegistryName().getNamespace().equals(RevivalCore.MODID)).collect(Collectors.toList())) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
            }
        }

        @SubscribeEvent
        public static void onSuitRegistry(RCRegistryEvent.SuitRegistryEvent e) {
            AbstractSuit grimmlawke = new AbstractSuit("grimmlawke", Color.LIGHT_GRAY) {
                @Override
                public ItemSuit getHelmet() {
                    return ItemRegistry.CoreItems.GRIMMLAWKE_HEAD;
                }

                @Override
                public ItemSuit getChest() {
                    return ItemRegistry.CoreItems.GRIMMLAWKE_CHEST;
                }

                @Override
                public ItemSuit getLeggings() {
                    return ItemRegistry.CoreItems.GRIMMLAWKE_LEGS;
                }

                @Override
                public void handleEffects(EntityPlayer player) {
                }
            };
            AbstractSuit royalZano = new AbstractSuit("royal_zano", new Color(66, 220, 156)) {
                @Override
                public ItemSuit getHelmet() {
                    return ItemRegistry.CoreItems.ROYAL_ZANO_HEAD;
                }

                @Override
                public ItemSuit getChest() {
                    return ItemRegistry.CoreItems.ROYAL_ZANO_CHEST;
                }

                @Override
                public ItemSuit getLeggings() {
                    return ItemRegistry.CoreItems.ROYAL_ZANO_LEGS;
                }

                @Override
                public void handleEffects(EntityPlayer player) {
                    if (!player.world.isRemote)
                        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 2, false, false));
                }
            };
            AbstractSuit darkMessiah = new AbstractSuit("dark_messiah", new Color(104, 32, 116)) {
                @Override
                public ItemSuit getHelmet() {
                    return ItemRegistry.CoreItems.DARK_MESSIAH_HEAD;
                }

                @Override
                public ItemSuit getChest() {
                    return ItemRegistry.CoreItems.DARK_MESSIAH_CHEST;
                }

                @Override
                public ItemSuit getLeggings() {
                    return ItemRegistry.CoreItems.DARK_MESSIAH_LEGS;
                }

                @Override
                public void handleEffects(EntityPlayer player) {
                    if (!player.world.isRemote)
                        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 15, false, false));
                }
            };
            e.registerAll(new AbstractSuit[] {grimmlawke, royalZano, darkMessiah});
        }

        @SubscribeEvent
        public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
            IForgeRegistry<EntityEntry> reg = e.getRegistry();
            EntityEntries.registerEntries();
            reg.registerAll(EntityEntries.ENTRY_LIST.toArray(new EntityEntry[0]));
            EntityEntries.ENTRY_LIST.clear();
        }

        // Use in preinit in mod.
        public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
            GameRegistry.registerTileEntity(clazz, new ResourceLocation(RevivalCore.MODID, name));
        }

        public static class EntityEntries {

            private static final List<EntityEntry> ENTRY_LIST = new ArrayList<>();
            private static int ID = -1;

            // Register entity entries here
            public static void registerEntries() {

            }

            private static void registerEntry(String name, Class<? extends Entity> entityClass) {
                EntityEntry entry = createEntryBuilder(name).entity(entityClass).build();
                ENTRY_LIST.add(entry);
            }

            private static void registerEntry(String name, Class<? extends Entity> entityClass, int primaryColor, int secondaryColor) {
                EntityEntry entry = createEntryBuilder(name).entity(entityClass).egg(primaryColor, secondaryColor).build();
                ENTRY_LIST.add(entry);
            }

            private static void registerEntry(String name, Class<? extends Entity> entityClass, int trackRange, int updatesPerSecond, boolean sendsVelocityUpdates) {
                EntityEntry entry = createEntryBuilder(name).entity(entityClass).tracker(trackRange, updatesPerSecond, sendsVelocityUpdates).build();
                ENTRY_LIST.add(entry);
            }

            private static void registerEntry(String name, Class<? extends Entity> entityClass, int primaryColor, int secondaryColor, int trackRange, int updatesPerSecond, boolean sendsVelocityUpdates) {
                EntityEntry entry = createEntryBuilder(name).entity(entityClass).egg(primaryColor, secondaryColor).tracker(trackRange, updatesPerSecond, sendsVelocityUpdates).build();
                ENTRY_LIST.add(entry);
            }

            private static <E extends Entity> EntityEntryBuilder<E> createEntryBuilder(String name) {
                EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
                ResourceLocation rl = new ResourceLocation(RevivalCore.MODID, name);
                return builder.id(rl, ID++).name(rl.toString());
            }
        }
    }

    public static class TileRegistry {
        public static void init() {
            Registries.Registry.registerTileEntity(TileEntitySuitMaker.class, "suit_maker");
            Registry.registerTileEntity(BasicTileEntities.TileEntityTrailEditorBasic.class, "trail_editor_basic");
            Registry.registerTileEntity(BasicTileEntities.TileEntityTrailEditorAdvanced.class, "trail_editor_advanced");
        }

        @SideOnly(Side.CLIENT)
        public static void bindEntityTEISR() {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuitMaker.class, new RenderSuitMaker());
            ClientRegistry.bindTileEntitySpecialRenderer(BasicTileEntities.TileEntityTrailEditorBasic.class, new RenderTrailEditor<>(new ModelTrailEditorBasic()));
            ClientRegistry.bindTileEntitySpecialRenderer(BasicTileEntities.TileEntityTrailEditorAdvanced.class, new RenderTrailEditor<>(new ModelTrailEditorAdvanced()));
        }
    }

    public static final class SuitRegistry implements IRegistry<AbstractSuit> {
        private static final SuitRegistry INSTANCE = new SuitRegistry();

        public static SuitRegistry instance() {
            return INSTANCE;
        }

        @Override
        public HashSet<AbstractSuit> getRegistry() {
            return Registries.SUITS;
        }

        @Override
        public void register(AbstractSuit toRegister) {
            if (!containsObject(toRegister)) {
                this.getRegistry().add(toRegister);
            }
        }

        @Override
        public void registerAll(AbstractSuit[] toRegister) {
            for (AbstractSuit suit : toRegister) {
                register(suit);
            }
        }

        @Override
        public void registerAll(Collection<AbstractSuit> toRegister) {
            registerAll(toRegister.toArray(new AbstractSuit[0]));
        }

        @Override
        public boolean containsObject(AbstractSuit object) {
            for (AbstractSuit suit : this.getRegistry()) {
                if (object.getName().equalsIgnoreCase(suit.getName())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static final class SuitMakerRecipeRegistry implements IRegistry<RVRecipe> {
        private static final SuitMakerRecipeRegistry INSTANCE = new SuitMakerRecipeRegistry();

        public static SuitMakerRecipeRegistry instance() {
            return INSTANCE;
        }

        @Override
        public HashSet<RVRecipe> getRegistry() {
            return Registries.SUIT_RECIPES;
        }

        @Override
        public void register(RVRecipe toRegister) {
            if (!containsObject(toRegister)) {
                this.getRegistry().add(toRegister);
            }
        }

        @Override
        public void registerAll(RVRecipe[] toRegister) {
            for (RVRecipe recipe : this.getRegistry()) {
                this.register(recipe);
            }

        }

        @Override
        public void registerAll(Collection<RVRecipe> toRegister) {
            for (RVRecipe recipe : this.getRegistry()) {
                this.register(recipe);
            }

        }

        @Override
        public boolean containsObject(RVRecipe object) {
            for (RVRecipe recipe : this.getRegistry()) {
                if (object.getName().equalsIgnoreCase(recipe.getName())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static final class AbilityRegistry implements IRegistry<Ability> {

        private static final AbilityRegistry INSTANCE = new AbilityRegistry();

        public static AbilityRegistry instance() {
            return INSTANCE;
        }

        @Override
        public HashSet<Ability> getRegistry() {
            return Registries.ABILITIES;
        }

        @Override
        public void register(Ability toRegister) {
            if (!containsObject(toRegister)) {
                this.getRegistry().add(toRegister);
            }
        }

        @Override
        public void registerAll(Ability[] toRegister) {
            for (Ability base : toRegister) {
                register(base);
            }

        }

        @Override
        public void registerAll(Collection<Ability> toRegister) {
            registerAll(toRegister.toArray(new Ability[0]));
        }

        @Override
        public boolean containsObject(Ability object) {
            for (Ability ability : this.getRegistry()) {
                if (object.getName().equalsIgnoreCase(ability.getName())) {
                    return true;
                }
            }
            return false;
        }
    }
}
