package it.uniroma3.expensetracker.resources;

import it.uniroma3.expensetracker.domain.Budget;
import it.uniroma3.expensetracker.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/budget")
public class BudgetResource {

    @Autowired
    BudgetService budgetService;

    @GetMapping("")
    public ResponseEntity<Budget> getBudget(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        Budget budget = budgetService.fetchBudgetByUserId(userId);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Budget> updateBudget(HttpServletRequest request, @RequestBody Map<String, Object> budgetMap) {
        int userId = (Integer) request.getAttribute("userId");
        Double totalBudget = Double.valueOf(budgetMap.get("totalBudget").toString());
        Budget budget = budgetService.updateBudget(userId, totalBudget);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
}
