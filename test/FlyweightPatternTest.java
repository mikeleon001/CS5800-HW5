package test;

import flyweight.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;


public class FlyweightPatternTest {
    
    @Test
    public void testPropertiesFactory_CreateProperties() {
        CharacterProperties props = PropertiesFactory.getProperties("Arial", "Red", 12);
        
        assertNotNull(props);
        assertEquals("Arial", props.getFont());
        assertEquals("Red", props.getColor());
        assertEquals(12, props.getSize());
    }
    
    @Test
    public void testPropertiesFactory_ReusesSameProperties() {
        CharacterProperties props1 = PropertiesFactory.getProperties("Arial", "Red", 12);
        CharacterProperties props2 = PropertiesFactory.getProperties("Arial", "Red", 12);
        

        assertSame(props1, props2);
    }
    
    @Test
    public void testPropertiesFactory_DifferentPropertiesAreDistinct() {
        CharacterProperties props1 = PropertiesFactory.getProperties("Arial", "Red", 12);
        CharacterProperties props2 = PropertiesFactory.getProperties("Calibri", "Blue", 14);
        

        assertNotSame(props1, props2);
    }
    
    @Test
    public void testCharacter_Creation() {
        CharacterProperties props = PropertiesFactory.getProperties("Arial", "Red", 12);
        flyweight.Character character = new flyweight.Character('H', props);
        
        assertEquals('H', character.getCharacter());
        assertSame(props, character.getProperties());
    }
    
    @Test
    public void testDocument_AddCharacter() {
        Document doc = new Document("TestDoc");
        doc.addCharacter('A', "Arial", "Red", 12);
        
        assertEquals(1, doc.getCharacterCount());
        assertEquals("A", doc.getText());
    }
    
    @Test
    public void testDocument_AddMultipleCharacters() {
        Document doc = new Document("TestDoc");
        doc.addCharacter('H', "Arial", "Red", 12);
        doc.addCharacter('i', "Arial", "Red", 12);
        
        assertEquals(2, doc.getCharacterCount());
        assertEquals("Hi", doc.getText());
    }
    
    @Test
    public void testDocument_EditCharacter() {
        Document doc = new Document("TestDoc");
        doc.addCharacter('H', "Arial", "Red", 12);
        doc.editCharacter(0, 'W', "Calibri", "Blue", 14);
        
        assertEquals("W", doc.getText());
        flyweight.Character edited = doc.getCharacterAt(0);
        assertEquals('W', edited.getCharacter());
        assertEquals("Calibri", edited.getProperties().getFont());
    }
    
    @Test
    public void testDocument_SaveAndLoad() {
        String filename = "test_document.doc";
        

        Document original = new Document("TestDoc");
        original.addCharacter('T', "Arial", "Red", 12);
        original.addCharacter('e', "Arial", "Red", 12);
        original.addCharacter('s', "Arial", "Red", 12);
        original.addCharacter('t', "Arial", "Red", 12);
        original.save(filename);
        

        Document loaded = Document.load(filename);
        
        assertNotNull(loaded);
        assertEquals("Test", loaded.getText());
        assertEquals(4, loaded.getCharacterCount());
        

        new File(filename).delete();
    }
    
    @Test
    public void testDocument_MultiplePropertiesSharing() {
        Document doc = new Document("TestDoc");
        

        doc.addCharacter('H', "Arial", "Red", 12);
        doc.addCharacter('e', "Arial", "Red", 12);
        doc.addCharacter('l', "Arial", "Red", 12);
        doc.addCharacter('l', "Arial", "Red", 12);
        doc.addCharacter('o', "Arial", "Red", 12);
        

        CharacterProperties props1 = doc.getCharacterAt(0).getProperties();
        CharacterProperties props2 = doc.getCharacterAt(1).getProperties();
        CharacterProperties props3 = doc.getCharacterAt(2).getProperties();
        
        assertSame(props1, props2);
        assertSame(props2, props3);
    }
    
    @Test
    public void testCharacterProperties_Equality() {
        CharacterProperties props1 = new CharacterProperties("Arial", "Red", 12);
        CharacterProperties props2 = new CharacterProperties("Arial", "Red", 12);
        CharacterProperties props3 = new CharacterProperties("Calibri", "Blue", 14);
        
        assertEquals(props1, props2);
        assertNotEquals(props1, props3);
    }
}
