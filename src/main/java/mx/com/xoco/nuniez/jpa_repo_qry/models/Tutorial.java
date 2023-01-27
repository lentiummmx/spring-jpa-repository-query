package mx.com.xoco.nuniez.jpa_repo_qry.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.jpa.PgsqlQueryHints;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "tutorials")
@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NamedQueries(value = {
        @NamedQuery(name = "NQ_TUTORIALS_PUBLISHED",
                query = "SELECT t FROM Tutorial t WHERE t.published=:published",
                hints = {@QueryHint(name = PgsqlQueryHints.HINT_CUSTOM_QUERY_HINT,
                        value = "IndexScan(tutorials idx)")})})
@NamedNativeQueries({
        @NamedNativeQuery(name = "NNQ_TUTORIALS_PUBLISHED",
                query = "SELECT * FROM tutorials tut " +
                        "WHERE title = :title AND published = :published " +
                        "UNION " +
                        "SELECT * FROM tutorials tut " +
                        "WHERE description like :description AND published = :published",
                hints = {@QueryHint(name = PgsqlQueryHints.HINT_INDEX_SCAN,
                        value = "IndexScan(tutorials idx)")},
                resultClass = Tutorial.class),
        @NamedNativeQuery(name = "Tutorial.findByTitleAndPublished",
                query = "SELECT * FROM tutorials tut " +
                        "WHERE title = ?1 AND published = ?2",
                hints = {@QueryHint(name = PgsqlQueryHints.HINT_INDEX_SCAN,
                        value = "IndexScan(tutorials idx)")},
                resultClass = Tutorial.class)})
public class Tutorial extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private int level;

    private boolean published;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tutorial tutorial = (Tutorial) o;
        return id != null && Objects.equals(id, tutorial.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
