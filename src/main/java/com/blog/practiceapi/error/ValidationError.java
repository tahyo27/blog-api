package com.blog.practiceapi.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationError {

    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;

    public ValidationError(Map<String, String> map) {
        this.title = map.get("title");
        this.content = map.get("content");
    }
}
