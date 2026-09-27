package ru.comavp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // org.hibernate.MappingException в случае strategy = InheritanceType.TABLE_PER_CLASS
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "internal_name")
    private String internalName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    public AbstractEntity() {
    }

    public AbstractEntity(String internalName, LocalDateTime creationDate, LocalDateTime modifyDate) {
        this.internalName = internalName;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
