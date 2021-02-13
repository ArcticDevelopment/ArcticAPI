package dev.kyro.arcticapi;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        ArcticAPI.init(this, "", "");

        getCommand("api").setExecutor(new TestCommand());
    }

    @Override
    public void onDisable() {

    }
}
