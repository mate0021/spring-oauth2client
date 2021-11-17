package rnd.mate00.oauth2client.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rnd.mate00.oauth2client.business.PurchaseOrder;
import rnd.mate00.oauth2client.business.repository.PurchaseOrderRepository;
import rnd.mate00.oauth2client.provider.OAuth2Provider;
import rnd.mate00.oauth2client.user.CurrentLoggedUser;
import rnd.mate00.oauth2client.user.DbUser;
import rnd.mate00.oauth2client.user.GoogleOAuthUser;
import rnd.mate00.oauth2client.user.repository.UserRepository;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/purchase")
    public String purchase(Model model) {
        PurchaseDto purchase = new PurchaseDto();
        model.addAttribute("purchase", purchase);

        return "purchase";
    }

    @PostMapping("/addPurchase")
    public String addPurchase(@ModelAttribute PurchaseDto purchaseDto, @CurrentLoggedUser OAuth2User currentUser) {
        GoogleOAuthUser user = (GoogleOAuthUser) currentUser;
        DbUser dbUser = userRepository.findByEmailAndProvider(user.getEmail(), OAuth2Provider.GOOGLE).orElseThrow();

        PurchaseOrder purchase = new PurchaseOrder();
        purchase.setUser(dbUser);
        purchase.setProductName(purchaseDto.getPurchaseName());
        orderRepository.save(purchase);
        
        return "redirect:/loginOk";
    }
}
