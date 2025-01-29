package Demo;
public class Main2 {

    public static void main(String[] args) {

        QuestionRepository questionRepository = new QuestionRepositoryMemoryImpl(10);

        questionRepository.save(
                new Question(
                        "What is capital of Nepal?",
                        new Option[] {
                                new Option("KTM", true), 
                                new Option("BKT", false), 
                                new Option("PKR", false), 
                                new Option("BRT", false), 
                        }
                )
        );

        questionRepository.save(
            new Question(
                    "What is national animal of Nepal?",
                    new Option[] {
                            new Option("Bear", false), 
                            new Option("Cow", true), 
                            new Option("Deer", false), 
                            new Option("Pig", false), 
                    }
            )
    );

       Question[] questions =  questionRepository.getAll();

       for(int i = 0; i < questions.length; i++) {
         System.out.println(questions[i]);
       }
    }
}