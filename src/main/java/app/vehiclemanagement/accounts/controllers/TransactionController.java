package app.vehiclemanagement.accounts.controllers;

import app.vehiclemanagement.accounts.models.Transaction;
import app.vehiclemanagement.accounts.services.TransactionService;
import app.vehiclemanagement.accounts.services.TransactionStatusService;
import app.vehiclemanagement.accounts.services.TransactionTypeService;
import app.vehiclemanagement.hr.services.EmployeeService;
import app.vehiclemanagement.parameters.services.ClientService;
import app.vehiclemanagement.parameters.services.ContactService;
import app.vehiclemanagement.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class TransactionController {

    @Autowired private TransactionService transactionService;
    @Autowired private ContactService contactService;
    @Autowired private SupplierService supplierService;
    @Autowired private ClientService clientService;
    @Autowired private EmployeeService employeeService;
    @Autowired private TransactionStatusService transactionStatusService;
    @Autowired private TransactionTypeService transactionTypeService;


    public Model addModelAttributes(Model model){
        model.addAttribute("contacts", contactService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("transactionStatuses", transactionStatusService.findAll());
        model.addAttribute("transactionTypes", transactionTypeService.findAll());
        model.addAttribute("transactions", transactionService.getAll());
        return model;
    }

    @GetMapping("/transactions")
    public String findAll(Model model){
        addModelAttributes(model);
        return "accounts/transactionList";
    }

    @GetMapping("/accounts/transaction/{id}")
    @ResponseBody
    public Transaction getTransaction(@PathVariable Integer id) {
        return transactionService.getById(id);
    }

    @GetMapping("/transactionAdd")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "/accounts/transactionAdd";
    }

    @GetMapping("/transactionEdit/{id}")
    public String editTransaction(@PathVariable Integer id, Model model){
        Transaction transaction = transactionService.getById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transactionEdit";
    }

    @GetMapping("/transactionDetails/{id}")
    public String detailsTransaction(@PathVariable Integer id, Model model) {
        Transaction transaction = transactionService.getById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transactionDetails";
    }

    @PostMapping("/transactions")
    public String addNew(Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @RequestMapping(value="/transactions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        transactionService.delete(id);
        return "redirect:/transactions";
    }

    @RequestMapping(value="/transactions/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }
}
