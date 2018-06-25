package com.lodge.Workout.Controller;

import com.lodge.Workout.Model.DurationSet;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.Workout;
import com.lodge.Workout.Model.data.DurationSetDao;
import com.lodge.Workout.Model.data.UserDao;
import com.lodge.Workout.Model.data.WorkoutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("workout")
public class WorkoutController {

    @Autowired
    private WorkoutDao workoutDao;

    @Autowired
    private DurationSetDao durationsetDao;

    @Autowired
    private UserDao userdao;


    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);
        model.addAttribute("workouts", u.getWorkouts());
        model.addAttribute("title", "My Workouts");

        return "workout/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddWorkoutForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);
        model.addAttribute("title", "Add Workout");
        model.addAttribute(new Workout());
        model.addAttribute("durationsets", u.getDurationSets());
        return "workout/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddWorkoutForm(
            @ModelAttribute @Valid Workout newWorkout,
            Errors errors,
            @RequestParam int durationId,
            Model model , @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Workout");
            return "workout/add";
        }
        DurationSet sets = durationsetDao.findOne(durationId);
        newWorkout.setDurationSet(sets);
        newWorkout.setUser(u);
        workoutDao.save(newWorkout);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveWorkoutForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);
        model.addAttribute("workouts", u.getWorkouts());
        model.addAttribute("title", "Remove Workout");
        return "workout/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveWorkoutForm(@RequestParam int[] workoutIds) {

        for (int id : workoutIds) {
            workoutDao.delete(id);
        }

        return "redirect:";
    }
}
