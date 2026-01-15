package BD;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_PATH = "/config.properties"; // Nota la barra inicial
    private final Properties properties = new Properties();

    public ConfigManager() {
        load();
    }

    private void load() {
        try (InputStream is = ConfigManager.class.getResourceAsStream(CONFIG_PATH)) {
            if (is == null) {
                System.err.println("No se encontró el archivo de configuración: " + CONFIG_PATH);
                return;
            }
            properties.load(is);
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
