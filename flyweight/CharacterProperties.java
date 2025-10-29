package flyweight;

import java.io.Serializable;


public class CharacterProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String font;
    private final String color;
    private final int size;
    
    public CharacterProperties(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }
    
    public String getFont() {
        return font;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        CharacterProperties that = (CharacterProperties) o;
        
        if (size != that.size) return false;
        if (!font.equals(that.font)) return false;
        return color.equals(that.color);
    }
    
    @Override
    public int hashCode() {
        int result = font.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + size;
        return result;
    }
}
