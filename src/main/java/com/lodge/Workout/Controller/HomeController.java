package com.lodge.Workout.Controller;


import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.data.ScheduleDao;
import com.lodge.Workout.Model.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userdao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("schedules", scheduleDao.findAll());
        model.addAttribute("title", "All Workout Plans");

        return "home/index";
    }

    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewItem(Model model , @PathVariable int scheduleId) {


        Schedule schedule = scheduleDao.findOne(scheduleId);
        model.addAttribute("title", schedule.getName());
        model.addAttribute("workouts", schedule.getWorkouts());
        model.addAttribute("scheduleId", schedule.getId());
        return "home/view";
    }
}
