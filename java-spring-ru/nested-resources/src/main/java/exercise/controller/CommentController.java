package exercise.controller;

import exercise.dto.CommentDto;
import exercise.dto.PostDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getCommentsForPost(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "{postId}/comments/{commentId}")
    public Comment getBook(@PathVariable Long commentId, @PathVariable Long postId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "{postId}/comments")
    public Comment createCommentForPost(@RequestBody CommentDto commentDto,
                                        @PathVariable Long postId) {

        Comment comment = new Comment();
        comment.setContent(commentDto.content());
        comment.setPost(postRepository.findById(postId).get());
        return commentRepository.save(comment);



    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@RequestBody CommentDto commentDto,
                                 @PathVariable Long postId,
                                 @PathVariable Long commentId) {

        if (!commentRepository.existsByIdAndPostId(commentId, postId)) {
            // Если не существует, возвращаем код ответа 404
            throw new ResourceNotFoundException("Comment not found");
        }
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId).get();

        comment.setContent(commentDto.content());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "{postId}/comments/{commentId}")
    public void deleteComment(
            @PathVariable ("postId") Long postId,
            @PathVariable("commentId") Long commentId) {
        if (!commentRepository.existsByIdAndPostId(commentId, postId)) {
            // Если не существует, возвращаем код ответа 404
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }

    // END
}
