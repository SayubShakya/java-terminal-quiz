package util.converter.impl;

import model.Option;
import model.Question;
import util.converter.JavaObjectConverterUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// What is capital of Nepal?##KTM,BKT,PKR,##1,
public class JavaObjectConverterUtilListQuestionImpl implements JavaObjectConverterUtil<List<Question>> {

    private static final String FILE_SUFFIX = ".txt";
    private static final String SEPARATOR = "##";
    public static final String OPTION_ANSWER_SEPARATOR = ",";


    @Override
    public void serialize(List<Question> object, String fileName) {
        File file = new File(fileName + FILE_SUFFIX);
        try {
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File created");
                } else {
                    System.out.println("File not created");
                }
            }

            FileWriter fw = new FileWriter(fileName + FILE_SUFFIX);

            for (Question question : object) {
                String line = question.getQuestionText();
                line += SEPARATOR;

                String answers = "";
                for (Option option : question.getOptions()) {
                    line += option.getName();
                    line += OPTION_ANSWER_SEPARATOR;

                    if (option.isCorrect()) {
                        answers += String.valueOf(option.getId());
                        answers += OPTION_ANSWER_SEPARATOR;
                    }
                }
                line += SEPARATOR;
                line += answers;
                line += "\n";
                fw.write(line);
            }

            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Question> deserialize(String fileName) {
        List<Question> questions = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(fileName + FILE_SUFFIX));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splittedLine = line.split(SEPARATOR);
                String options = splittedLine[1];
                String answers = splittedLine[2];

                Question question = new Question();

                question.setQuestionText(splittedLine[0]);

                String[] optionSplit = options.split(OPTION_ANSWER_SEPARATOR);

                String[] answerSplit = answers.split(OPTION_ANSWER_SEPARATOR);

                int optionId = 1;
                List<Option> optionsArray = new ArrayList<>();
                for(String optionString : optionSplit) {
                    Option option = new Option();
                    option.setId(optionId++);
                    option.setName(optionString);
                    optionsArray.add(option);

                    for (String answerString: answerSplit){
                        if(Integer.parseInt(answerString) == option.getId()) {
                            option.setCorrect(true);
                        }
                    }
                }

                question.setOptions(optionsArray);
                questions.add(question);
            }

            sc.close();

            return questions;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
