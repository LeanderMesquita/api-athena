package com.llm.athena.core.service;

import com.llm.athena.core.entity.News;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.NewsCreateRequestDto;
import com.llm.athena.core.http.request.NewsUpdateRequestDto;
import com.llm.athena.core.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;


    public Page<News> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public News getById(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public News getByEmail(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void create(NewsCreateRequestDto dto, User author){
        News news = new News(
                author,
                dto.description(),
                dto.lead(),
                dto.title()
        );
        repository.save(news);
    }

    public void update(String id, NewsUpdateRequestDto dto){
        News news = getById(id);
        news.setTitle(dto.title());
        news.setLead(dto.lead());
        news.setDescription(dto.description());
        repository.save(news);
    }

    public void delete(String id){
        repository.delete(getById(id));
    }

}
