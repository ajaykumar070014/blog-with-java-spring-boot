package com.avsoft.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avsoft.demo.entity.Blog;
import com.avsoft.demo.repo.BlogRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Transactional(readOnly = true)
	public List<Blog> getAllBlogs() {
		return blogRepository.findAllByOrderByUpdateAtDesc();
	}

	@Transactional(readOnly = true)
	public Blog getBlogById(Long id) {
		Optional<Blog> optionalBlog = blogRepository.findById(id);
		return optionalBlog.orElse(null);
	}

	@Transactional
	public void saveBlog(Blog blog) {
		if (blog.getId() != null) {
			Blog existingBlog = blogRepository.findById(blog.getId()).orElseThrow(() -> new RuntimeException("Blog not found"));
			blog.setCreateAt(existingBlog.getCreateAt());
			blog.setUpdateAt(LocalDateTime.now());
		} else {
			blog.setCreateAt(LocalDateTime.now());
			blog.setUpdateAt(LocalDateTime.now());
		}
		blogRepository.save(blog);
	}

	@Transactional
	public void deleteBlogById(Long id) {
		blogRepository.deleteById(id);
	}
}
