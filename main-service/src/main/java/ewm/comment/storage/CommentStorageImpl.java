package ewm.comment.storage;

import ewm.comment.domain.Comment;
import ewm.comment.entity.CommentEntity;
import ewm.comment.mapper.CommentDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentStorageImpl implements CommentStorage {
    private final CommentJpaRepository storage;
    private final CommentDomainEntity mapper;

    @Override
    public Comment save(Comment domain) {
        CommentEntity entity = storage.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
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
    public Optional<Comment> getById(Long id) {
        Optional<CommentEntity> entity = storage.findById(id);
        return entity.map(mapper::toDomain);
    }

    @Override
    public List<Comment> getAllCommentsByEventId(Long eventId) {
        List<CommentEntity> commentEntities = storage.findAllByEventId(eventId);
        return commentEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
