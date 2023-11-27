package com.labpos.ExempluLaborator.Controller;

import com.labpos.ExempluLaborator.Entity.Content;
import com.labpos.ExempluLaborator.Service.IContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songcollection/songs")
public class ContentController {
    @Autowired
    private IContentService contentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Content> getContentById(@PathVariable int id){
        Content response = contentService.getContentById(id);
        if(response != null)
        {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Content>> getContentByNamePartial(@RequestParam Optional<Integer>page, @RequestParam Optional<Integer>items_per_page, @RequestParam Optional<String>name, @RequestParam Optional<String>match, @RequestParam Optional<String>genre, @RequestParam Optional<Integer>year, @RequestParam Optional<String>type){
        Integer page_smt = page.orElse(0);
        Integer items_per_page_smt = items_per_page.orElse(0);
        String name_content = name.orElse("");
        String match_content = match.orElse("");
        String genre_smt = genre.orElse("");
        Integer year_smt = year.orElse(0);
        String type_smt = type.orElse("");

        if(items_per_page_smt == 0)
            items_per_page_smt = 6;

        List<Content> contents = contentService.getAllContents(page_smt, items_per_page_smt, name_content, match_content, genre_smt, year_smt, type_smt);

        if(contents != null)
        {
            return ResponseEntity.ok(contents);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.POST)
    public ResponseEntity<Content> updateContent(@PathVariable int id, @RequestParam Optional<String>name, @RequestParam Optional<String>genre, @RequestParam Optional<Integer>year, @RequestParam Optional<String>type){
        String name_content = name.orElse("");
        String genre_smt = genre.orElse("");
        Integer year_smt = year.orElse(0);
        String type_smt = type.orElse("");

        Content content = contentService.updateContent(id,name_content,genre_smt,year_smt,type_smt);

        if(content != null)
        {
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.notFound().build();
    }

}
