class Option {

    private int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    private String name; // options haru
    public void setName(String name) {
        this.name = name;
    }

    

    private boolean isCorrect; // option correct wala -T/F?

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