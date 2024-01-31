package mukhammed.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

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
@ToString(callSuper = true, exclude = {"company", "programmers"})
@SequenceGenerator(name = "base_id_gen", sequenceName = "company", allocationSize = 1, initialValue = 1)
public class Project extends BaseEntity{
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {PERSIST, DETACH, REFRESH, MERGE})
    private List<Programmer> programmers;

    public Project(String title) {
        this.title = title;
    }

    public void addProgrammer(Programmer programmer) {
        if (programmers == null) programmers = new ArrayList<>();
        programmers.add(programmer);
    }
}
