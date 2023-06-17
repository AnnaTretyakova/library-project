package ru.itgirl.libraryproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorUpdateDto {
    @NotNull(message = "Необходимо указать id")
    private Long id;
    @NotBlank(message = "Необходимо указать имя")
    @Pattern(regexp = "[A-Za-z]", message = "Разрешены только символы из английского алфавита")
    private String name;
    @NotBlank(message = "Необходимо указать фамилию")
    @Pattern(regexp = "[A-Za-z@._äöüß]", message = "Разрешены только символы из английского алфавита, @, ., ä, ö, ü, ß")
    private String surname;
}
