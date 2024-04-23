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

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_gen")
    //@SequenceGenerator(name = "user_gen", sequenceName = "users_user_id_seq", schema = "hibernate_course_schema", allocationSize = 1)
    @TableGenerator(name = "user_gen", table = "all_sequence", schema = "hibernate_course_schema", allocationSize = 1,
            pkColumnName = "table_name", valueColumnName = "pk_value")
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String userName;
    @Embedded
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Type(type = "jsonb")
    private String info;
}
