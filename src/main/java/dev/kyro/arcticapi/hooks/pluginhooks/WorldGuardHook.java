package dev.kyro.arcticapi.hooks.pluginhooks;

import com.google.common.collect.Lists;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public class WorldGuardHook {

	public static void registerFlag(String name, boolean def) {

		FlagRegistry flagRegistry = WorldGuardPlugin.inst().getFlagRegistry();
		StateFlag fishingFlag = new StateFlag(name, def);

		try {

			flagRegistry.register(fishingFlag);
			System.out.println("Registered flag: " + name);
		} catch(Exception e) {

			System.out.println("Failed to register flag: " + name);
		}
	}

	public static boolean hasFlag(Location location, String flagName) {

		RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
		RegionQuery query = container.createQuery();
		ApplicableRegionSet set = query.getApplicableRegions(location);
		List<ProtectedRegion> regionList = Lists.newArrayList(set);

		if(set.isVirtual()) return false;

		for(ProtectedRegion protectedRegion : regionList) {

			for(Map.Entry<Flag<?>, Object> flagEntry : protectedRegion.getFlags().entrySet()) {

				System.out.println(flagEntry.getKey().getName().equals(flagName) && flagEntry.getValue() == StateFlag.State.ALLOW);
				if(flagEntry.getKey().getName().equals(flagName) && flagEntry.getValue() == StateFlag.State.ALLOW) return true;
			}
		}
		return false;
	}

}
