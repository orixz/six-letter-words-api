package orixz.sixletterwordsapi.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orixz.sixletterwordsapi.model.SixLetterMatchResult;
import orixz.sixletterwordsapi.repository.SixLetterMatchRepository;
import orixz.sixletterwordsapi.service.SixLetterServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
public class FileInputController {

    private final SixLetterServiceImpl sixWordService;
    private final SixLetterMatchRepository matchRepository;
    private final ResultMapper mapper;
    @Value("${elements-to-combine}")
    private Integer requiredElementsForCombo;

    public FileInputController(SixLetterServiceImpl sixWordService, SixLetterMatchRepository matchRepository, ResultMapper mapper) {
        this.sixWordService = sixWordService;
        this.matchRepository = matchRepository;
        this.mapper = mapper;
    }

    @PostMapping(
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(BufferedReader br) throws IOException {
        Set<String> entries;

        try (br) {
            entries = br.lines().collect(Collectors.toSet());
        }

        Set<String> results = sixWordService.checkForCombinations(entries::stream, requiredElementsForCombo);
        String resultString = mapper.map(results);

        matchRepository.save(SixLetterMatchResult.builder()
                .result(resultString)
                .build());

        return ResponseEntity.ok(resultString);
    }
}
