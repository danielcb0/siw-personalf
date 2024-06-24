package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Budget;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;
import it.uniroma3.expensetracker.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public Budget fetchBudgetByUserId(Integer userId) throws EtResourceNotFoundException {
        return budgetRepository.findByUserId(userId);
    }

    @Override
    public Budget updateBudget(Integer userId, Double totalBudget) throws EtResourceNotFoundException {
        budgetRepository.update(userId, totalBudget);
        return budgetRepository.findByUserId(userId);
    }
}
