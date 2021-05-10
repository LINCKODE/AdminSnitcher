package me.linckode.adminsnitcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Configuration {

    private String chatMessage;
    private List<String> ignoredCommands;

    public static File getConfigFile() {
        return new File(AdminSnitcher.getInstance().getDataFolder() + File.separator + "config.yaml");
    }

    public Configuration(){}
    public Configuration(String chatMessage, String[] ignoredCommands) {
        this.chatMessage = chatMessage;
        this.ignoredCommands = Arrays.asList(ignoredCommands.clone());
    }

    public static Configuration getConfiguration() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(getConfigFile(), Configuration.class);
    }
    public static void writeDefaultConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        Configuration configuration = new Configuration("&8[&cA&4S&8]&c Op player &4%player% &chas issued: \"&4%command%&c\".", new String[]{"help", "tell", "as"});
        mapper.writeValue(getConfigFile(), configuration);
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public List<String> getIgnoredCommands() {
        return ignoredCommands;
    }

}
