package com.revivalmodding.revivalcore.util.helper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.RCTeam;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber
public class ModHelper {

    public static List<String> betaTester = new ArrayList<>();
    public static List<String> teamMembers = new ArrayList<>();
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final JSONCreator JSON_CREATOR = new JSONCreator();

    public static boolean getIsDev() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public static void startupChecks() {
        if (getIsDev()) {

        }
        setTeamMembers(RCTeam.JOSIA50);
        setTeamMembers(RCTeam.TUD);
        setTeamMembers(RCTeam.TOMA);

        setBetaTester(RCTeam.KIROZAKI);
        setBetaTester(RCTeam.CHARLES);
        betaTester.addAll(teamMembers);
    }

    public static void setBetaTester(String uuid) {
        betaTester.add(uuid);
    }

    public static void setTeamMembers(String uuid) {
        teamMembers.add(uuid);
    }

    public static boolean betaTesterCheck(String uuid) {
        for (String uuid1 : betaTester) {
            if (uuid1.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static boolean teamMemberCheck(String uuid) {
        for(String uuid1 : teamMembers) {
            if(uuid1.equals(uuid)) {
                return true;
            }
        }
        return false;
    }
    
    public static JSONCreator jsonCreator() {
    	return JSON_CREATOR;
    }

    @SubscribeEvent
    public static void PreventNonBetaTester(PlayerEvent.PlayerLoggedInEvent e) {
        if(!betaTesterCheck(e.player.getUniqueID().toString()) && !getIsDev() && RevivalCore.check) {
            throw new IllegalStateException("You don't have acces to this!");
        }
    }
    
    /** Created by Toma **/
    public static final class JSONCreator {
    	
    	/**
    	 * Generates all required .json files for both blocks and items
    	 * Call before registering models!
    	 * @param modID
    	 * @param assetsPath - path to {@code /main/resources/assets}
    	 */
    	public void generateFiles(String modID, String assetsPath) {
    		createBlockModelFiles(modID, assetsPath + "/" + modID);
    		createItemModelFiles(modID, assetsPath + "/" + modID + "/models/item");
    	}
    	
    	/**
    	 * Generates item model json file
    	 * @param modID - the ID of mod for which we are generating the json files
    	 * @param path - path to models/item directory in your pc
    	 */
    	public void createItemModelFiles(String modID, String itemModelDirectoryPath) {
    		if(!getIsDev()) {
    			return;
    		}
    		ForgeRegistries.ITEMS.getValuesCollection().stream()
    				.filter(i -> i.getRegistryName().getNamespace().equals(modID))
    				.forEach(i -> {createItemModel(i, itemModelDirectoryPath, modID);});
    	}
    	
    	/**
    	 * Generates blockstate, model and item model json files
    	 * <u>Must be called before item model creation!</u> -> Because of item block file
    	 * @param modID - the ID of mod for which we are generating the json files
    	 * @param path - path to {@code assets/modid} directory in your pc
    	 */
    	public void createBlockModelFiles(String modID, String path) {
    		if(!getIsDev()) {
    			return;
    		}
    		ForgeRegistries.BLOCKS.getValuesCollection().stream()
    			.filter(b -> b.getRegistryName().getNamespace().equals(modID))
    			.forEach(b -> {createBlockModel(b, modID, path);});
    	}
    	
    	private void createItemModel(Item item, String path, String id) {
    		File file = new File(path + "/" + item.getRegistryName().getPath() + ".json");
    		if(!file.exists()) {
    			try {
    				file.createNewFile();
    				FileWriter writer = new FileWriter(file);
    				String json = 
    				"{\n" +
    				"\t\"parent\": \"item/generated\",\n" +
    				"\t\"textures\": {\n" +
    				"\t\t\"layer0\": \""+id+":items/" + item.getRegistryName().getPath() + "\"\n" +
    				"\t}\n" +
    				"}";
    				writer.write(json);
    				writer.close();
    				RevivalCore.logger.info("Created item model for {}", item.getRegistryName());
    			} catch(SecurityException e) {
    				RevivalCore.logger.fatal("Couldn't create new file in {}, no access", file.getPath());
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	private void createBlockModel(Block block, String modID, String path) {
    		String name = block.getRegistryName().getPath();
    		File blockState = new File(path + "/blockstates/" + name + ".json");
    		File model = new File(path + "/models/block/" + name + ".json");
    		File item = new File(path + "/models/item/" + name + ".json");
    		if(!blockState.exists()) {
    			try {
    				blockState.createNewFile();
    				FileWriter writer = new FileWriter(blockState);
    				String json = 
    				"{\n"+
    				"\t\"variants\": {\n"+
    				"\t\t\"normal\": {\n"+
    				"\t\t\t\"model\": \""+block.getRegistryName().toString()+"\"\n"+
    				"\t\t}\n"+
    				"\t}\n"+
    				"}";
    				writer.write(json);
    				writer.close();
    				RevivalCore.logger.info("Created new blockstate file for {}", block.getRegistryName());
    			} catch(SecurityException e) {
    				RevivalCore.logger.fatal("Couldn't create new file in {}, no access", blockState.getPath());
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    		if(!model.exists()) {
    			try {
    				model.createNewFile();
    				FileWriter writer = new FileWriter(model);
    				String json =
    				"{\n"+
    				"\t\"parent\": \"block/cube_all\",\n"+
    				"\t\"textures\": {\n"+
    				"\t\t\"all\": \""+modID+":blocks/"+name+"\"\n"+
    				"\t}\n"+
    				"}";
    				writer.write(json);
    				writer.close();
    				RevivalCore.logger.info("Created new model file for {}", block.getRegistryName());
    			} catch(SecurityException e) {
    				RevivalCore.logger.fatal("Couldn't create new file in {}, no access", model.getPath());
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    		if(!item.exists()) {
    			try {
    				item.createNewFile();
    				FileWriter writer = new FileWriter(item);
    				String json =
    				"{\n"+
    				"\t\"parent\": \""+modID+":block/"+name+"\"\n"+
    				"}";
    				writer.write(json);
    				writer.close();
    				RevivalCore.logger.info("Created new item file for {}", block.getRegistryName());
    			} catch(SecurityException e) {
    				RevivalCore.logger.fatal("Couldn't create new file in {}, no access", item.getPath());
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
 }
