package mukhammed.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "programmers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "projects")
@SequenceGenerator(name = "base_id_gen", sequenceName = "programmer_seq", allocationSize = 1)
public class Programmer extends BaseEntity{
    private String fullName;
    private String email;
    @ManyToMany(mappedBy = "programmers")
    private List<Project> projects;

    public void addProject(Project project) {
        if (project == null) projects  = new ArrayList<>();
        projects.add(project);
    }
}
