package orixz.sixletterwordsapi.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SixLetterServiceImpl implements SixLetterService {

    @Override
    public Set<String> checkForCombinations(Supplier<Stream<String>> entries, Integer requiredElementsForCombo) {

//        not used
//        requiredElementsForCombo

        Set<String> resultSet = new HashSet<>();
        Set<String> singles = filterByEntryLength(entries, 1);
        Set<String> doubles = filterByEntryLength(entries, 2);
        Set<String> triplets = filterByEntryLength(entries, 3);
        Set<String> quadruplets = filterByEntryLength(entries, 4);
        Set<String> quintuplets = filterByEntryLength(entries, 5);
        Set<String> sextuplets = filterByEntryLength(entries, 6);

        singles.forEach(single -> {
            quintuplets.forEach(quintuplet -> {
                addCombination(single, quintuplet, resultSet, sextuplets);
                addCombination(quintuplet, single, resultSet, sextuplets);
            });
        });

        doubles.forEach(doublet -> {
            quadruplets.forEach(quadruplet -> {
                addCombination(doublet, quadruplet, resultSet, sextuplets);
                addCombination(quadruplet, doublet, resultSet, sextuplets);
            });
        });

        triplets.forEach(triplet -> {
            Stream<String> otherTriplets = triplets.stream().filter(entry -> !entry.equals(triplet));
            otherTriplets.forEach(otherTriplet ->
                    addCombination(triplet, otherTriplet, resultSet, sextuplets));
        });

        return resultSet;
    }

    private Set<String> filterByEntryLength(Supplier<Stream<String>> entries, int i) {
        return entries.get().filter(entry -> entry.length() == i).collect(Collectors.toSet());
    }

    protected void addCombination(String string1, String string2, Set<String> resultSet, Set<String> sextuplets) {
        String combination = string1 + string2;
        if (sextuplets.contains(combination)) {
            resultSet.add(String.format("%s+%s=%s", string1, string2, combination));
        }
    }

}
