package hourlyworkapplication.hourlyworkapp.web;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hourlyworkapplication.hourlyworkapp.model.TimeLogRepository;
import hourlyworkapplication.hourlyworkapp.model.WorkTypeRepository;
import hourlyworkapplication.hourlyworkapp.model.AppUserRepository;
import hourlyworkapplication.hourlyworkapp.model.TimeLog;
import hourlyworkapplication.hourlyworkapp.model.WorkType;
import hourlyworkapplication.hourlyworkapp.model.AppUser;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class LogTimeController {

    @Autowired
    private TimeLogRepository timeLogRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
	private AppUserRepository appUserRepository;
    
    @RequestMapping("/adminTimeLogList")
    public String timeLogList(Model model) {
        model.addAttribute("logTimeList", timeLogRepository.findAll());
        return "adminTimeLogList";
    }

    @RequestMapping("/index")
    public String mainPage() {
        return "index";
    }

    // RESTful service to get all loggedTimes
    @RequestMapping(value="/allLoggedTimes", method = RequestMethod.GET)
    public @ResponseBody List<TimeLog> bookListRest() {	
        return (List<TimeLog>) timeLogRepository.findAll();
    }

    @RequestMapping(value="/login")
	public String login() {
		return "login";
	}

    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("timeLog", new TimeLog());
        model.addAttribute("workTypes", workTypeRepository.findAll());
        return "addTimeLog";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TimeLog timeLog) {

        if (timeLog.getId() == null) {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();
            AppUser userNow = appUserRepository.findByUsername(username);
            timeLog.setUser(userNow);
            timeLogRepository.save(timeLog);
            return "redirect:/timeLogList";
        } else {
            timeLogRepository.save(timeLog);
            return "redirect:/adminTimeLogList";
        }

	}
    
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String deleteTimeLog(@PathVariable("id") Long timeLogId, Model model) {
        timeLogRepository.deleteById(timeLogId);        
        return "redirect:/adminTimeLogList";
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public String editTimeLog(@PathVariable("id") Long timeLogId, Model model) {
        Optional<TimeLog> timeLogFromDataBase = timeLogRepository.findById(timeLogId);

        if (timeLogFromDataBase.isPresent()) {
            model.addAttribute("timeLog", timeLogFromDataBase.get());
            model.addAttribute("workTypes", workTypeRepository.findAll());
            return "editTimeLog";
        }       

        return "redirect:/adminTimeLogList";
    }
    
    
    @RequestMapping(value = "/timeLogList")
    public String showLogTimeList(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        AppUser userNow = appUserRepository.findByUsername(username);
        model.addAttribute("timeLogs", timeLogRepository.findByUser(userNow));
        return  "timeLogList";
    }
}
