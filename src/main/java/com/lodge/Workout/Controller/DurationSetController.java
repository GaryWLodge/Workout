package com.lodge.Workout.Controller;

import com.lodge.Workout.Model.DurationSet;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.data.DurationSetDao;
import com.lodge.Workout.Model.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("durationset")
public class DurationSetController {

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

        model.addAttribute("durationsets", u.getDurationSets());
        model.addAttribute("title", "Duration/Set");

        return "durationset/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDurationSetForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        model.addAttribute("title", "Add Duration/Set");
        model.addAttribute("durationset" ,new DurationSet());
        return "durationset/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDurationSetForm(@ModelAttribute @Valid DurationSet newDurationSet,
                                         Errors errors, Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        User u = userdao.findByUsername(username).get(0);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Duration/Set");
            return "durationset/add";
        }
        newDurationSet.setUser(u);
        durationsetDao.save(newDurationSet);
        return "redirect:";
    }
}
