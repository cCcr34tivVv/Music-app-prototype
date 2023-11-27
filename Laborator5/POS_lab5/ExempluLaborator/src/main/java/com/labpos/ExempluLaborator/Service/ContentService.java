package com.labpos.ExempluLaborator.Service;

import com.labpos.ExempluLaborator.Entity.Content;
import com.labpos.ExempluLaborator.Repository.IContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContentService implements IContentService{
    @Autowired
    private IContentRepository contentRepository;

    @Override
    public Content getContentById(int id) {
        return contentRepository.getById(id);
    }

    @Override
    public List<Content> getByAll(String name, String genre, int year, String type) throws Exception {
        List<Content> contents = contentRepository.getByAll(name,genre,year,type);
        if (contents.isEmpty())
            throw new Exception();
        return contents;
    }

    @Override
    public List<Content> getAllContents(Integer page, Integer items_per_page, String name, String match, String genre, Integer year, String type) {
        List<Content> contents = null;
        List<Content> contents2 = null;
        boolean filtered = false;

        if(name.compareTo("") != 0){
            //trebuie sa filtrez dupa nume
            if (match.compareTo("") == 0) {
                contents = contentRepository.getByNamePartial(name);
            }
            else {
                contents = contentRepository.getByNameStrict(name);
            }
            filtered = true;
        }

        if(genre.compareTo("") != 0){
            contents2 = contentRepository.getByGenre(genre);
            if(filtered){
                contents.retainAll(contents2);
            }
            else{
                contents = contents2;
                filtered = true;
            }
        }

        if(year != 0){
            contents2 = contentRepository.getByYear(year);
            if(filtered){
                contents.retainAll(contents2);
            }
            else{
                contents = contents2;
                filtered = true;
            }
        }

        if(type.compareTo("") != 0){
            contents2 = contentRepository.getByType(type);
            if(filtered){
                contents.retainAll(contents2);
            }
            else{
                contents = contents2;
                filtered = true;
            }
        }

        if(!filtered){
            contents = contentRepository.getAll();
        }

        int nrPages = contents.size() / items_per_page;
        if (contents.size() % items_per_page != 0)
            nrPages++;

        if (page > 0) {
            if (page > nrPages || page == nrPages)
                return contents.subList((nrPages - 1) * items_per_page, contents.size());
            return contents.subList((page - 1) * items_per_page, page * items_per_page);
        }
        return contents;
    }

    @Override
    public Content updateContent(int id, String name, String genre, Integer year, String type) {
        if(name.compareTo("") != 0){
            contentRepository.updateName(name, id);
        }

        if(genre.compareTo("") != 0){
            contentRepository.updateGenre(genre, id);
        }

        if(year > 0){
            contentRepository.updateYear(year, id);
        }

        Content content = contentRepository.getById(id);

        return content;
    }
}
