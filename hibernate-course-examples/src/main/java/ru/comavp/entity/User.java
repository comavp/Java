package ru.comavp.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "hibernate_course_schema")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(typeClass = JsonBinaryType.class, name = "jsonb")
@Access(AccessType.PROPERTY)
public class User {

    private String userName;
    PersonalInfo personalInfo;
    private Role role;
    private String info;

    @Column(unique = true)
    public String getUserName() {
        return this.userName;
    }

    @EmbeddedId
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    public PersonalInfo getPersonalInfo() {
        return this.personalInfo;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return this.role;
    }

    @Type(type = "jsonb")
    public String getInfo() {
        return this.info;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
