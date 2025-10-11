package com.movieflix.controller;

import com.movieflix.controller.request.StreamingRequest;
import com.movieflix.controller.response.StreamingResponse;
import com.movieflix.entity.Category;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/streaming")
@SecurityRequirement(name = "bearerAuth")
public class StreamingController {
    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @Operation(summary = "Create a streaming")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Streaming was successfully created")
    })
    @PostMapping
    public ResponseEntity<Void> createStreaming(@Valid @RequestBody StreamingRequest streaming) {
        streamingService.create(StreamingMapper.toStreaming(streaming));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all streaming")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of streaming"),
    })
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAllStreaming() {
        List<Streaming> streaming = streamingService.findAll();
        return ResponseEntity.ok(streaming
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @Operation(summary = "Get streaming by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Streaming found"),
            @ApiResponse(responseCode = "404", description = "Streaming not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        Optional<Streaming> streaming = streamingService.getById(id);

        return streaming.map(value ->
                        ResponseEntity.ok(StreamingMapper.toStreamingResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Delete streaming by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Streaming deleted"),
            @ApiResponse(responseCode = "404", description = "Streaming not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id) {
        boolean deleted = streamingService.deleteById(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }

}
