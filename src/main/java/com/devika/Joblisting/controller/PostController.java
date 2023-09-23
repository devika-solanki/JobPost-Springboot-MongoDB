package com.devika.Joblisting.controller;

import com.devika.Joblisting.repository.PostRepository;
import com.devika.Joblisting.model.Post;
import com.devika.Joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController
{

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
//////////////////////////////////// get all posts /////////////////////////////////////////////
    @GetMapping("/allPosts")
    @CrossOrigin
    public ResponseEntity<?> getAllPosts()
    {
        List<Post> post = repo.findAll();
        if(!post.isEmpty()){
            return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No posts available",HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/allPosts")
//    @CrossOrigin
//    public List<Post> getAllPosts()
//    {
//        return repo.findAll();
//    }
//////////////////////////////// get particular post by text ////////////////////////////////////
    // posts/java
    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text)
{
    return srepo.findByText(text);
}

////////////////////////////////////// add a post //////////////////////////////////////
    @PostMapping("/post")
    @CrossOrigin
    public ResponseEntity<?> addPost(@RequestBody Post post)
    {
        try {
            repo.save(post);
            return new ResponseEntity<Post>(post,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//////////////////////////////////// update a post ////////////////////////////////////
    @PutMapping("/update/{id}")
    @CrossOrigin
    public ResponseEntity<?> updatePost(@PathVariable("id") String id, @RequestBody  Post post)
    {

        Optional<Post> postOptional = repo.findById(id);
        if(postOptional.isPresent()) {
            Post postToSave = postOptional.get();
            postToSave.setProfile(post.getProfile() != null ? post.getProfile() : postToSave.getProfile());
            postToSave.setDesc(post.getDesc() != null ? post.getDesc() : postToSave.getDesc());
            postToSave.setExp(post.getExp() != 0 ? post.getExp() : postToSave.getExp());
            postToSave.setTechs(post.getTechs() != null ? post.getTechs() : postToSave.getTechs());
            repo.save(postToSave);
            return new ResponseEntity<>(postToSave , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Profile not found",HttpStatus.NOT_FOUND);
        }
    }

///////////////////////////////////// delete a post ///////////////////////////////////
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public ResponseEntity<?> deletePost(@PathVariable("id") String id)
    {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        // return srepo.deletebyprofile(id);
    }
}