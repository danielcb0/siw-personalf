package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.Budget;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

public interface BudgetRepository {

    Budget findByUserId(Integer userId) throws EtResourceNotFoundException;

    Integer create(Integer userId, Double totalBudget) throws EtResourceNotFoundException;

    void update(Integer userId, Double totalBudget) throws EtResourceNotFoundException;
}
