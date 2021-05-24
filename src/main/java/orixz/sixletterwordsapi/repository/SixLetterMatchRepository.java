package orixz.sixletterwordsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orixz.sixletterwordsapi.model.SixLetterMatchResult;

@Repository
public interface SixLetterMatchRepository extends JpaRepository<SixLetterMatchResult, String> {
}
