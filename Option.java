class Option {

    private int id;
    private String name; // options haru
    private boolean isCorrect; // option correct wala -T/F?

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Option() {
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