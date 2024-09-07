package ru.comavp.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "hibernate_course_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(typeClass = JsonBinaryType.class, name = "jsonb")
public class User {

    @EmbeddedId
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String userName;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Type(type = "jsonb")
    private String info;
}
