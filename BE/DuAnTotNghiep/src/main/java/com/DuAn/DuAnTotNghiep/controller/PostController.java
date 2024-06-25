package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentCTResult;
import com.DuAn.DuAnTotNghiep.entities.Post;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentCTResultRequest;
import com.DuAn.DuAnTotNghiep.model.request.PostRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentCTResultService;
import com.DuAn.DuAnTotNghiep.service.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping("post")
    @Operation(summary = "List post")
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.findAllPost());
    }

    @GetMapping("post-except-deleted")
    @Operation(summary = "List post except deleted")
    public ResponseEntity<List<Post>> getAllPostExceptDeleted() {
        return ResponseEntity.ok(postService.findAllPostExceptDeleted());
    }

    @GetMapping("post-id/{Id}")
    @Operation(summary = "dental post Id")
    public ResponseEntity<Post> getPostId( @PathVariable Integer Id) {
        return ResponseEntity.ok(postService.findPostById(Id));
    }
    @PostMapping("post")
    @Operation(summary = "save post")
    public ResponseEntity<Post> savePost(@Valid @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.savePost(postRequest));
    }
    @PutMapping("post/{Id}")
    @Operation(summary = "update post")
    public ResponseEntity<Post> updatePost(@PathVariable int Id, @Valid @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.updatePost(Id, postRequest));
    }

    @DeleteMapping("post/{Id}")
    @Operation(summary = "delete post")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable int Id){
        return ResponseEntity.ok(postService.deletePost(Id));
    }

    @DeleteMapping("soft-delete-post/{Id}")
    @Operation(summary = "delete soft post")
    public ResponseEntity<MessageResponse> softDeletePost(@PathVariable int Id){
        return ResponseEntity.ok(postService.softDeletePost(Id));
    }
}
