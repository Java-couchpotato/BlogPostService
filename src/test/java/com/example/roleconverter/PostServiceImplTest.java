package com.example.roleconverter;

import com.example.dto.request.PostCreateRequestDTO;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import com.example.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl service;

    @Mock
    private PostRepository postRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("should throw UPGRADE_REQUIRED,when account status inactive")
    public void shouldThrowUPGRADE_REQUIREDwhenaccountStatusInactive() {

        PostCreateRequestDTO requestDTO = PostCreateRequestDTO.builder()
                .authorId(1L)
                .body("POST BODY1")
                .title("title1")
                .build();

        HttpStatus expectedStatus = HttpStatus.UPGRADE_REQUIRED;
        String expectedMessage = String.format("Blog with ID %d is in status [INACTIVE]",requestDTO.authorId());

        Mockito
                .when(authorRepository.findById(requestDTO.authorId()))
                .thenReturn(Optional.empty());


        ResponseStatusException ex = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> service.create(requestDTO)
        );

        Assertions.assertEquals(expectedStatus,ex.getStatus());
        Assertions.assertEquals(expectedMessage,ex.getReason());
    }


}
