package exercise.repository;

import exercise.model.Comment;
import exercise.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findAllByPostId(Long postId);

    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);

    Optional<Post> findByPostId(Long postId);

    boolean existsByIdAndPostId(Long commentId, Long postId);

    // END
}
