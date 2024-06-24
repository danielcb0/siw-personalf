package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.Budget;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class BudgetRepositoryImpl implements BudgetRepository {

    private static final String SQL_FIND_BY_USER_ID = "SELECT BUDGET_ID, USER_ID, TOTAL_BUDGET FROM ET_BUDGETS WHERE USER_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO ET_BUDGETS (BUDGET_ID, USER_ID, TOTAL_BUDGET) VALUES(NEXTVAL('ET_BUDGETS_SEQ'), ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_BUDGETS SET TOTAL_BUDGET = ? WHERE USER_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Budget findByUserId(Integer userId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID, new Object[]{userId}, budgetRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Budget not found");
        }
    }

    @Override
    public Integer create(Integer userId, Double totalBudget) throws EtResourceNotFoundException {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setDouble(2, totalBudget);
                return ps;
            });
            return userId;
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Double totalBudget) throws EtResourceNotFoundException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{totalBudget, userId});
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid request");
        }
    }

    private RowMapper<Budget> budgetRowMapper = ((rs, rowNum) -> {
        return new Budget(rs.getInt("BUDGET_ID"), rs.getInt("USER_ID"), rs.getDouble("TOTAL_BUDGET"));
    });
}
