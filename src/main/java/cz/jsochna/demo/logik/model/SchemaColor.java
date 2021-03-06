package cz.jsochna.demo.logik.model;

public enum SchemaColor implements Color {
    RED,
    GREEN,
    ORANGE,
    BLUE,
    YELLOW,
    PINK,
    SWHITE,
    SBLACK,
    GRAY,
    ;

    public char toChar() {
        return (char) ('A' + ordinal());
    }

    static {
        assert RED.toChar() == 'A';
    }
}
