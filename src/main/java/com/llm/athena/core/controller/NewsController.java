package com.llm.athena.core.controller;

import com.llm.athena.core.entity.News;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.NewsCreateRequestDto;
import com.llm.athena.core.http.request.NewsUpdateRequestDto;
import com.llm.athena.core.http.response.NewsResponseDto;
import com.llm.athena.core.policy.NewsPolicy;
import com.llm.athena.core.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish/news")
public class NewsController {

    private final NewsPolicy policy;
    private final NewsService service;

    public NewsController(
            NewsPolicy policy,
            NewsService service
    ) {
        this.policy = policy;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<NewsResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User authUser
    )
    {
        if (!policy.getAll(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<NewsResponseDto> response = service.getAll(pageable).map(NewsResponseDto::new);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        News news = service.getById(id);
        if (!policy.getById(authUser, news)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        NewsResponseDto response = new NewsResponseDto(news);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewsCreateRequestDto dto, @AuthenticationPrincipal User authUser)
    {
        if (!policy.create(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
        service.create(dto, authUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody NewsUpdateRequestDto dto, @AuthenticationPrincipal User authUser){
        News news = service.getById(id);
        if (!policy.update(authUser, news)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        News news = service.getById(id);
        if (!policy.delete(authUser, news)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
