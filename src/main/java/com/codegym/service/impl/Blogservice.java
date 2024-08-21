package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Blogservice implements IBlogService {
    @Autowired
    private IBlogRepository repository;

    @Override
    public Iterable<Blog> findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(Pageable pageable, String title) {
        return repository.findAllByTitleContaining(pageable, title);
    }

    @Override
    public Iterable<Blog> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Blog blog) {
        repository.save(blog);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
    repository.deleteById(id);
    }
}
