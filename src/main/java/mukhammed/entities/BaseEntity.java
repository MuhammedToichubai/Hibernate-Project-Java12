package mukhammed.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mukhammed Asantegin
 */
@MappedSuperclass
@Getter @Setter
public class BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "base_id_gen"
    )
    private Long id;

    @Override
    public String toString() {
        return "id=" + id+", ";
    }
}
