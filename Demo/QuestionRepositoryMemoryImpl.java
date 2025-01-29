package Demo;
public class QuestionRepositoryMemoryImpl implements QuestionRepository{

    private Question[] questions = null;
    private int currentIndex = 0;

    public QuestionRepositoryMemoryImpl(int initialCapacity) {
        questions = new Question[initialCapacity];
    }



    
    @Override
    public Question[] getAll() {
       return questions;
    }

    @Override
    public Question getById(Integer id) {
      for(int i=0; i<questions.length; i++){
        if(questions[i].getId() == id){
            return questions[i];
        }
      }
      System.out.println("Question with id " + id + " not found");
      return null;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean save(Question question) {
        try{
            questions[currentIndex] = question;
            currentIndex++;
        }catch(Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
            return false;
        }
        return true;
    }

}