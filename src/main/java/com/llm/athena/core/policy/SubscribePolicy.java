package com.llm.athena.core.policy;

import com.llm.athena.core.entity.Subscribe;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.entity.enums.JobRole;
import com.llm.athena.core.policy.core.Policy;
import org.springframework.stereotype.Service;

@Service
public class SubscribePolicy implements Policy<Subscribe> {

    @Override
    public boolean before(User user) {
        return user.getRole().equals(JobRole.ADMIN);
    }

    @Override
    public boolean getAll(User user) {
        return true;
    }

    @Override
    public boolean getById(User user, Subscribe entity) {
        return true;
    }

    @Override
    public boolean create(User user) {
        return true;
    }

    @Override
    public boolean update(User user, Subscribe entity) {
        return user.getId().equals(entity.getAuthor().getId());
    }

    @Override
    public boolean delete(User user, Subscribe entity) {
        return user.getId().equals(entity.getAuthor().getId());
    }

    @Override
    public boolean forceDelete(User user) {
        return false;
    }

    @Override
    public boolean restore(User user) {
        return false;
    }
}
