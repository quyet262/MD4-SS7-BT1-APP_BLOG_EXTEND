package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Page<Blog> findAll(Pageable pageable);

    Iterable<Blog> findByCategory(Category category);

    Page<Blog> findAllByTitleContaining(Pageable pageable, String title);


}
