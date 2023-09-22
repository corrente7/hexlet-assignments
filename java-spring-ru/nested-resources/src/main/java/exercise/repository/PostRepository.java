package exercise.repository;

import exercise.model.Comment;
import exercise.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    boolean existsByTitle(String title);

    Optional<Post> findById(Long postId);
}
