import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FirstExampleTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testJdbcBefore() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Before Java 8
        List<Person> personsBefore = jdbcTemplate.query("SELECT * FROM person WHERE dep = ?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, "Sales");
                    }
                },
                new RowMapper<Person>() {
                    @Override
                    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Person(rs.getString(1), rs.getInt(2));
                    }
                });

        // After Java 8
        List<Person> personsAfter = jdbcTemplate.query("SELECT * FROM person WHERE dep = ?",
                ps -> ps.setString(1, "Sales"),
                (rs, rowNum) -> new Person(rs.getString(1), rs.getInt(2)));
    }
}
