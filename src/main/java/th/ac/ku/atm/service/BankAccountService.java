package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {

    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://localhost:8091/api/bankaccount/customer/" +
                customerId;
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);
    }

    public void createBankaccount(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount";

        restTemplate.postForObject(url, bankAccount, BankAccount.class);
    }

    public List<BankAccount> getBankaccounts() {
        String url = "http://localhost:8091/api/bankaccount/";

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }

    public BankAccount getBankAccount(int id) {
        String url = "http://localhost:8091/api/bankaccount/" + id;

        ResponseEntity<BankAccount> response =
                restTemplate.getForEntity(url, BankAccount.class);

        return response.getBody();
    }

    public void deposit(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount/" +
                bankAccount.getId();
        int id = bankAccount.getId();
        double amount = bankAccount.getBalance();
        double current = getBankAccount(id).getBalance();

        bankAccount = getBankAccount(id);
        if (amount > 0) {
            bankAccount.setBalance(amount + current);
        }

        restTemplate.put(url, bankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount/" +
                bankAccount.getId();
        restTemplate.delete(url, bankAccount);
    }

    public void withdraw(BankAccount bankAccount) {
        String url = "http://localhost:8091/api/bankaccount/" +
                bankAccount.getId();
        int id = bankAccount.getId();
        double amount = bankAccount.getBalance();
        double current = getBankAccount(id).getBalance();

        bankAccount = getBankAccount(id);

        if (amount > 0 && (amount <= current)) {
            bankAccount.setBalance(current - amount);
        }

        restTemplate.put(url, bankAccount);
    }

}
