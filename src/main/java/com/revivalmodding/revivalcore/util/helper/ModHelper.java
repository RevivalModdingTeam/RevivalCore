package com.revivalmodding.revivalcore.util.helper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.RCTeam;

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
    
    public static JSONCreator jsonCretor() {
    	return JSON_CREATOR;
    }

    @SubscribeEvent
    public static void PreventNonBetaTester(PlayerEvent.PlayerLoggedInEvent e) {
        if(!betaTesterCheck(e.player.getUniqueID().toString()) && !getIsDev() && RevivalCore.check) {
            throw new IllegalStateException("You don't have acces to this!");
        }
    }
    
    public static final class JSONCreator {
    	
    	public void createItemModelFiles(String modID, String itemModelDirectoryPath) {
    		if(!getIsDev()) {
    			return;
    		}
    		ForgeRegistries.ITEMS.getValuesCollection().stream()
    				.filter(i -> i.getRegistryName().getNamespace().equals(modID))
    				.forEach(i -> {createItemModel(i, itemModelDirectoryPath, modID);});
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
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	// TODO block models/states
    }
 }
