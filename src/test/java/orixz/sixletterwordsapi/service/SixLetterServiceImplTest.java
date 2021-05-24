package orixz.sixletterwordsapi.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class SixLetterServiceImplTest {

    private SixLetterServiceImpl sixLetterService;

    @Test
    void combinationsOnlyAddedIfFoundIn6LetterSet() {
        sixLetterService = new SixLetterServiceImpl();
        HashSet<String> resultSet = new HashSet<>();
        sixLetterService.addCombination("test", "je", resultSet, new HashSet<>());
        Assertions.assertThat(resultSet).isEmpty();
    }

    @Test
    void combinationCorrectAddedIfFoundIn6LetterSet() {
        sixLetterService = new SixLetterServiceImpl();
        HashSet<String> resultSet = new HashSet<>();
        sixLetterService.addCombination("test", "je", resultSet, Set.of("testje"));
        Assertions.assertThat(resultSet).containsExactly("test+je=testje");
    }
}