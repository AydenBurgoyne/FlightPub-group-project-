package me.groupFour.data;

import me.groupFour.dao.BookingEntityDAO;
import me.groupFour.dao.IAccountEntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes({"ActiveUser","Booking","reserved"})
@RequestMapping("Account")
public class AccountController {
    private IAccountEntityDAO ac;
    private BookingEntityDAO BookingDAO;
    //@PutMapping    ....other http verbs
    //@PostMapping
    @Autowired
    public AccountController(IAccountEntityDAO ac,BookingEntityDAO BookingDAO){
        this.ac = ac;
        this.BookingDAO = BookingDAO;
    }

    @GetMapping
    @RequestMapping("CreateAccount")
    public ModelAndView CreateAccount()
    {
        ModelAndView view = new ModelAndView("CreateAccount");
        view.addObject("AccountEntity", new AccountEntity());
        return view;
    }

    @GetMapping
    @RequestMapping("ViewAccount")
    public ModelAndView ViewAccount( ModelMap model)
    {
        ModelAndView view = new ModelAndView("Account");
        view.addObject("account", model.get("ActiveUser"));
        return view;
    }

    @PostMapping
    @RequestMapping("LogOut")
    public ModelAndView LogOut(ModelMap model){
        ModelAndView view = new ModelAndView("HomePage");
        view.addObject("SearchQueryEntity",new SearchQueryEntity());
        model.put("ActiveUser",null);
        model.remove("ActiveUser");
        //view.addObject("account", null);
        return view;
    }

    @PostMapping
    @RequestMapping("ValidateAccCreation")
    public ModelAndView ValidateAccount(@Valid @ModelAttribute("AccountEntity") AccountEntity account, BindingResult bindingResult)                                                   // indicating that a request will be passed in via url as "name"
    {
        if (bindingResult.hasErrors()){
            ModelAndView view = new ModelAndView("CreateAccount");
            return view;
        }
        else if (ac.findById(account.getEmail()) != null) {
            ModelAndView view = new ModelAndView("CreateAccount");
            return view;
        }
        else {
            ac.create(account);
            ModelAndView view = new ModelAndView("Login");
            view.addObject("LoginQueryEntity",new LoginQueryEntity());
            return view;
        }
    }
    @PostMapping
    @RequestMapping("ValidateAccCreationGuest")
    public ModelAndView ValidateAccountGuest(@Valid @ModelAttribute("AccountEntity") AccountEntity account, BindingResult bindingResult,ModelMap model)                                                   // indicating that a request will be passed in via url as "name"
    {
        if (bindingResult.hasErrors()){
            ModelAndView view = new ModelAndView("CreateAccountGuest");
            return view;
        }
        else if (ac.findById(account.getEmail()) != null) {
            ModelAndView view = new ModelAndView("CreateAccountGuest");
            return view;
        }
        else {
            ac.create(account);
            /*String password = account.getPword();
            password = account.encryptPassword(password);
            account.setPword(password);
            ac.update(account);*/
            BookingEntity book = (BookingEntity)model.get("Booking");
            book.setAccountID(account);
            BookingDAO.create(book);
            ModelAndView view = new ModelAndView("ConfirmationPage");
            view.addObject("Booking", book);
            view.addObject("reserved",model.getAttribute("reserved"));
            return view;
        }
    }

    @GetMapping
    @RequestMapping("Login")        // 8080::localhost/Account/login
    public ModelAndView Login(){
        ModelAndView view = new ModelAndView("Login");
        view.addObject("LoginQueryEntity",new LoginQueryEntity());
        return view;
    }

    @PostMapping
    @RequestMapping("ValidateLogin")
    // to enable validation, we pass in an arg to the create method that is bindingresult. this will tell us if there are any errors in view model. the view model is specified as a param. valid, makes it validate teh beans passed in the form. we also need to specifiy that it is a modelattribute and give it the name
    public ModelAndView ValidateLogin(
            @Valid @ModelAttribute("LoginQueryEntity") LoginQueryEntity query, BindingResult bindingResult, ModelMap model
    ){
        if(bindingResult.hasErrors())       // if this happens there are errors in the bean validation, return them back to the form page
        {
            ModelAndView view = new ModelAndView("Login");
            return view;
        }else if(checkLoginCredentials(query))
        {
            AccountEntity account = ac.findById(query.getEmailAddress());
            model.put("ActiveUser",account);
            ModelAndView view = new ModelAndView("redirect:/");
            return view;
        }else
        {
            ModelAndView view = new ModelAndView("Login");
            return view;
        }
    }

    private boolean checkLoginCredentials(LoginQueryEntity query) {
        AccountEntity account = ac.findById(query.getEmailAddress());
        return account.getPword().equals(query.getPassword());
    }
}
