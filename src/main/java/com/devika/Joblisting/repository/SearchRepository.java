package com.devika.Joblisting.repository;

import com.devika.Joblisting.model.Post;

import java.util.List;
import java.util.Optional;

public interface SearchRepository {

    List<Post> findByText(String text);

}
