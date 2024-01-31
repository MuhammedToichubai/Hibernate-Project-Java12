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
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "projects")
@SequenceGenerator(name = "base_id_gen", sequenceName = "company_seq", allocationSize = 1, initialValue = 1)
public class Company extends BaseEntity {
    private String name;
    @OneToOne(cascade = {REMOVE}, orphanRemoval = true)
    private Address address;
    @OneToMany(mappedBy = "company", cascade = {REMOVE})
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
    }

    public void addProject(Project project) {
        if (projects == null) projects = new ArrayList<>();
        projects.add(project);
    }
}
