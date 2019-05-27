package com.revivalmodding.revivalcore.core.registry;

import java.util.Collection;
import java.util.HashSet;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.DebugAbility0;
import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderSuitMaker;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import com.revivalmodding.revivalcore.core.common.events.RVRegistryEvent;
import com.revivalmodding.revivalcore.core.common.items.CoreItems;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.SuitDebug;
import com.revivalmodding.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.recipes.RVRecipeBuilder;
import com.revivalmodding.revivalcore.util.helper.IHaveItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class Registries {
	
	public static final HashSet<AbstractSuit> SUITS = new HashSet<>();
	public static final HashSet<RVRecipe> SUIT_RECIPES = new HashSet<>();
	public static final HashSet<AbilityBase> ABILITIES = new HashSet<>();

    @EventBusSubscriber
    public static class Registry {

        @SubscribeEvent
        public static void onSuitMakerRecipesRegister(RVRegistryEvent.SuitMakerRecipeRegistryEvent e) {
        	//TODO: recipes for suit maker
        	e.register(
        			RVRecipeBuilder.create()
        			.name("debug")
        			.result(CoreItems.coffee_mug)
        			.addIngredient(Items.DIAMOND, 2)
        			.craftingTime(250)
        			.build());
        }
        
        @SubscribeEvent
        public static void registerAbility(RVRegistryEvent.AbilityRegistryEvent e) {
        	e.register(new DebugAbility0());
        }

        @SubscribeEvent
        public static void onSuitRegister(RVRegistryEvent.SuitRegistryEvent e) {
        	e.register(new SuitDebug());
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            CoreItems.registerRenders();
            for(Block block : CoreBlocks.BLOCK_LIST) {
                if(block instanceof IHaveItem) {
                    if(((IHaveItem) block).hasItem())
                        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
                }
            }
        }

        @SubscribeEvent
        public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
            IForgeRegistry<EntityEntry> reg = e.getRegistry();
            reg.registerAll();
        }

        public static class EntityEntries {

        }

        // Use in preinit in mod.
        public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
            GameRegistry.registerTileEntity(clazz, new ResourceLocation(RevivalCore.MODID, name));
        }
    }

    public static class TileRegistry {
        public static void init() {
            Registries.Registry.registerTileEntity(TileEntitySuitMaker.class, "suit_maker");
        }

        @SideOnly(Side.CLIENT)
        public static void bindEntityTEISR() {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuitMaker.class, new RenderSuitMaker());
        }
    }
    
    public static final class SuitRegistry implements IRegistry<AbstractSuit>
    {
    	private static final SuitRegistry INSTANCE = new SuitRegistry();
    	
    	public static SuitRegistry instance()
    	{
    		return INSTANCE;
    	}
    	
    	@Override
    	public HashSet<AbstractSuit> getRegistry()
    	{
    		return Registries.SUITS;
    	}
    	
    	@Override
    	public void register(AbstractSuit toRegister) 
    	{
    		if(!containsObject(toRegister)) {
    			this.getRegistry().add(toRegister);
    		}
    	}
    	
    	@Override
    	public void registerAll(AbstractSuit[] toRegister)
    	{
    		for(AbstractSuit suit : toRegister) {
    			register(suit);
    		}
    	}
    	
    	@Override
    	public void registerAll(Collection<AbstractSuit> toRegister)
    	{
    		registerAll(toRegister);
    	}
    	
    	@Override
    	public boolean containsObject(AbstractSuit object)
    	{
    		for(AbstractSuit suit : this.getRegistry()) {
    			if(object.getName().equalsIgnoreCase(suit.getName())) {
    				return true;
    			}
    		}
    		return false;
    	}
    }
    
    public static final class SuitMakerRecipeRegistry implements IRegistry<RVRecipe>
    {
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
			if(!containsObject(toRegister)) {
				this.getRegistry().add(toRegister);
			}
		}

		@Override
		public void registerAll(RVRecipe[] toRegister) {
			for(RVRecipe recipe : this.getRegistry()) {
				this.register(recipe);
			}
			
		}

		@Override
		public void registerAll(Collection<RVRecipe> toRegister) {
			for(RVRecipe recipe : this.getRegistry()) {
				this.register(recipe);
			}
			
		}

		@Override
		public boolean containsObject(RVRecipe object) {
			for(RVRecipe recipe : this.getRegistry()) {
				if(object.getName().equalsIgnoreCase(recipe.getName())) {
					return true;
				}
			}
			return false;
		}
    }
    
    public static final class AbilityRegistry implements IRegistry<AbilityBase> {
    	
    	private static final AbilityRegistry INSTANCE = new AbilityRegistry();
    	
    	public static AbilityRegistry instance() {
    		return INSTANCE;
    	}
    	
    	@Override
    	public HashSet<AbilityBase> getRegistry() {
    		return Registries.ABILITIES;
    	}
    	
    	@Override
    	public void register(AbilityBase toRegister) {
    		if(!containsObject(toRegister)) {
    			this.getRegistry().add(toRegister);
    		}
    	}
    	
    	@Override
    	public void registerAll(AbilityBase[] toRegister) {
    		for(AbilityBase base : toRegister) {
    			register(base);
    		}
    		
    	}
    	
    	@Override
    	public void registerAll(Collection<AbilityBase> toRegister) {
    		registerAll(toRegister);
    	}
    	
    	@Override
    	public boolean containsObject(AbilityBase object) {
    		for(AbilityBase ability : this.getRegistry()) {
    			if(object.getName().equalsIgnoreCase(ability.getName())) {
    				return true;
    			}
    		}
    		return false;
    	}
    }
}
