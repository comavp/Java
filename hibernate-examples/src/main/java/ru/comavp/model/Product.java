package ru.comavp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
}
