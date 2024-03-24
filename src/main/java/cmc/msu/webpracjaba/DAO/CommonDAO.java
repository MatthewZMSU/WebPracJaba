package cmc.msu.webpracjaba.DAO;

import cmc.msu.webpracjaba.Common;

import java.util.Collection;

public interface CommonDAO<T extends Common<ID>, ID> {
    T getById(ID id);

    Collection<T> getAll();

    T save(T entity);

    void saveCollection(Collection<T> entities);

    void delete(T entity);

    void deleteById(ID id);

    T update(T entity);
}
