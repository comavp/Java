package com.example.tacocloud.repository.impl;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Taco;
import com.example.tacocloud.repository.TacoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(final Taco taco) {
        final long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(final Taco taco) {
        taco.setCreatedAt(new Date());
        final PreparedStatementCreatorFactory statementFactory = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP);
        statementFactory.setReturnGeneratedKeys(true);
        final PreparedStatementCreator statement = statementFactory.newPreparedStatementCreator(
                        Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime()))
                );
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(statement, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(final Ingredient ingredient, final long tacoId) {
        jdbcTemplate.update("insert into Taco_Ingredients (taco, ingredient) " + "values (?, ?)",
                tacoId, ingredient.getId());
    }
}
