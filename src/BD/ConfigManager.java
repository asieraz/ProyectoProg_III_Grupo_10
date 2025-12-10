package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_PATH = "config.properties";
    private final Properties properties = new Properties();

    public ConfigManager() {
        load();
    }

    private void load() {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            System.out.println("Configuración cargada desde: " + CONFIG_PATH);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de configuración: " + e.getMessage());
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key, int def) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(def)));
        } catch (NumberFormatException e) {
            System.err.println("Valor inválido para '" + key + "', usando defecto: " + def);
            return def;
        }
    }
}
