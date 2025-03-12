package app.vehiclemanagement.accounts.controllers;

import app.vehiclemanagement.accounts.models.TransactionStatus;
import app.vehiclemanagement.accounts.services.TransactionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionStatusController {

    @Autowired
    private TransactionStatusService transactionStatusService;

    @GetMapping("/accounts/transactionStatusList")
    public String getAll(Model model) {
        List<TransactionStatus> transactionStatusList = transactionStatusService.findAll();
        model.addAttribute("transactionStatuses", transactionStatusList);
        return "/accounts/transactionStatusList";
    }

    @GetMapping("/accounts/transactionStatusList/{id}")
    @ResponseBody
    public TransactionStatus getById(@PathVariable Integer id) {
        return transactionStatusService.findById(id);
    }

    @PostMapping(value = "/accounts/transactionStatusList")
    public String save(TransactionStatus transactionStatus) {
        transactionStatusService.save(transactionStatus);
        return "redirect:/accounts/transactionStatusList";
    }

    @RequestMapping(value = "/accounts/transactionStatusList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        transactionStatusService.delete(id);
        return "redirect:/accounts/transactionStatusList";
    }
}
