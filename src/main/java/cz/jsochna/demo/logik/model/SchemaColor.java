package cz.jsochna.demo.logik.model;

public enum SchemaColor implements Color {
    RED,
    GREEN,
    ORANGE,
    BLUE,
    YELLOW,
    PINK,
    GRAY
    ;

    char toChar() {
        return (char) ('A' + ordinal());
    }
}
