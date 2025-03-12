package app.vehiclemanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

   @GetMapping("/index")
    public String goHome(){
       return "index";
   }

   @GetMapping("/guestsprofile")
   public String guestsProfile(){
       return "guestsprofile";
   }


   @GetMapping("/accessDenied")
   public String accessDenied(){
       return "/security/accessDenied";
   }

   @GetMapping("/accessRestricted")
   public String accessRestricted(){
       return "/security/accessRestricted";
   }

    @GetMapping("/hr")
    public String hr(){
        return "/hr/index";
    }
    @GetMapping("/fleet")
    public String fleet(){
        return "/fleet/index";
    }

    @GetMapping("/accounts")
    public String accounts(){
        return "/accounts/index";
    }

    @GetMapping("/parameters")
    public String parameters(){
        return "/parameters/index";
    }

    @GetMapping("/security")
    public String security(){
       return "/security/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "/index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
