package io.github.skeffy.tellernet.controller;

import io.github.skeffy.tellernet.dao.TransactionDao;
import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Transaction;
import io.github.skeffy.tellernet.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    private TransactionDao transactionDao;
    private TransactionService transactionService;

    public TransactionController(TransactionDao transactionDao, TransactionService transactionService) {
        this.transactionDao = transactionDao;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(int accountId) {
        try {
            return transactionDao.getTransactionsByAccount(accountId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        try {
            return transactionService.transact(transaction);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
