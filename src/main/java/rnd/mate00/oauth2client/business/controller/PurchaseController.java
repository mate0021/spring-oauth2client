package rnd.mate00.oauth2client.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rnd.mate00.oauth2client.business.repository.PurchaseOrderRepository;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseOrderRepository repository;

    @GetMapping("/purchase")
    public String purchase(Model model) {
        PurchaseDto purchase = new PurchaseDto();
        model.addAttribute("purchase", purchase);

        return "purchase";
    }

    @PostMapping("/addPurchase")
    public String addPurchase(@ModelAttribute PurchaseDto purchaseDto) {

        return "redirect:/loginOk";
    }
}
