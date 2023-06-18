package ru.itgirl.libraryproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDto {
    @NotBlank(message = "Необходимо указать имя")
    @Size(max = 16, message = "Длина имени не должна превышать 16-ти символов")
    @Pattern(regexp = "[^\"/*]", message = "В имени нельзя использовать такие символы как \",/,*")
    private String name;
    @NotBlank(message = "Необходимо указать жанр")
    @Size(min = 2, max = 15)
    private String genre;
}
