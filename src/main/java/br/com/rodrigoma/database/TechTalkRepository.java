package br.com.rodrigoma.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TechTalkRepository extends JpaRepository<TechTalk, Long> {

    // TODO 04 Repository, JpaRepository, finds

    // http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

    List<TechTalk> findByAuthor(String author);

    List<TechTalk> findByDurationGreaterThan(int duration);

    List<TechTalk> findByCategoryAndDurationGreaterThan(String category, int duration);

}