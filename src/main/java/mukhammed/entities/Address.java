package mukhammed.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
@SequenceGenerator(name = "base_id_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address extends BaseEntity{
    private String country;
    @OneToOne(mappedBy = "address")
    private Programmer programmer;

    public Address(String country) {
        this.country = country;
    }
}
