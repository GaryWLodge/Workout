package com.lodge.Workout.Controller;


import com.lodge.Workout.Model.Comments;
import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.data.CommentDao;
import com.lodge.Workout.Model.data.ScheduleDao;
import com.lodge.Workout.Model.data.UserDao;
import com.lodge.Workout.Model.forms.AddCommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userdao;

    @Autowired
    private CommentDao commentdao;

    @RequestMapping(value = "")
    public String index(Model model,String username) {

        model.addAttribute("schedules", scheduleDao.findAll());
        model.addAttribute("title", "All Workout Plans");
        model.addAttribute("users", userdao.findAll());



        return "home/index";
    }

    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewItem(Model model , @PathVariable int scheduleId) {


        Schedule schedule = scheduleDao.findOne(scheduleId);
        User user = userdao.findOne(scheduleId);
        model.addAttribute("title", schedule.getName());
        model.addAttribute("workouts", schedule.getWorkouts());
        model.addAttribute("scheduleId", schedule.getId());
        model.addAttribute("users", user);
        model.addAttribute("comments", schedule.getComments());

        return "home/view";
    }

    @RequestMapping(value = "viewuser/{homeId}", method = RequestMethod.GET)
    public String userItem(Model model , @PathVariable int homeId, String username) {

        User user = userdao.findOne(homeId);
        model.addAttribute("title", user.getUsername() +" "+ "Workout Plans");
        model.addAttribute("schedules", user.getSchedules());


        return "home/viewuser";
    }

    @RequestMapping(value = "add-comment/{scheduleId}", method = RequestMethod.GET)
    public String add(Model model, @PathVariable int scheduleId){
        model.addAttribute("title", "Add Comment");
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute(new Comments());
        Schedule s = scheduleDao.findById(scheduleId).get(0);
        AddCommentForm form = new AddCommentForm(commentdao.findAll(),s);
        model.addAttribute("form", form);
        return "home/add-comment";
    }

    @RequestMapping(value = "add-comment", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid  AddCommentForm form ,Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Comment");
            model.addAttribute("form", form);
            return "home/add-comment";
        }

        Schedule theSchedule = scheduleDao.findOne(form.getScheduleId());

        commentdao.save(comments);
        return "redirect: /home/view/" + theSchedule.getId();
    }

}
