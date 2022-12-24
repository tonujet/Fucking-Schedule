package com.example.rozklad.services;

import com.example.rozklad.dao.ImageDataRepo;
import com.example.rozklad.entities.ImageData;
import com.example.rozklad.entities.News;
import com.example.rozklad.utils.ImageUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageDataService {

    private final ImageDataRepo imageDataRepo;
    private final DbaService dbaService;
    private final String itemName;
    private final NewsService newsService;

    public ImageDataService(ImageDataRepo imageDataRepo,
                            DbaService dbaService,
                            @Value("Image") String itemName,
                            NewsService newsService) {
        this.imageDataRepo = imageDataRepo;
        this.dbaService = dbaService;
        this.itemName = itemName;
        this.newsService = newsService;
    }

    public byte[] getImage(long id) {
        ImageData imageData = dbaService.get(imageDataRepo, itemName, id);
        return ImageUtils.decompressImage(imageData.getImage());
    }

    @SneakyThrows
    public void uploadImage(MultipartFile file, long newsId) {
        imageDataRepo.save(ImageData.builder()
                .image(ImageUtils.compressImage(file.getBytes()))
                .news(newsService.getNews(newsId))
                .build()
        );
    }

    @SneakyThrows
    public void updateImage(MultipartFile file, long newsId) {
        News news = newsService.getNews(newsId);
        imageDataRepo.save(ImageData.builder()
                .id(news.getImages().get(0).getId())
                .image(ImageUtils.compressImage(file.getBytes()))
                .news(news)
                .build()
        );
    }
}
