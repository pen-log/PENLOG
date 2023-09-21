package backend.domain.subComment.service;

import backend.domain.comment.Comment;
import backend.domain.member.Member;
import backend.domain.subComment.SubComment;
import backend.domain.subComment.dto.SubCommentCreateRequest;
import backend.domain.subComment.dto.SubCommentUpdateRequest;
import backend.domain.subComment.repository.SubCommentRepository;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static backend.global.exception.ExceptionCode.SUB_COMMENT_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class SubCommentService {

    private final SubCommentRepository subCommentRepository;

    public SubComment findById(Long id) {
        Optional<SubComment> opSubComment = subCommentRepository.findById(id);

        if (opSubComment.isEmpty()) {
            throw new BadRequestException(SUB_COMMENT_NOT_FOUND);
        }

        return opSubComment.get();
    }

    public List<SubComment> findByComment(Comment comment) {
        return subCommentRepository.findByComment(comment);
    }

    public SubComment create(Member member, Comment comment, SubCommentCreateRequest request) {
        return subCommentRepository.save(new SubComment(member, comment, request));
    }

    public SubComment update(SubComment subComment, SubCommentUpdateRequest request) {
        return subCommentRepository.save(subComment.update(request));
    }

    public void delete(SubComment subComment) {
        subCommentRepository.delete(subComment);
    }

    public void deleteById(Long id) {
        subCommentRepository.deleteById(id);
    }

}
