package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("unused")
public class APlayerData {

    private static HashMap<UUID, APlayer> playerData = new HashMap<>();

    public static void init() {

        File folder = new File(ArcticAPI.plugin.getDataFolder(), "playerdata/");
        if(!folder.exists()) return;
        File[] files = folder.listFiles();

        for(File file : files) {

            if(!file.isFile() || !file.getName().endsWith(".yml")) continue;

            try {

                UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
                APlayer aPlayer = new APlayer(pUUID, file);
                playerData.put(pUUID, aPlayer);
            } catch(Exception ignored) {}
        }
    }

    public static FileConfiguration getPlayerData(UUID pUUID) {

        if(!playerData.containsKey(pUUID)) return createPlayerData(pUUID).playerdata;
        APlayer aPlayer = playerData.get(pUUID);

        return aPlayer.playerdata;
    }

    public static void savePlayerData(UUID pUUID) {

        if(!playerData.containsKey(pUUID)) return;
        APlayer aPlayer = playerData.get(pUUID);

        try {
            aPlayer.playerdata.save(aPlayer.playerFile);
        } catch (IOException ignored) {}
    }

    public static APlayer createPlayerData(UUID pUUID) {

        File playerFile = new File(ArcticAPI.plugin.getDataFolder(), "playerdata/" + pUUID + ".yml");

        if(!playerFile.exists()) {
            try {
                boolean ignored = playerFile.mkdirs();
                boolean ignored2 = playerFile.createNewFile();
            } catch(IOException exception) {

                exception.printStackTrace();
            }
        }

        APlayer aPlayer = new APlayer(pUUID, playerFile);
        playerData.put(pUUID, aPlayer);

        return aPlayer;
    }
}
