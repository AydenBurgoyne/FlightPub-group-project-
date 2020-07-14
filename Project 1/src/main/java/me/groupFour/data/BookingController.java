package me.groupFour.data;
import me.groupFour.dao.PriceEntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes({"journeyEntity", "confirmedFlights", "legEntity"})
@RequestMapping("/book")
public class BookingController {
    PriceEntityDAO priceDAO;
    private BookingController(PriceEntityDAO priceDAO){
        this.priceDAO = priceDAO;
    }
    private JourneyEntity journeyEntity;
    private LinkedList<LegEntity> confirmedFlights;


    @GetMapping
    public ModelAndView ViewBooking(
            @ModelAttribute("journeyEntity") JourneyEntity journeyEntity,
            @ModelAttribute("confirmedFlights") LinkedList<LegEntity> confirmedFlights,
            @ModelAttribute("currentLeg") LegEntity currentLeg,
            Model model
    ){
        //not sure how to set these so they start with the intial value

        //reset the legEntityList each time the function is called
        LinkedList<LegEntity> legEntityLinkedList = journeyEntity.getLegsOfJourney();

        if(!legEntityLinkedList.isEmpty()) {
            //condition where if the list is not empty, still has legs to display

            //create a view for the Journey
            ModelAndView view = new ModelAndView("BookJourney");
            //if it is not empty, remove the first leg
            currentLeg = legEntityLinkedList.remove();

            //update the journey entity to it has to be able to be done
            model.addAttribute("journeyEntity", journeyEntity);

            //not sure if this needs to be done to update the seat within the session
            //updates the currentLeg within the spring session
            model.addAttribute("currentLeg", currentLeg);


            //add it to the view returned
            view.addObject(currentLeg);

            //update the journeyEntity object for the next time the function is called?
            // remove the latest item, resend the item to this page
            //todo not sure if this is correct


            //when the list is empty, redirect the user to the completion page or the home page or something?
            //might send the booked journey details to that page aswell, to present the flights purchased

            return view;
        }else {
            //condition where there are no legs left in the journey
            //check to see if all have been set/confirmed
            //the journey entitity will have legs, need to go through until none left
            //want to send to same page, but with diff list shit idk

            //todo change
            //todo final flight list
            ModelAndView view = new ModelAndView("confirm");
            view.addObject(confirmedFlights);
            return view;
        }
    }


    @GetMapping
    @RequestMapping("confirm")
    // /book/confirm
    public RedirectView ConfirmBooking(
            @SessionAttribute("journeyEntity") JourneyEntity journeyEntity,
            @SessionAttribute("confirmedFlights") LinkedList<LegEntity> confirmedFlights,
            @SessionAttribute("currentLeg") LegEntity currentLeg,
            boolean confirmed,
            Model model
    )
    {
        if(!confirmed){
            //return to main page
            return new RedirectView("HomePage");
        } else {
            //condition where true
            confirmedFlights.add(currentLeg);
            model.addAttribute("confirmedFlights", confirmedFlights);
        }
        //change to a redirect, not sure if this works
        return new RedirectView("BookingController");
    }

    @GetMapping
    @RequestMapping("final")
    public ModelAndView FinalBooking(
            @SessionAttribute("confirmedFlights") LinkedList<LegEntity> confirmedFlights,
            Model  model
    ){
        //shows all of the current legs that have been confirmed, has final confirmation
        //model.getAttribute()
        ModelAndView view = new ModelAndView("BookJourney");
        //should display all of the diff flights
        view.addObject(confirmedFlights);
        return null;
    }
    /*
    @PostMapping
    @RequestMapping(value="SeatPrice")
    @ResponseBody
    public List<String> AirportAutoComplete(@RequestParam (value="term",defaultValue="") String term){
        leg.setPriceEn(PDAO.findPrice(tempJ.getSearchTime(),temp,classcode,ticketcode,temp.getAirlineCode()));
        List<String> AirportNameList = new ArrayList<>();
        Iterator<DestinationEntity> iterator = list.iterator();
        while(iterator.hasNext()){
            AirportNameList.add(iterator.next().getAirport());
        }
        return AirportNameList;
    }
*/
}
