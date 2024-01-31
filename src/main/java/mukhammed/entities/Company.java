package mukhammed.entities;

import jakarta.persistence.*;
import lombok.*;

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
@ToString(callSuper = true)
@SequenceGenerator(name = "base_id_gen", sequenceName = "company", allocationSize = 1, initialValue = 1)
public class Company extends BaseEntity {
    private String name;
    @OneToOne(cascade = {REMOVE}, orphanRemoval = true)
    private Address address;

    public Company(String name) {
        this.name = name;
    }

}
