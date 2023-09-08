package backend.domain.comment.service;

import backend.domain.comment.Comment;
import backend.domain.comment.dto.CommentCreateRequest;
import backend.domain.comment.dto.CommentUpdateRequest;
import backend.domain.comment.repository.CommentRepository;
import backend.domain.member.Member;
import backend.domain.post.Post;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static backend.global.exception.ExceptionCode.COMMENT_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        Optional<Comment> opComment = commentRepository.findById(id);

        if (opComment.isEmpty()) {
            throw new BadRequestException(COMMENT_NOT_FOUND);
        }

        return opComment.get();
    }

    @Transactional(readOnly = true)
    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment create(Member member, Post post, CommentCreateRequest request) {
        return commentRepository.save(new Comment(member, post, request));
    }

    public Comment update(Comment comment, CommentUpdateRequest request) {
        return commentRepository.save(comment.update(request));
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

}
