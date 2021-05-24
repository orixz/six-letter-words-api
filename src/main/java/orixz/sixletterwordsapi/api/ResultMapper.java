package orixz.sixletterwordsapi.api;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResultMapper {

    public String map(Set<String> resultSet) {
        StringBuilder stringBuilder = new StringBuilder();
        resultSet.forEach(result -> stringBuilder.append(result).append("\n"));
        return stringBuilder.toString();
    }

}
