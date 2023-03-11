package pidev.tn.aurora.entities.enumeration;

public enum Note {
    EXCELLENT(4),
    GOOD(3),
    AVERAGE(2),
    MEDIOCRE(1);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}