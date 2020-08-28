package cz.jsochna.demo.logik.model;

public class CharacterColor implements Color {

    final char character;

    public CharacterColor(char character) {
        this.character = character;
    }

    @Override
    public char toChar() {
        return character;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
