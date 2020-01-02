package cn.vtyc.ehs.controller.award;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.JSONResult;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.core.jqGrid.JqGridResult;
import cn.vtyc.ehs.dao.first.AwardDao;
import cn.vtyc.ehs.dao.first.PeopleDao;
import cn.vtyc.ehs.dto.AccidentTypeJqGridParam;
import cn.vtyc.ehs.entity.AccidentLevel;
import cn.vtyc.ehs.entity.award.Award;
import cn.vtyc.ehs.entity.award.People;
import cn.vtyc.ehs.service.AwardService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/awardBackstage")
public class BackstageAwardController extends BaseController {

    @Autowired
    private AwardService awardService;

    @RequestMapping(value = "/award")
    public String deptList(Model model){
        model.addAttribute("menus", getMenus("award"));
        return "/award/list";
    }

    @RequestMapping(value = "/grid")
    @ResponseBody
    public Result grid() {
        PageInfo<Award> pageInfo = awardService.selectByJqGridParam();
        JqGridResult<Award> result = new JqGridResult<>();
        //当前页
        result.setPage(pageInfo.getPageNum());
        //数据总数
        result.setRecords(pageInfo.getTotal());
        //总页数
        result.setTotal(pageInfo.getPages());
        //当前页数据
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    @RequestMapping(value = "/award/delete")
    @ResponseBody
    public Result awardDelete(@RequestParam Integer id) {
        awardService.delete(id);
        return OK;
    }

    @RequestMapping("/award/insert")
    @ResponseBody
    public Result insert(@Valid @RequestBody JSONObject dto) {
        String level = dto.getString("level");
        String levelDesc = dto.getString("level_desc");
        String department = dto.getString("department");
        Integer dept_num = dto.getInteger("dept_num");

        Award award = new Award(level,levelDesc,department,dept_num);
        awardService.insert(award);
        return OK;
    }

    @RequestMapping("/award/get")
    @ResponseBody
    public Result get(@RequestParam Integer id) {
        return new JSONResult(awardService.select(id));
    }

    @RequestMapping("/award/update")
    @ResponseBody
    public Result update(@Valid @RequestBody JSONObject dto) {
        Integer id = dto.getInteger("id");
        String level = dto.getString("level");
        String levelDesc = dto.getString("level_desc");
        String department = dto.getString("department");
        Integer dept_num = dto.getInteger("dept_num");
        Award award = new Award();
        award.setId(id);
        award.setLevel(level);
        award.setLevelDesc(levelDesc);
        award.setDepartment(department);
        award.setDeptNum(dept_num);
        awardService.update(award);
        return OK;
    }
}
