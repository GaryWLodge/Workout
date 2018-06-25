package com.lodge.Workout.Controller;

import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.Workout;
import com.lodge.Workout.Model.data.ScheduleDao;
import com.lodge.Workout.Model.data.UserDao;
import com.lodge.Workout.Model.data.WorkoutDao;
import com.lodge.Workout.Model.forms.AddScheduleItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userdao;

    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);

        model.addAttribute("schedules", u.getSchedules());
        model.addAttribute("title", "My Workout Plans");

        return "schedule/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        model.addAttribute("title", "Add Workout Plan");
        model.addAttribute(new Schedule());
        return "schedule/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Schedule schedule,
                      Errors errors, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Workout Plan");
            return "schedule/add";
        }
        User u = userdao.findByUsername(username).get(0);
        schedule.setUser(u);
        scheduleDao.save(schedule);
        return "redirect:view/" + schedule.getId();
    }

    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewItem(Model model , @PathVariable int scheduleId,
                           @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        Schedule schedule = scheduleDao.findOne(scheduleId);
        model.addAttribute("title",  schedule.getName());
        model.addAttribute("workouts", schedule.getWorkouts());
        model.addAttribute("scheduleId", schedule.getId());
        return "schedule/view";
    }

    @RequestMapping(value = "add-item/{scheduleId}", method = RequestMethod.GET)
    public String addScheduleItem(Model model , @PathVariable int scheduleId,
                                  @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);
        Schedule schedule = scheduleDao.findOne(scheduleId);

        AddScheduleItemForm form =  new AddScheduleItemForm(u.getWorkouts(), schedule);

        model.addAttribute("title", "Add item to Workout Plan: " + schedule.getName());
        model.addAttribute("form" , form);
        return "schedule/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String AddScheduleForm(Model model , @ModelAttribute @Valid AddScheduleItemForm form,
                              Errors errors,
                                  @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "schedule/add-item";
        }
        Workout theWorkout = workoutDao.findOne(form.getWorkoutId());
        Schedule theSchedule = scheduleDao.findOne(form.getScheduleId());
        theSchedule.addItem(theWorkout);
        theSchedule.setUser(u);
        scheduleDao.save(theSchedule);

        return "redirect:/schedule/view/" + theSchedule.getId();
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveWorkoutForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);
        model.addAttribute("schedules", u.getSchedules());
        model.addAttribute("title", "Remove Plan");
        return "schedule/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveWorkoutForm(@RequestParam int[] scheduleId) {

        for (int schedule : scheduleId) {
            scheduleDao.delete(schedule);
        }

        return "redirect:";
    }
}
