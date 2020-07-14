package me.groupFour.data;
import me.groupFour.data.AccountEntity;
import me.groupFour.data.SearchQueryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping
    //@PutMapping    ....other http verbs
    //@PostMapping
    public ModelAndView HomePageActive(ModelMap model)                                                   // indicating that a request will be passed in via url as "name"
    {
        ModelAndView view = new ModelAndView("HomePage");
        view.addObject("SearchQueryEntity",new SearchQueryEntity());
        if(model.containsKey("ActiveUser")) {
            AccountEntity temp = (AccountEntity)model.get("ActiveUser");
            view.addObject("ActiveUser", temp);
        }
        else{
            AccountEntity temp = null;
            view.addObject("ActiveUser",temp);
        }
        return view;
    }//data model for the view. not like a regular business model

    @GetMapping
    @RequestMapping("login")
    public ModelAndView Login(){
        ModelAndView view = new ModelAndView("Login");
        return view;
    }
}
