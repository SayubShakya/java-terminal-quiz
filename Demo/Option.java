package Demo;

class Option {
    private String name; // options haru
    private boolean isCorrect; // option correct wala -T/F?

    public Option(String name, boolean isCorrect) {
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return name + " (Correct: " + isCorrect + ")";
    }
}