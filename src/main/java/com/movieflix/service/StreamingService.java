package com.movieflix.service;

import com.movieflix.entity.Streaming;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingService {
    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public Streaming create(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Streaming getById(Long id){
        return streamingRepository.findById(id).orElse(null);
    }

    public boolean deleteById(Long id) {
        if (streamingRepository.existsById(id)) {
            streamingRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
