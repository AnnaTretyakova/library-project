package ru.itgirl.libraryproject.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDto {
    @NotNull(message = "Необходимо указать id")
    @Positive(message = "Значение должно быть больше нуля")
    @Min(value = 2, message = "Книгу с id = 1 нельзя проапдейтить")
    private Long id;
    @NotBlank(message = "Необходимо указать имя")
    @Size(max = 16, message = "Длина имени не должна превышать 16-ти символов")
    private String name;
    @NotBlank(message = "Необходимо указать жанр")
    @Size(min = 2, max = 15)
    private String genre;
}
