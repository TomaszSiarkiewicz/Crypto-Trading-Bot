package pl.simpbot.trader;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class InMemoryPositionDatabaseImpl implements PositionRepository{
    Map<UUID, Position> positionDatabase = new HashMap<>();
    @Override
    public <S extends Position> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Position> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Position> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Position> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Position> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Position> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Position> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Position> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Position, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Position> S save(S entity) {
        positionDatabase.put(UUID.randomUUID(), entity);
        return entity;
    }

    @Override
    public <S extends Position> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Position> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<Position> findAll() {
        return null;
    }

    @Override
    public List<Position> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Position entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Position> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Position> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Position> findAll(Pageable pageable) {
        return null;
    }

    public Optional<Position> findFirst() {
        return positionDatabase.values().stream().findFirst();
    }
}
