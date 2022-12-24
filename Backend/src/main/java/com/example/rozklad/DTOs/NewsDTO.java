package com.example.rozklad.DTOs;

import com.example.rozklad.entities.News;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private long id;

    @NotBlank(message = "News title is null")
    @Size(min = 2, max = 200, message = "News name title be between 2 and 200 characters")
    private String title;

    @NotBlank(message = "News title is null")
    private String description;

    @NotBlank(message = "News title is null")
    private String text;

    private LocalDateTime dateCreated;

    private long headerImgId;


    public static NewsDTO toDTO(News news, long id) {
        if (news != null) {
            return NewsDTO.builder()
                    .id(news.getId())
                    .title(news.getTitle())
                    .description(news.getDescription())
                    .text(news.getText())
                    .headerImgId(id)
                    .dateCreated(news.getDateCreated())
                    .build();
        }
        return null;
    }
}
