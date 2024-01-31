package mukhammed.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "projects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "base_id_gen", sequenceName = "company", allocationSize = 1, initialValue = 1)
public class Project extends BaseEntity{
    private String title;
    @ManyToOne
    private Company company;

    public Project(String title) {
        this.title = title;
    }
}
