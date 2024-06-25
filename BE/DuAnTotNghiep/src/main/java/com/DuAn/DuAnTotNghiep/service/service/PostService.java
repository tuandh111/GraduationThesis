package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Post;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.PostRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface PostService {

    Post findPostById(int postId);

    List<Post> findAllPost();

    List<Post> findAllPostExceptDeleted();

    Post savePost(PostRequest postRequest);

    Post updatePost(int postId, PostRequest postRequest);

    MessageResponse deletePost(int postId);

    MessageResponse softDeletePost(int postId);
}
