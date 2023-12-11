package example.spring.brand;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "brand")
public class BrandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;

    public BrandModel(BrandRequestDTO dto) {
        this.name = dto.name();
    }

    public BrandModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
