package com.revivalmodding.revivalcore.util.helper;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revivalmodding.revivalcore.util.RCTeam;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class ModHelper {

    public static List<String> betaTester = new ArrayList<>();
    public static List<String> teamMembers = new ArrayList<>();
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

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
        setBetaTester(RCTeam.X39);
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

    @SubscribeEvent
    public static void PreventNonBetaTester(PlayerEvent.PlayerLoggedInEvent e) {
        if(!ModHelper.betaTesterCheck(e.player.getUniqueID().toString()) && !ModHelper.getIsDev()) {
            throw new IllegalStateException("You don't have acces to this!");
        }
    }
 }
