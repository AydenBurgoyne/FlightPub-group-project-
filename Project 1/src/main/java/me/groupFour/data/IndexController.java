package me.groupFour.data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    //@PutMapping    ....other http verbs
    //@PostMapping
    public ModelAndView index(
            @RequestParam(name = "name") String name
    )                                                   // indicating that a request will be passed in via url as "name"
    {
        ModelAndView view = new ModelAndView("index");
        view.addObject("name", name);       // passing a value to the index.js[ and we will be referencing that value as "name"
        return view;
    }//data model for the view. not like a regular business model

    @GetMapping("test")
    public ModelAndView test(){
        ModelAndView view = new ModelAndView("Flight/flight");
        return view;
    }
}
