package com.lodge.Workout.Controller;


import com.lodge.Workout.Model.Comments;
import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.Voted;
import com.lodge.Workout.Model.data.CommentDao;
import com.lodge.Workout.Model.data.ScheduleDao;
import com.lodge.Workout.Model.data.UserDao;
import com.lodge.Workout.Model.data.VotedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userdao;

    @Autowired
    private CommentDao commentdao;

    @Autowired
    private VotedDao votedDao;

    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);
        model.addAttribute("schedules", scheduleDao.findAllByOrderByVoteDesc());
        model.addAttribute("title", "All Workout Plans");
        model.addAttribute("user",username);
        model.addAttribute("voted",votedDao.findAll());

        ArrayList<Schedule> votelist = new ArrayList<>();



        model.addAttribute("votelist",votelist);


        for (Schedule schedule : scheduleDao.findAllByOrderByVoteDesc()){
             for ( Voted vote : schedule.getVotes()){
                 if (vote.getUser().equals(u) && schedule.equals(vote.getSchedule()) ){
                     model.addAttribute("voted" , true);
                     model.addAttribute("schedule" , schedule);
                 }else{
                     model.addAttribute("voted" , false);
                     model.addAttribute("schedule" , schedule);
                 }
             }

        }






        return "home/index";
    }

    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewItem(Model model , @PathVariable int scheduleId, Comments comments) {


        Schedule schedule = scheduleDao.findOne(scheduleId);
        User user = userdao.findOne(scheduleId);
        model.addAttribute("title", schedule.getName());
        model.addAttribute("workouts", schedule.getWorkouts());
        model.addAttribute("scheduleId", scheduleId);
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
    public String add(Model model, @PathVariable int scheduleId, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        model.addAttribute("title", "Add Comment to ");
        Schedule schedule = scheduleDao.findOne(scheduleId);
        model.addAttribute("scheduleId", schedule.getName());
        model.addAttribute("username", username);
        model.addAttribute(new Comments());
        return "home/add-comment";
    }

    @RequestMapping(value = "add-comment/{scheduleId}", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid  @PathVariable int scheduleId , Comments comments
            , Errors errors, @CookieValue(value = "user", defaultValue = "none") String username) {


        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Comment");
            model.addAttribute("scheduleId", scheduleId);
            model.addAttribute("username", username);
            return "home/add-comment";
        }

        Schedule theSchedule = scheduleDao.findOne(scheduleId);
        comments.setSchedule(theSchedule);
        commentdao.save(comments);
        return "redirect:/home/view/" + scheduleId;
    }

    @RequestMapping(value = "up/{scheduleId}", method = RequestMethod.GET)
    public String up(Model model, @ModelAttribute @PathVariable int scheduleId
            , Voted voted,@CookieValue(value = "user", defaultValue = "none") String username) {


        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);
        Schedule schedule = scheduleDao.findOne(scheduleId);
        int vote = schedule.getVote();
        schedule.setVote(vote + 1);

        scheduleDao.save(schedule);
        model.addAttribute(new Voted());
        voted.setSchedule(schedule);
        voted.setUser(u);
        votedDao.save(voted);
        return "redirect:/home";
    }

    @RequestMapping(value = "down/{scheduleId}", method = RequestMethod.GET)
    public String down(Model model, @ModelAttribute @PathVariable int scheduleId
            , Voted voted, @CookieValue(value = "user", defaultValue = "none") String username) {


        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);
        Schedule schedule = scheduleDao.findOne(scheduleId);
        int vote = schedule.getVote();
        schedule.setVote(vote - 1);

        scheduleDao.save(schedule);
        model.addAttribute(new Voted());
        voted.setSchedule(schedule);
        voted.setUser(u);
        votedDao.save(voted);

        return "redirect:/home";
    }
}
