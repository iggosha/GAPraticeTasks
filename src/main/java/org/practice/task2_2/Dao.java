package org.practice.task2_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T getById(long id);
    List<T> getAll();
    List<T> getAll(int selectedPage);
    List<T> getAllWithFilter(String filter);
    void create(T t);
    void update(T oldT, T newT);
    void delete(T t);
}
