package com.sayub.repository;

import com.sayub.model.Option;

import java.util.List;

public interface OptionRepository {

    boolean saveAll(List<Option> options);
}
