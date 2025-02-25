package com.llm.athena.core.policy.core;

import com.llm.athena.core.entity.User;

public interface Policy<T> {
    boolean before(User user);

    boolean getAll(User user);

    boolean getById(User user, T entity);

    boolean create(User user);

    boolean update(User user, T entity);

    boolean delete(User user, T entity);

    boolean forceDelete(User user);

    boolean restore(User user);
}
