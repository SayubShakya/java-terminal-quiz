package util.converter.impl;

import model.Question;
import util.converter.FileJacksonJsonWriter;
import util.converter.JavaObjectConverterUtil;

import java.util.List;

public class JavaObjectWriterUtilQuestionJacksonImplFileJackson extends FileJacksonJsonWriter<List<Question>> implements JavaObjectConverterUtil<List<Question>> {

    @Override
    public void serialize(List<Question> object, String fileName) {
        try {
            super.serialize(object, fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public List<Question> deserialize(String fileName) {
        try {
            return super.deserialize(fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }
}

