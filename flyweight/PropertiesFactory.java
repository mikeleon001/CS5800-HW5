package flyweight;

import java.util.HashMap;
import java.util.Map;


public class PropertiesFactory {
    private static final Map<String, CharacterProperties> propertiesCache = new HashMap<>();
    

    public static CharacterProperties getProperties(String font, String color, int size) {
        String key = font + "_" + color + "_" + size;
        
        CharacterProperties properties = propertiesCache.get(key);
        
        if (properties == null) {
            properties = new CharacterProperties(font, color, size);
            propertiesCache.put(key, properties);
        }
        
        return properties;
    }
}
