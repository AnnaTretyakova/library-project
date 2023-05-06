package ru.itgirl.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.libraryproject.model.Book;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {
    private Long id;
    private String name;
    private String genre;
    private List<AuthorDto> authors;

    /*
    //I wrote this code to understand how builder looks like
    public static BookDtoBuilder builder(){
        return new BookDtoBuilder();
    }

    public static class BookDtoBuilder{
        private Long id;
        private String name;
        private String genre;

        private List<AuthorDto> authors;

        BookDtoBuilder(){}

        public BookDtoBuilder id(final Long id){
            this.id = id;
            return this;
        }

        public BookDtoBuilder name(final String name){
            this.name = name;
            return this;
        }

        public BookDtoBuilder genre(final String genre){
            this.genre = genre;
            return this;
        }

        public BookDtoBuilder authors(final List<AuthorDto> authors){
            this.authors = authors;
            return this;
        }

        public BookDto build(){
            return new BookDto(this.id, this.name, this.genre, this.authors);
        }

        public String toString(){
            return "BookDto.BookDtoBuilder(id = " + this.id + ", name = " + this.name + ", genre = " + this.genre + ", authors = " + this.authors +")";
        }
    }*/

}
