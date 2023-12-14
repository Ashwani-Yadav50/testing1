package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	PostRepository postrepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);

		Post newPost= new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return postrepository.save(newPost)
		
//		return null;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post =findPostById(postId);
		User user = userService.findUserById(userId);

		if(post.getUser().getId() != user.getId()) {
			throw new Exception("you can't delete another user's post");
		}
	
		postrepository.delete(post);
		return "post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return postrepository.findPostsByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt= postrepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("post not found with id"+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postrepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		
		Post post= findPostById(postId);
		User user =userService.findUserById(userId);
		
		if(user.getSavedPost().add(post)) {
			user.getSavedPost().remove(post);
		} 
		else user.getSavedPost().add(post);
		userRepository.save(user);
		
		return post;
		
		
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post= findPostById(postId);
		User user =userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		
		post.getLiked().add(user);
		return postrepository.save(post);
	}

}
