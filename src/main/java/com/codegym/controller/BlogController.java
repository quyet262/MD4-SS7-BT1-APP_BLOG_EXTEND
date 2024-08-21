package com.codegym.controller;


import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired

    private ICategoryService categoryService;

    @ModelAttribute("categorys")
    public Iterable<Category> listCategorys() {
        return categoryService.findAll();
    }



    @GetMapping("")
    public ModelAndView findAll(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5,Sort.by("date").descending()
                .and(Sort.by("title").ascending()));
        ModelAndView modelAndView = new ModelAndView("/blogs/index");
        Page<Blog> blogs = blogService.findAll(pageable);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }


    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") Optional<String> search,
                               @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5,Sort.by("date").descending()
                .and(Sort.by("title").ascending()));
        Page<Blog> blogs;
        if (search.isPresent()) {
            blogs = blogService.findAllByTitleContaining(pageable, search.get());
        } else{
            blogs = blogService.findAll(pageable);
    }

    ModelAndView modelAndView = new ModelAndView("/blogs/index");
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("search", search.orElse(""));
        return modelAndView;
}
@GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/blogs/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
}
@PostMapping("/create")
    public String create(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
}
@GetMapping("/{id}/edit")
    public ModelAndView editForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blogs/update");
            modelAndView.addObject("blog",blog.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error_404");
        }

}
@PostMapping("/update")
    public String update(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
}

@GetMapping("/{id}/delete")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blogs/delete");
            modelAndView.addObject("blog",blog.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error_404");
        }
}
@PostMapping("/delete")
    public String delete(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/blogs";
}
@GetMapping("/{id}/view")
    public ModelAndView viewForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blogs/view");
        modelAndView.addObject("blog",blog.get());
        return modelAndView;
}



}
