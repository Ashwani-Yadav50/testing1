package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Post;
import com.example.demo.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception{
		
		Post createdPost = postService.createNewPost(post, userId);
		
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
		
		String message = postService.deletePost(postId, userId);
		return null;
	}
}


/// 8 lec 10:58