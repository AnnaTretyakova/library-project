package ru.itgirl.libraryproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.GenreDto;
import ru.itgirl.libraryproject.service.AuthorService;
import ru.itgirl.libraryproject.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    //По этому url нужно получать название жанра и список всех книг со всеми авторами у этого жанра
    @GetMapping("/genre/{id}")
    GenreDto getGenreAndAllRelatedBooksByIdOfTheGenre(@PathVariable("id") Long id){
        return genreService.getGenreById(id);
    }

}
