package me.groupFour.data;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.groupFour.dao.*;
import me.groupFour.dao.ClassEntityDAO;
import me.groupFour.dao.DestinationEntityDAO;
import me.groupFour.dao.FlightEntityDAO;
import me.groupFour.dao.TicketEntityDAO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;


@Controller
@RequestMapping("search")
//Search Controllers deals with processing the info provided by the search query entity
public class SearchController {
    //Initialising DAOs
    private TicketEntityDAO TEDAO;
    private ClassEntityDAO CEDAO;
    private FlightEntityDAO flightDAO;
    private DestinationEntityDAO des;
    private PriceEntityDAO PDAO;
    SearchQueryEntity searchQuery;


//Constructor
    public SearchController(TicketEntityDAO TEDAO, ClassEntityDAO CEDAO, FlightEntityDAO flightDAO, DestinationEntityDAO des, PriceEntityDAO PDAO) {
        this.TEDAO = TEDAO;
        this.CEDAO = CEDAO;
        this.des = des;
        this.flightDAO = flightDAO;
        this.PDAO = PDAO;
    }


    //You have to register an InitBinder in your controller to let spring convert your date string to java.util.Date object and set it in command object.

    @GetMapping
    public ModelAndView Search(@Valid @ModelAttribute("SearchQueryEntity") SearchQueryEntity sq, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/");
        }
        DestinationEntity FromDest = des.searchByAirportNameSingle(sq.getFromDest());
        DestinationEntity ToDest = des.searchByAirportNameSingle(sq.getToDest());
        LinkedList<DestinationEntity> DList = new LinkedList<>();
        LinkedList<JourneyEntity> finallist = new LinkedList<>();
        LinkedList<LinkedList<DestinationEntity>> list;
        flightMap graph = new flightMap(des,flightDAO);
        if(!sq.getViaDest().equals("")){
            DestinationEntity viaDest = des.searchByAirportNameSingle(sq.getViaDest());
            list = graph.viaPath(FromDest.getDestinationCode(),viaDest.getDestinationCode(),ToDest.getDestinationCode());
        }
        else {
            list = graph.getPaths(FromDest.getDestinationCode(), ToDest.getDestinationCode());
        }
        LinkedList<LinkedList<DestinationEntity>> copyl = ( LinkedList<LinkedList<DestinationEntity>>)list.clone();

        Iterator<LinkedList<DestinationEntity>> it = copyl.iterator();
        while(it.hasNext()){
            LinkedList<DestinationEntity> temp = (LinkedList<DestinationEntity>)it.next().clone();

            generateJourneys(temp,sq,null,finallist,null,null,(temp.size()-1)); //destamount needs to be the amount of legs it needs
        }
        ModelAndView view = new ModelAndView("SearchResults");
        view.addObject("journeyList",finallist);
        return view;
    }


//Generate Journeys aka stiches together different flights forming legs that make up journeys.
    public void generateJourneys(LinkedList<DestinationEntity> Destinationlist, SearchQueryEntity search,JourneyEntity journey,LinkedList<JourneyEntity> finallist,DestinationEntity lastDest,Timestamp LastArrival,int destAmount){
        DestinationEntity dest1; //Destination 1
        DestinationEntity dest2;//Destination 2
        Timestamp RangeStart; //Start of time searching range
        Timestamp RangeEnd; //End of time searching range
        JourneyEntity tempJourney; //declaring tempJourney entity
        int max_wait = search.getMaxLayoverTime(); //int for max wait time at any segment of the journey.
        //Checks that the Destination list is empty and that the journey has the amount of legs expected
        if(journey!=null&&Destinationlist.isEmpty()&&journey.getLegsOfJourney().size()==destAmount){
            journey.calculatingPrice(); //calculates the total price for the journey based on the individual prices for the legs.
            finallist.add(journey); // adds the journey to the journey linkedlist.
            return;
        }
        //checks if the journey is null, meaning that it's the first time the generateJourney method has been called.
        if(journey==null){
            tempJourney = new JourneyEntity();
            dest1 = Destinationlist.poll();//starting destination
            dest2 = Destinationlist.poll();//End destination for this particular flight
            RangeStart = search.getFromTimeSQL(); //Start of time range for searching for flights on this particular leg.
            RangeEnd = search.getToTimeSQL(); //End of range for searching for flights for this particular leg.
        }
        //if the journey is not null e.g not the first time that the generateJourney method has been run.
        else {
            tempJourney = journey;
            dest1 = lastDest; //uses the destination from the last leg as the deparature.
            dest2 = Destinationlist.poll(); //Grabs the next destination from the destination list to use as the destination.
            RangeStart= new Timestamp(LastArrival.getTime()+(3600000*2)); //adds offset for transfering flights
            RangeEnd = new Timestamp(RangeStart.getTime()+(3600000*max_wait)); //adds the max wait to the buffered range start
        }
        //Using flightDAO to search for flights returns as List of flight entities.
        List<FlightEntity> FlightResultList = flightDAO.searchFlight(RangeStart,RangeEnd,dest1,dest2);//assuming searchFlight uses (Timestamp start, Timestamp end, DestinationEntity dest1, DestinationEntity dest2
        Iterator<FlightEntity> it = FlightResultList.listIterator(); //gets list iterator.
        while(it.hasNext()){ //while loop to go through the flight list.
            //cloning existing journeys and destination list to prevent Destinations from accidentally being affected.
            JourneyEntity tempJ = new JourneyEntity();
            LinkedList<DestinationEntity>list = Destinationlist;
            try {
                 list = (LinkedList<DestinationEntity>)Destinationlist.clone();
                tempJ = tempJourney.Clone();
            }
            catch(Exception e){
                System.out.println(e);
            }
            FlightEntity temp = it.next(); //grabs the flight from the list.
            TicketEntity ticketcode = TEDAO.findById(search.getTicketCode()); //converts the ticketcode string stored in the search entity to TicketEntity by grabing from DB
            ClassEntity classcode = CEDAO.findById(search.getClassCode()); //grabs the ClassEntity from the db based on the string stored in the search entity.
            LegEntity leg = new LegEntity(); //creates a new leg for a journey.
            leg.setFlightID(temp); //sets the flight for the leg.
            leg.setTicketclass(classcode); //sets the classcode for the leg
            leg.setTickettype(ticketcode); //sets the ticketcode for the leg.
            //leg.setPriceEn(PDAO.findById(16686));
            leg.setPriceEn(PDAO.findPrice(tempJ.getSearchTime(),temp,classcode,ticketcode,temp.getAirlineCode())); //sets the price by searching for the price entity in the database.
            tempJ.addLeg(leg); //adds the leg to the journey
            generateJourneys(list,search,tempJ,finallist,dest2,temp.getArrivalTime(),destAmount); //keeps going through until no more destinations are left. //calls the generate journey method recursively to continue generating the journeys.

        }
        //if the journey is not null and the destiantion list is empty and the destination amount is the size expected.
        if(journey!=null&&Destinationlist.isEmpty()&&journey.getLegsOfJourney().size()==destAmount){
            journey.calculatingPrice();
            finallist.add(journey);
        }

    }
    //Method returns json with City names that are similar to those inputted by the user. This enables the autocompete destination fields.
    @GetMapping
    @RequestMapping(value="AirportAutoComplete")
    @ResponseBody
    public List<String> AirportAutoComplete(@RequestParam (value="term",defaultValue="") String term){
        List<DestinationEntity> list = des.searchByAirportName(term);
        List<String> AirportNameList = new ArrayList<>();
        Iterator<DestinationEntity> iterator = list.iterator();
        while(iterator.hasNext()){
            AirportNameList.add(iterator.next().getAirport());
        }
        return AirportNameList;
    }




}
