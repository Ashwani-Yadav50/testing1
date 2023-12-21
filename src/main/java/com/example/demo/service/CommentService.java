package com.example.demo.service;

import com.example.demo.models.Comment;

public interface CommentService {
	
	public  Comment createComment(Comment commnet, Integer postId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception;
	
	

}
