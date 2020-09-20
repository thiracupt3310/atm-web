package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    private ArrayList<BankAccount> bankAccounts;

    @PostConstruct
    public void postContruct() {
        bankAccounts = new ArrayList<>();
    }

    public void createBankaccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public List<BankAccount> getBankaccount() {
        return new ArrayList<>(this.bankAccounts);
    }
}
