package orixz.sixletterwordsapi.service;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface SixLetterService {

    Set<String> checkForCombinations(Supplier<Stream<String>> entries, Integer requiredElementsForCombo);
}
