package com.example.service.impl;

import com.example.dto.adminDTO.AdminShowAuthorsDTO;
import com.example.dto.authorDTO.AuthorUpdateRequestDTO;
import com.example.dto.entryDTO.PasswordDTO;
import com.example.dto.authorDTO.AuthorRoleResponseDTO;
import com.example.repository.AuthorRepository;
import com.example.repository.BlogSessionRepository;
import com.example.repository.BlogUserPasswordRepository;
import com.example.service.AdminService;
import com.example.utils.AuthorityFinder;
import com.example.utils.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AuthorRepository authorRepository;
    private final BlogSessionRepository sessionRepository;
    private final BlogUserPasswordRepository passwordRepository;

    @Override
    public void promoteToAdmin(Long id, String role) {

        var author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!author.isAdmin()) {
            author.setAdmin(true);
            author.getRole().getPermissions().removeIf(p -> p.toString().equals("users.posts.rw"));
            author.setUpdatedOn(Instant.now());
            authorRepository.save(author);
            var sessions = sessionRepository.findAllByBlogAuthor(author);
            sessionRepository.deleteAll(sessions);
        }
    }

    @Override
    public void demoteToUser(Long id
    ) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (author.isAdmin()) {
            author.setAdmin(false);
            author.getRole().getPermissions().removeIf(r -> r.toString().startsWith("admin"));
            author.setUpdatedOn(Instant.now());
            authorRepository.save(author);
            var sessions = sessionRepository.findAllByBlogAuthor(author);
            sessionRepository.deleteAll(sessions);
        }
    }

    @Override
    public void updateUsersNameOrLastname(AuthorUpdateRequestDTO updateRequestDTO, Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (AuthorityFinder.getAuthority().contains("admin.users.rw")) {
            author.setFirstName(updateRequestDTO.firstname());
            author.setLastName(updateRequestDTO.lastname());
        }
        authorRepository.save(author);
    }


    @Override
    public void resetPassword(Long id, Boolean resetSession, PasswordDTO passwordDTO) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.MULTI_STATUS));
        var password = passwordRepository.findByUser(author);

        if (password == null) {
            throw new ResponseStatusException((HttpStatus.GONE));
        }

        String encPas = BCrypt.hashpw(passwordDTO.password(), password.getSalt());
        password.setPassword(encPas);
        passwordRepository.save(password);

        if (resetSession) {
            var sessions = sessionRepository.findAllByBlogAuthor(author);
            sessionRepository.deleteAll(sessions);
        }

    }

    @Override
    public List<AdminShowAuthorsDTO> showsRegularUsersToAdmin() {
        var authors = authorRepository.findAll();
        if (AuthorityFinder.getAuthority().contains("admin.admins.ro")){
            return DtoConverter.mapToAdminShowsListDto(authors) ;
        }
        return null;
    }

    @Override
    public void showRoles() {

    }

    @Override
    public AuthorRoleResponseDTO grantUserRole() {
        return null;
    }

    @Override
    public AuthorRoleResponseDTO revokeUserRole() {
        return null;
    }

    @Override
    public void showAllRolesAvailable() {

    }
}
