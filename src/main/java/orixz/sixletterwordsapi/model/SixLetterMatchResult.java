package orixz.sixletterwordsapi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_match_result")
public class SixLetterMatchResult {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(name = "created_timestamp")
    @NotNull
    private LocalDateTime createdOn;

    @Column(length = Integer.MAX_VALUE)
    private String result;

    @PrePersist
    private void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

}
