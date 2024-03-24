package cmc.msu.webpracjaba.DAO;

import java.util.Collection;

public interface CommonDAO<T, ID> {
    ID getId();

    void setId(ID id);

    T getById(ID id);

    Collection<T> getAll();

    T save(T entity);

    void saveCollection(Collection<T> entities);

    void delete(T entity);

    void deleteById(ID id);

    T update(T entity);
}
