package app.vehiclemanagement.accounts.controllers;

import app.vehiclemanagement.accounts.models.TransactionType;
import app.vehiclemanagement.accounts.services.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping("/accounts/transactionTypeList")
    public String getAll(Model model) {
        List<TransactionType> transactionTypeList = transactionTypeService.findAll();
        model.addAttribute("transactionTypes", transactionTypeList);
        return "/accounts/transactionTypeList";
    }

    @GetMapping("/accounts/transactionTypeList/{id}")
    @ResponseBody
    public TransactionType getById(@PathVariable Integer id) {
        return transactionTypeService.findById(id);
    }

    @PostMapping(value = "/accounts/transactionTypeList")
    public String save(TransactionType transactionType) {
        transactionTypeService.save(transactionType);
        return "redirect:/accounts/transactionTypeList";
    }

    @RequestMapping(value = "/accounts/transactionTypeList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
       transactionTypeService.delete(id);
        return "redirect:/accounts/transactionTypeList";
    }
}
