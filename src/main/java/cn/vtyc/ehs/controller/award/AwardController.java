package cn.vtyc.ehs.controller.award;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.JSONResult;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.dao.first.*;
import cn.vtyc.ehs.entity.WechatGallery;
import cn.vtyc.ehs.entity.award.Award;
import cn.vtyc.ehs.entity.award.People;
import cn.vtyc.ehs.entity.award.WonPeople;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/award")
public class AwardController extends BaseController {

    @Autowired
    private AwardDao awardDao;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private WonPeopleDao wonPeopleDao;


    @RequestMapping(value = "/show")
    public String list(Model model){
        model.addAttribute("departments",awardDao.getAllDepartment());
        return "/award/award";
    }

    @RequestMapping(value = "/getAwardLevel")
    @ResponseBody
    public Result getAwardLevel(@RequestParam String department){
        List<Award> award = awardDao.getAwardLevel(department);
        List<String> names = new ArrayList<>();
        List<String> showNames = new ArrayList<>();
        if ("全部".equals(department)){
            names = peopleDao.selectAllList();
            showNames = peopleDao.selectALLShowList();
        }else{
            names = peopleDao.selectIsWinList(department);
            showNames = peopleDao.selectShowList(department);
        }
        return new JSONResult(award,names,showNames);
    }

    @RequestMapping(value = "/getAwardNum")
    @ResponseBody
    public Result getAwardNum(@RequestBody JSONObject jsonObject) {
        String dept = jsonObject.getString("dept");
        String award = jsonObject.getString("award");
        String nd = awardDao.getAwardND(award.split("-")[0],dept);
        String desc = awardDao.getAwardDesc(award.split("-")[0],dept);
        return new JSONResult(nd,desc);
    }

    @RequestMapping(value = "/updateWinPeople")
    @ResponseBody
    public Result updateWinPeople(@RequestBody JSONObject jsonObject) {
        List<String> names= new ArrayList<>(Arrays.asList(jsonObject.getString("names").split("\\|")));
        //更新状态
        peopleDao.updateWinPeople(names);
        String dept = jsonObject.getString("dept");
        String award = jsonObject.getString("award");
        //记录中奖状态
        List<WonPeople> wonPeopleList = new ArrayList<>();
        for(String name:names){
            wonPeopleList.add(new WonPeople(dept,award,name));
        }
        wonPeopleDao.insertList(wonPeopleList);
        return OK;
    }
}
