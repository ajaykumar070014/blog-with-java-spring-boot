package com.avsoft.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avsoft.demo.entity.Blog;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByOrderByCreateAtDesc();

    List<Blog> findAllByOrderByUpdateAtDesc();
}
