package ewm.comment.storage;

import ewm.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentStorageImpl implements CommentStorage {
    private final CommentJpaRepository storage;

    @Override
    public CommentEntity save(CommentEntity domain) {
        return storage.save(domain);
    }

    @Override
    public void delete(Long id) {
        storage.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return storage.existsById(id);
    }

    @Override
    public Optional<CommentEntity> getById(Long id) {
        return storage.findById(id);
    }

    @Override
    public List<CommentEntity> getAllCommentsByEventId(Long eventId) {
        return storage.findAllByEventId(eventId);

    }
}
