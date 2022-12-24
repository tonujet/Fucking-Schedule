package com.example.rozklad.services;


import com.example.rozklad.DTOs.NewsDTO;
import com.example.rozklad.DTOs.ScheduleDTO;
import com.example.rozklad.dao.NewsRepo;
import com.example.rozklad.entities.News;
import com.example.rozklad.entities.Schedule;
import com.example.rozklad.exceptions.handlers.ItemHandler;
import com.example.rozklad.services.Interfaces.ICRUDService;
import com.example.rozklad.services.Interfaces.IGetAllService;
import com.example.rozklad.services.Interfaces.ISearchService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements
        IGetAllService<NewsDTO>,
        ISearchService<NewsDTO> {

    private final NewsRepo newsRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public NewsService(NewsRepo newsRepo,
                       ItemHandler itemHandler,
                       DbaService dbaService,
                       @Value("news") String itemName) {
        this.newsRepo = newsRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    @SneakyThrows
    public long create(NewsDTO newsDTO) {
        itemHandler.handleIsIdNull(newsDTO.getId(), itemName);
        News news = newsRepo.save(News.toEntity(newsDTO));
        return news.getId();

    }

    @SneakyThrows
    public void update(NewsDTO newsDTO) {
        newsRepo.save(News.toEntity(newsDTO));
    }


    public NewsDTO get(long id) {
        News news = dbaService.get(newsRepo, itemName, id);
        return NewsDTO.toDTO(news, news.getImages().get(0).getId());

    }


    public void delete(long id) {
        dbaService.get(newsRepo, itemName, id);
        newsRepo.deleteById(id);
    }

    @Override
    public List<NewsDTO> getAll(int limit) {
        if (limit == 0) {
            return newsRepo.findAll()
                    .stream()
                    .map(news -> NewsDTO.toDTO(news, news.getImages().get(0).getId()))
                    .toList();
        }
        return newsRepo.findAll(Pageable.ofSize(limit))
                .stream()
                .map(news -> NewsDTO.toDTO(news, news.getImages().get(0).getId()))
                .toList();

    }

    public News getNews(long id){
        return dbaService.get(newsRepo, itemName, id);
    }
    @Override
    public List<NewsDTO> search(String query, int limit) {
        if(limit == 0){
            return listToDTO(newsRepo.findAll(query));
        }
        return listToDTO(newsRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public List<NewsDTO> listToDTO(List<News> list){
        return list.stream()
                .map((news) ->
                        NewsDTO.toDTO(news, news.getImages().get(0).getId()))
                .toList();
    }
}
