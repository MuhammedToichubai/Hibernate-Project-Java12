package mukhammed.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

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
    @ManyToMany(mappedBy = "programmers", cascade = {PERSIST, DETACH, REFRESH, MERGE})
    private List<Project> projects;

    @OneToOne(cascade = {REMOVE}, orphanRemoval = true)
    private Address address;

    public void addProject(Project project) {
        if (project == null) projects  = new ArrayList<>();
        projects.add(project);
    }
}
