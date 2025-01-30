public class Main {
    public static void main(String[] args) {
        Quizable quizable = new QuizImpl(new QuizRepositoryMemoryImpl());
        quizable.menu();
    }
}