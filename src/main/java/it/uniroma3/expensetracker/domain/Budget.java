package it.uniroma3.expensetracker.domain;

public class Budget {
    private Integer budgetId;
    private Integer userId;
    private Double totalBudget;

    public Budget(Integer budgetId, Integer userId, Double totalBudget) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.totalBudget = totalBudget;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }
}
