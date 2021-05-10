package me.linckode.adminsnitcher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class AdminSnitcher extends JavaPlugin {

    private static AdminSnitcher instance;
    public static AdminSnitcher getInstance(){
        return instance;
    }
    private static Configuration configuration;
    public static Configuration getConfiguration(){
        return configuration;
    }

    @Override
    public void onEnable() {
        instance = this;
        loadConfigCheck();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("as").setExecutor(new AdminSnitcherCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static boolean loadConfigCheck(){

        boolean result = loadConfig();
        if (!result){
            getInstance().getLogger().severe("Errors were encountered while loading configuration! Disabling plugin!");
            Bukkit.getPluginManager().disablePlugin(getInstance());
        }
        return result;
    }
    private static boolean loadConfig(){
        if (!Configuration.getConfigFile().exists()) {
            try {
                getInstance().getDataFolder().mkdirs();
                Configuration.writeDefaultConfig();
                getInstance().getLogger().info("Created configuration file at location: " + Configuration.getConfigFile().getAbsolutePath());
                getInstance().getLogger().info("Use the command \"/as reload\" after editing it to apply the changes.");
            } catch (IOException e) {
                getInstance().getLogger().severe("Couldn't create config file at location: " + Configuration.getConfigFile().getAbsolutePath());
                e.printStackTrace();
                return false;
            }
        }

        try {
            configuration = Configuration.getConfiguration();
            getInstance().getLogger().info("Loaded config file.");
            getInstance().getLogger().info("Chat message: " + configuration.getChatMessage());
            getInstance().getLogger().info("Ignored commands:");
            for (String command : configuration.getIgnoredCommands()) {
                getInstance().getLogger().info("   - " + command);
            }
        } catch (IOException e) {
            getInstance().getLogger().severe("Could not load config file at location: " + Configuration.getConfigFile().getAbsolutePath());
            e.printStackTrace();
            return false;
        }

        getInstance().getLogger().info("Configuration successfully loaded.");
        return true;
    }
}
