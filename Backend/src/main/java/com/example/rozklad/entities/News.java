package com.example.rozklad.entities;

import com.example.rozklad.DTOs.NewsDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String text;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<ImageData> images;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static News toEntity(NewsDTO dto) {
        return News.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .text(dto.getText())
                .build();
    }
}
