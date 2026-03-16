package com.chatapp.repository;

import com.chatapp.model.MessageDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageSearchRepository extends ElasticsearchRepository<MessageDocument, String> {
    List<MessageDocument> findByContentContaining(String text);
}
