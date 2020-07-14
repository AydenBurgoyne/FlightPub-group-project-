package me.groupFour.data;

import me.groupFour.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;

@Controller
@SessionAttributes({"currentJourney","proposedFlights","TicketType","ClassCode","timestamp","price","BookedJourney","Booking","ActiveUser","reserved"})
@RequestMapping("/Journey")
public class JourneyController {
    private DestinationEntityDAO destinationEntitydao;
    private TicketEntityDAO TEDAO;
    private ClassEntityDAO CEDAO;
    private FlightEntityDAO flightDAO;
    private PriceEntityDAO PDAO;
    private AccountEntityDAO ADA;
    private BookingEntityDAO bDAO;
    PlaneConfigDAO pdao;

    public JourneyController(PlaneConfigDAO pdao,BookingEntityDAO bDAO,AccountEntityDAO ADA, DestinationEntityDAO destinationEntitydao, TicketEntityDAO TEDAO, ClassEntityDAO CEDAO, FlightEntityDAO flightDAO, PriceEntityDAO PDAO) {
        this.destinationEntitydao = destinationEntitydao;
        this.pdao = pdao;
        this.TEDAO = TEDAO;
        this.ADA = ADA;
        this.CEDAO = CEDAO;
        this.bDAO = bDAO;
        this.flightDAO = flightDAO;
        this.PDAO = PDAO;
    }


    @GetMapping("addHotel")
    public ModelAndView addHotel(ModelMap model){
        return new ModelAndView("HotelAddon");
    }



    @GetMapping("confirmIndividualSeat")
    public ModelAndView confirmIndividualSeat(){
        ModelAndView view = new ModelAndView("IndividualSeatBooking");
        return view;
    }

    //
    //yyyy-MM-dd
    @GetMapping("bookPage")
    public ModelAndView search(){
        ModelAndView view = new ModelAndView("BookingPage");

        return view;
    }
    @GetMapping("viewJourney")
    public ModelAndView viewJourney(@RequestParam List<Integer> flightIds,@RequestParam String TicketType,@RequestParam String ClassCode, ModelMap model){
        model.remove("bookedFlights"); //Remove any existing booked flights
        List<FlightEntity> flights = new LinkedList<>();

        for(Integer id : flightIds){
            flights.add(flightDAO.findById(id));
        }
        Date date = new Date();

        BookedJourneyEntity journey = new BookedJourneyEntity();
        Timestamp searchTime = new Timestamp(date.getTime());
        model.remove("BookedJourney");
        FlightEntity temp = flights.get(0);
        LinkedList<Review> listReview = new LinkedList<Review>();
        Review newReview = new Review();
        newReview.setContent("This was the best flight I've ever had.");
        newReview.setTitle("Best Flight");
        listReview.add(newReview);
        newReview = new Review();
        newReview.setContent("Great Flight would fly again");
        newReview.setTitle("Great Flight");
        listReview.add(newReview);
        ModelAndView view = new ModelAndView("JourneyViewPage");
        TicketEntity ticket = TEDAO.findById(TicketType);
        ClassEntity ClassType = CEDAO.findById(ClassCode);
        PriceEntity price = PDAO.findPrice(searchTime,temp,ClassType,ticket,temp.getAirlineCode());
        String fixedArray = fixingString(flightIds);
        view.addObject("FlightID",fixedArray);
        view.addObject("Flights",flights);
        view.addObject("Dep",flights.get(0));
        view.addObject("Des",flights.get(flights.size()-1));
        view.addObject("price",price);
        view.addObject("ticket",ticket);
        view.addObject("ClassType",ClassType);
        view.addObject("Reviews",listReview);
        return view;
    }
    @GetMapping("searchAirport")
    public ModelAndView searchAirport(@RequestParam Map<String,String> allParams){


        ModelAndView view = new ModelAndView("Flight/flight");

        DestinationEntity entity = new DestinationEntity();

        String searchString = allParams.get("search");

        List<DestinationEntity> testSearch = destinationEntitydao.searchByAirportName(searchString);

        for(DestinationEntity dest : testSearch){
            System.out.println(dest.getAirport()  + " code: "+dest.getDestinationCode());
        }

        //view.addObject("results",testSearch);

        //Search airport by name using search criteria

        //Pass results to jsp

        return view;
    }

    @GetMapping("newBooking")
    @ResponseBody
    public ModelAndView newBooking( @RequestParam List<Integer> flightIds,@RequestParam String TicketType,@RequestParam String ClassCode, ModelMap model){
        List<FlightEntity> flights = new LinkedList<>();

        for(Integer id : flightIds){
            flights.add(flightDAO.findById(id));
        }

        //Insert booking details into session
        model.remove("bookedFlights"); //Remove any existing booked flights
        Date date = new Date();
        BookedJourneyEntity journey = new BookedJourneyEntity();
        Timestamp searchTime = new Timestamp(date.getTime());
        model.put("timestamp",searchTime);
        model.put("proposedFlights",flights);   //Insert journey flights
        model.put("ClassCode",ClassCode);
        model.put("TicketType",TicketType);
        model.remove("BookedJourney");
        model.put("BookedJourney",journey);

        return new ModelAndView("forward:./show");
    }


    @GetMapping("show")

    @ResponseBody
    public ModelAndView show(@SessionAttribute("BookedJourney")BookedJourneyEntity journey, ModelMap model,@SessionAttribute("proposedFlights") List<FlightEntity> proposedFlights,@SessionAttribute("TicketType") String TicketType,@SessionAttribute("ClassCode") String ClassCode,@SessionAttribute("timestamp") Timestamp timestamp) {
        ModelAndView view = new ModelAndView("BookingPage");
        if (proposedFlights.isEmpty()) {
            BookingEntity booking = new BookingEntity();
            // booking.setAccountID(id);
            booking.setJourneyID(journey);
            if(!model.containsKey("ActiveUser")){
                model.put("Booking",booking);
             view = new ModelAndView("CreateAccountGuest");
                view.addObject("AccountEntity", new AccountEntity());
                return view;

            }
            AccountEntity user = (AccountEntity)model.get("ActiveUser");
            booking.setAccountID(user);
            bDAO.create(booking);

            model.put("Booking", booking);
            view = new ModelAndView("ConfirmationPage");
            view.addObject("Booking", booking);
            view.addObject("reserved",model.get("reserved"));
            return view;

        }
        //determinign which arrangement and stuff gets sent.
        int offset;
        FlightEntity temp = proposedFlights.get(0);
        planeSeatingArrangementsEntity config = temp.getPlaneCode().getPlaneList().get(0);

        if (ClassCode.equals("BUS")) {
            view.addObject("Arrangement",ChangeArray(config.getBC_arrangement().split(",")));
            view.addObject("Rows", config.getBC_row_count());
            view.addObject("Excluded", fixingString(config.getBC_seats_excluded().split(",")));
            offset = Integer.valueOf(config.getBC_row_range().split(" ", 2)[0]);
            view.addObject("offset", offset);
        }
        if (ClassCode.equals("ECO")) {
            view.addObject("Arrangement",ChangeArray(config.getEE_arrangment().split(",")));
            view.addObject("Rows", config.getEE_row_count());
            view.addObject("Excluded", fixingString(config.getEE_seats_excluded().split(",")));
            offset = Integer.valueOf(config.getEE_row_range().split(" ", 2)[0]);
            view.addObject("offset", offset);
        }
        if (ClassCode.equals("FIR")){
            view.addObject("Arrangement",ChangeArray(config.getFC_arrangement().split(",")));
        view.addObject("Rows", config.getFC_row_count());
        view.addObject("Excluded", fixingString(config.getFC_seats_excluded().split(",")));
        offset = Integer.valueOf(config.getFC_row_range().split(" ", 2)[0]);
            offset++;
        view.addObject("offset", offset);
    }
        if(ClassCode.equals("PME")){
            view.addObject("Arrangement",ChangeArray(config.getPE_arrangement().split(",")));
                view.addObject("Rows",config.getPE_row_count());
                view.addObject("Excluded",fixingString(config.getPE_seats_excluded().split(",")));
                offset = Integer.valueOf(config.getPE_row_range().split(" ",2)[0]);
                view.addObject("offset",offset);
        }
        TicketEntity ticket = TEDAO.findById(TicketType);
        ClassEntity ClassType = CEDAO.findById(ClassCode);
        PriceEntity price = PDAO.findPrice(timestamp,temp,ClassType,ticket,temp.getAirlineCode());
        view.addObject("currentFlight",temp);
        model.put("proposedFlights",proposedFlights);
        model.put("price",price);
        view.addObject("PlaneConfig",config);
        view.addObject("price",price);
        return view;
    }

    public JourneyEntity generateJourney(LinkedList<FlightEntity> flightEntities){
        //List<FlightEntity> FlightResultList = flightEntities;//assuming searchFlight uses (Timestamp start, Timestamp end, DestinationEntity dest1, DestinationEntity dest2

        Iterator<FlightEntity> it = flightEntities.listIterator();
        return null;
    }

    @RequestMapping(value = "bookinghandling", method = RequestMethod.GET)
    public ModelAndView bookinghandling(@RequestParam String hasReserved, ModelMap model,@RequestParam String seatId,@SessionAttribute("proposedFlights") List<FlightEntity> proposedFlights,@SessionAttribute("price") PriceEntity price,@SessionAttribute("BookedJourney") BookedJourneyEntity journey,@SessionAttribute("TicketType") String TicketType,@SessionAttribute("ClassCode") String ClassCode,@SessionAttribute("timestamp") Timestamp timestamp){
        if(journey==null){
            journey = new BookedJourneyEntity();
        }
        model.put("reserved",hasReserved);
        TicketEntity ticket = TEDAO.findById(TicketType);
        ClassEntity ClassType = CEDAO.findById(ClassCode);
        LegEntity newLeg = new LegEntity();
        newLeg.setFlightID((proposedFlights.remove(0)));
        newLeg.setTickettype(ticket);
        newLeg.setTicketclass(ClassType);
        newLeg.setPriceEn(PDAO.findPrice(timestamp,newLeg.getFlightID(),ClassType,ticket,newLeg.getFlightID().getAirlineCode()));
        newLeg.setSeat(seatId);
        journey.addLeg(newLeg);
        model.put("BookedJourney",journey);
        model.put("proposedFlights",proposedFlights);
        return new ModelAndView("forward:./show");
    }

    public Integer[] ChangeArray(String[] array){
        Integer[] arrayInt = new Integer[array.length];
        for(int i=0;i<array.length;i++){
            arrayInt[i]=Integer.valueOf(array[i]);
        }
       return arrayInt;
    }
    public String fixingString(String[] array){
        String output = "[";
        for(int i = 0;i<array.length;i++){
            output=output+"'"+array[i]+"'";
            if(i!=array.length-1){
                output=output+",";
            }
        }
        output=output+"]";
        return output;
    }
    public String fixingString(List<Integer> array){
        String output = "";
        Iterator<Integer> it = array.iterator();
        while(it.hasNext()){
            output=output+Integer.toString(it.next());
            if(it.hasNext()){
                output=output+",";
            }
        }
        output=output;
        return output;
    }
}
