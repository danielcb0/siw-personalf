package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Budget;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

public interface BudgetService {
    Budget fetchBudgetByUserId(Integer userId) throws EtResourceNotFoundException;

    Budget updateBudget(Integer userId, Double totalBudget) throws EtResourceNotFoundException;
}
