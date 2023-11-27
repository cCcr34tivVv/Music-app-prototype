package com.labpos.ExempluLaborator.Service;

import com.labpos.ExempluLaborator.Entity.Content;

import java.util.List;

public interface IContentService {
    Content getContentById(int id);

    List<Content> getByAll(String name, String genre, int year, String type) throws Exception;

    List<Content> getAllContents(Integer page, Integer items_per_page, String name, String match, String genre, Integer year, String type);

    Content updateContent(int id, String name, String genre, Integer year, String type);
}
