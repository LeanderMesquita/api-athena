package com.llm.athena.core.policy;

import com.llm.athena.core.entity.News;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.entity.enums.JobRole;
import com.llm.athena.core.policy.core.Policy;
import org.springframework.stereotype.Service;

@Service
public class NewsPolicy implements Policy<News> {

    @Override
    public boolean before(User user) {
        return user.getRole().equals(JobRole.ADMIN);
    }

    @Override
    public boolean getAll(User user) {
        return true;
    }

    @Override
    public boolean getById(User user, News entity) {
        return true;
    }

    @Override
    public boolean create(User user) {
        return user.getRole().equals(JobRole.COORDINATOR)
                || user.getRole().equals(JobRole.DIRECTOR);
    }

    @Override
    public boolean update(User user, News entity) {
        return user.getRole().equals(JobRole.COORDINATOR)
                || user.getRole().equals(JobRole.DIRECTOR);
    }

    @Override
    public boolean delete(User user, News entity) {
        return user.getRole().equals(JobRole.COORDINATOR)
                || user.getRole().equals(JobRole.DIRECTOR);
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
