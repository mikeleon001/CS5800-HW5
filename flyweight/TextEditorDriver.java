package flyweight;


public class TextEditorDriver {
    public static void main(String[] args) {

        Document document = new Document("MyDocument");
        
        String text = "HelloWorldCS5800";
        

        for (int i = 0; i < 5; i++) {
            document.addCharacter(text.charAt(i), "Arial", "Red", 12);
        }
        

        for (int i = 5; i < 10; i++) {
            document.addCharacter(text.charAt(i), "Calibri", "Blue", 14);
        }
        

        for (int i = 10; i < 14; i++) {
            document.addCharacter(text.charAt(i), "Verdana", "Black", 16);
        }
        

        for (int i = 14; i < 16; i++) {
            document.addCharacter(text.charAt(i), "Arial", "Blue", 16);
        }
        

        String filename = "HelloWorldCS5800.doc";
        document.save(filename);
        System.out.println("Document saved: " + filename);


        Document loadedDocument = Document.load(filename);
        if (loadedDocument != null) {
            System.out.println("Document loaded: " + loadedDocument.getText());

            System.out.println("\nCharacter Properties (4 variations):");
            for (int i = 0; i< loadedDocument.getCharacterCount(); i++) {
                Character c = loadedDocument.getCharacterAt(i);
                CharacterProperties props = c.getProperties();
                System.out.printf("'%c' - Font: %s, Color: %s, Size: %d%n",
                        c.getCharacter(), props.getFont(), props.getColor(), props.getSize());
            }
        }
    }
}
