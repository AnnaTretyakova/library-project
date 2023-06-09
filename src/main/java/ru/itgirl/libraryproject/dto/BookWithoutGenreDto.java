package ru.itgirl.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class BookWithoutGenreDto {
    private Long id;
    private String name;
    private List<AuthorWithoutBooksDto> authors;
}
