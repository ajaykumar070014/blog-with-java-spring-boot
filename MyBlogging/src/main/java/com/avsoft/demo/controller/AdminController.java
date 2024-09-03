package com.avsoft.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avsoft.demo.entity.Blog;
import com.avsoft.demo.service.BlogService;

@Controller

@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BlogService blogService;

	@GetMapping("/blogs")
	public String listBlogs(Model model) {

		List<Blog> blogs = blogService.getAllBlogs();
		model.addAttribute("blogs", blogs);
		return "blogs";
	}

	@GetMapping("/blogs/edit/{id}")
	public String editBlogForm(@PathVariable Long id, Model model) {
		Blog blog = blogService.getBlogById(id);
		if (blog != null) {
			model.addAttribute("blog", blog);
			return "edit-blogs";
		}
		return "redirect:/blogs";
	}

	@PostMapping("/blogs/update/{id}")
	public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
		blog.setId(id);
		blogService.saveBlog(blog);
		return "redirect:/admin/blogs";
	}

	@GetMapping("/blogs/delete/{id}")
	public String deleteBlog(@PathVariable Long id) {
		blogService.deleteBlogById(id);
		return "redirect:/admin/blogs";
	}

	@GetMapping("/blogs/create")
	public String createBlogForm(Model model) {
		model.addAttribute("blog", new Blog());
		return "create-blog";
	}

	@PostMapping("/blogs/save")
	public String saveBlog(@ModelAttribute Blog blog, Model model) {
		blogService.saveBlog(blog);

		model.addAttribute("blog", new Blog());
		return "create-blog";
	}


}
