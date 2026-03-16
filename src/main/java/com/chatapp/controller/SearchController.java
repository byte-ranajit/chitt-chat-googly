package com.chatapp.controller;

import com.chatapp.model.MessageDocument;
import com.chatapp.repository.MessageSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SearchController {

    private MessageSearchRepository searchRepository;

    @GetMapping("/messages/search")
    public List<MessageDocument> search (@RequestParam String query){
        return searchRepository.findByContentContaining(query);
    }
}
