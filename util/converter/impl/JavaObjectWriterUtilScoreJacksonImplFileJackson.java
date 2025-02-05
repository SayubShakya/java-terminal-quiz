package util.converter.impl;

import model.Score;
import util.converter.FileJacksonJsonWriter;
import util.converter.JavaObjectConverterUtil;

import java.util.List;

public class JavaObjectWriterUtilScoreJacksonImplFileJackson extends FileJacksonJsonWriter<List<Score>> implements JavaObjectConverterUtil<List<Score>> {

    @Override
    public void serialize(List<Score> object, String fileName) {
        try {
            super.serialize(object, fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public List<Score> deserialize(String fileName) {
        try {
            return super.deserialize(fileName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }
}