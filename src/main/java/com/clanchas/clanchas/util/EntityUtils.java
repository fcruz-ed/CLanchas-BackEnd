package com.clanchas.clanchas.util;

import org.springframework.orm.ObjectRetrievalFailureException;
import com.clanchas.clanchas.model.BaseClass;

import java.util.List;

public abstract class EntityUtils {
    /**
     * Look up the entity of the given class with the given id in the given collection.
     *
     * @param entities      the list to search
     * @param entityClass   the entity class to look up
     * @param id            the entity id to look up
     * @param <T>           entity class
     * @return              the found entity
     * @throws ObjectRetrievalFailureException if the entity was not found
     */
    public static <T extends BaseClass> T getById(List<T> entities, Class<T> entityClass, Long id)
            throws ObjectRetrievalFailureException {
        for (T entity: entities) {
            if(entity.getId().equals(id) && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, id);
    }
}
