package edu.tennis.score.homasapienss.DTO.MatchDefinition;

public enum Points {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7");

    private final String label;

    Points(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
