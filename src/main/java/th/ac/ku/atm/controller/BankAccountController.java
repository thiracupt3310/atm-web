package th.ac.ku.atm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.service.CustomerService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankaccountPage(Model model) {
        model.addAttribute("allBankaccount", bankAccountService.getBankaccounts());
        return "bankaccount";
    }
    @PostMapping
    public String registerBankaccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.createBankaccount(bankAccount);
        model.addAttribute("allBankaccount", bankAccountService.getBankaccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-deposit";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {
        bankAccountService.deposit(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankaccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteBankAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {

        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankaccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-withdraw";
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id,
                          @ModelAttribute BankAccount bankAccount,
                          Model model) {
        bankAccountService.withdraw(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankaccounts());
        return "redirect:/bankaccount";
    }
}
