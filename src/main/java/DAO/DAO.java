package DAO;

import java.util.List;

public interface DAO<T> {
    void create(T obj);
    T read(int id);
    List<T> readAll();
    void update(T obj);
    boolean delete(int id);
}