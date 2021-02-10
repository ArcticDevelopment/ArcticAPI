package dev.kyro.arcticapi.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class APlayer {

    public UUID pUUID;
    public File playerFile;
    public FileConfiguration playerdata;

    public APlayer(UUID pUUID, File playerFile) {

        this.pUUID = pUUID;
        this.playerFile = playerFile;

        playerdata = YamlConfiguration.loadConfiguration(playerFile);
    }
}
