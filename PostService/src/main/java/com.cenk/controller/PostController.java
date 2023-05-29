package com.cenk.controller;

import com.cenk.dto.request.GetPostRequestDto;
import com.cenk.dto.response.GetPostResponseDto;
import com.cenk.repository.entity.Post;
import com.cenk.repository.entity.PostResim;
import com.cenk.service.PostResimService;
import com.cenk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostResimService postResimService;

    @PostMapping("/getposts")
    @CrossOrigin("*")
    public ResponseEntity<List<GetPostResponseDto>> getPost(@RequestBody @Valid GetPostRequestDto dto){
        return ResponseEntity.ok(postService.getPosts(dto));
    }

    @CrossOrigin("*")
    @PostMapping("/newpost")
    public ResponseEntity<Void> newPost(String aciklama,String userid,String url,String url2){
        Post post = new Post();
        post.setAciklama(aciklama);
        post.setUserid(userid);
        post.setPaylasimzamani(System.currentTimeMillis());
        postService.save(post);
        /**
         * Post kayıt işleminden sonra post un id bilgisi atanmış olur.
         */
        PostResim postResim = new PostResim();
        postResim.setPostid(post.getId());
        postResim.setUrl(url);
        postResimService.save(postResim);
        postResim = new PostResim();
        postResim.setPostid(post.getId());
        postResim.setUrl(url2);
        postResimService.save(postResim);
        return ResponseEntity.ok().build();
    }
}
