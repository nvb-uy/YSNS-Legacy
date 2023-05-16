package elocindev.ysns.forge.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;


public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    public static final Path file = FMLPaths.GAMEDIR.get().toAbsolutePath().resolve("config").resolve("ysns.json");
    
    public static ConfigEntries loadConfig() {
      try {
          if (Files.notExists(file)) {
              ConfigEntries exampleConfig = new ConfigEntries();
              exampleConfig.blacklisted_entities.add("any_mod:example_entity");
              exampleConfig.blacklisted_entities.add("any_mod:another_example_entity");
              String defaultJson = BUILDER.toJson(exampleConfig);
              Files.writeString(file, defaultJson);
          }

          return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

      } catch (IOException exception) {
          throw new RuntimeException(exception);
      }
  }
}
