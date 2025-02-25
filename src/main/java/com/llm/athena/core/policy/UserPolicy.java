package com.llm.athena.core.policy;

import com.llm.athena.core.entity.Admin;
import com.llm.athena.core.entity.BaseUser;
import com.llm.athena.core.entity.User;

import com.llm.athena.core.entity.enums.JobRole;
import com.llm.athena.core.policy.core.Policy;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserPolicy implements Policy<User> {


    @Override
    public boolean before(BaseUser user) {
        return user instanceof Admin;
    }

    @Override
    public boolean getAll(BaseUser user) {
        return true;
    }

    @Override
    public boolean getById(BaseUser user, User entity) {
        return entity.getRole().equals(JobRole.DIRECTOR) ||
                entity.getRole().equals(JobRole.COORDINATOR);
    }

    @Override
    public boolean create(BaseUser user) {
        return true;
    }

    @Override
    public boolean update(BaseUser user, User entity) {
        return Objects.equals(user.getId(), entity.getId());
    }

    @Override
    public boolean delete(BaseUser user, User entity) {
        return Objects.equals(user.getId(), entity.getId());
    }

    @Override
    public boolean forceDelete(BaseUser user) {
        return false;
    }

    @Override
    public boolean restore(BaseUser user) {
        return false;
    }
}
