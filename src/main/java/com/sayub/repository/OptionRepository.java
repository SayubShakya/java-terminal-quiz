package com.sayub.repository;

import com.sayub.model.Option;

import java.util.List;

public interface OptionRepository {

    boolean save(Option option);

    boolean saveAll(List<Option> options);
}
