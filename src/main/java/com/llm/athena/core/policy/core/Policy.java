package com.llm.athena.core.policy.core;

import com.llm.athena.core.entity.BaseUser;

public interface Policy<T> {
    boolean before(BaseUser user);

    boolean getAll(BaseUser user);

    boolean getById(BaseUser user, T entity);

    boolean create(BaseUser user);

    boolean update(BaseUser user, T entity);

    boolean delete(BaseUser user, T entity);

    boolean forceDelete(BaseUser user);

    boolean restore(BaseUser user);
}
