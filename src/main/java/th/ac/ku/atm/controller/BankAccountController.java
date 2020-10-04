package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
//        model.addAttribute("allBankaccount", bankAccountService.getBankaccount());
        return "bankaccount";
    }
    @PostMapping
    public String registerBankaccount(@ModelAttribute BankAccount bankAccount, Model model) {
//        bankAccountService.createBankaccount(bankAccount);
//        model.addAttribute("allCustomers", bankAccountService.getBankaccount());
        return "redirect:bankaccount";
    }

}
