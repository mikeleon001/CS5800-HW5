package flyweight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String documentName;
    private List<Character> characters;
    
    public Document(String documentName) {
        this.documentName = documentName;
        this.characters = new ArrayList<>();
    }
    

    public void addCharacter(char c, String font, String color, int size) {
        CharacterProperties properties = PropertiesFactory.getProperties(font, color, size);
        Character character = new Character(c, properties);
        characters.add(character);
    }
    

    public void editCharacter(int index, char newChar, String font, String color, int size) {
        if (index >= 0 && index < characters.size()) {
            CharacterProperties properties = PropertiesFactory.getProperties(font, color, size);
            characters.set(index, new Character(newChar, properties));
        }
    }
    

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Character c : characters) {
            sb.append(c.getCharacter());
        }
        return sb.toString();
    }
    

    public void save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving document: " + e.getMessage());
        }
    }
    

    public static Document load(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Document) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading document: " + e.getMessage());
            return null;
        }
    }
    
    public String getDocumentName() {
        return documentName;
    }
    
    public int getCharacterCount() {
        return characters.size();
    }
    
    public Character getCharacterAt(int index) {
        if (index >= 0 && index < characters.size()) {
            return characters.get(index);
        }
        return null;
    }
}
