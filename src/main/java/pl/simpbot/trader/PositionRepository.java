package pl.simpbot.trader;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PositionRepository extends MongoRepository<Position, UUID> {
}
