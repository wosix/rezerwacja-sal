package repository;

import java.util.List;

public interface IRepository<T, ID> {
    void dodajNowy(T item);
    void usunPoId(ID id);
    List<T> zwrocWszystkie();
    T zwrocJedenPoId(ID id);
    void edytujPoId(ID id, T item);
}