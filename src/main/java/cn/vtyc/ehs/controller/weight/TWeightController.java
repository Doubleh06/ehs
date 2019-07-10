package cn.vtyc.ehs.controller.weight;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.JSONResult;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.core.jqGrid.JqGridResult;
import cn.vtyc.ehs.dto.TWeightJqGridParam;
import cn.vtyc.ehs.entity.TWeight;
import cn.vtyc.ehs.service.TWeightService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tweight")
public class TWeightController extends BaseController {

    @Autowired
    private TWeightService tWeightService;

    @RequestMapping(value = "/detail")
    public String tweightList(Model model){
        model.addAttribute("menus", getMenus("detail"));
        model.addAttribute("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "/tweight/list";
    }

    @RequestMapping(value = "/detail/grid")
    @ResponseBody
    public Result grid( TWeightJqGridParam param) {
        PageInfo<TWeight> pageInfo = tWeightService.selectByJqGridParam(param);
        JqGridResult<TWeight> result = new JqGridResult<>();
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

    @RequestMapping(value = "/sum")
    public String tweighSumList(Model model){
        model.addAttribute("menus", getMenus("sum"));
        model.addAttribute("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "/tweight/sum";
    }

    @RequestMapping(value = "/sum/grid")
    @ResponseBody
    public Result sumGrid( TWeightJqGridParam param) {
        PageInfo<Map> pageInfo = tWeightService.selectSumByJqGridParam(param);
        JqGridResult<Map> result = new JqGridResult<>();
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

    @RequestMapping(value = "/sum/export")
    @ResponseBody
    public Result export(HttpServletResponse response, @RequestParam String date) throws IOException {
        //组织查询语句
        TWeightJqGridParam param = new TWeightJqGridParam();
        param.setDate(date);
        List<TWeight> tWeightList = tWeightService.selectListByJqGridParam(param);
        List<Map> tWeightSumList = tWeightService.selectListSumByJqGridParam(param);


        //excel 表头
        String[] columns = new String[]{"类型", "重量", "时间"};
        String[] columns2 = new String[]{"类型", "总重量"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");

        //创建表头
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        //i-行   j-列
        for (int i = 0; i < tWeightList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < columns.length; j++) {
                row.createCell(0).setCellValue(tWeightList.get(i).getFmaterial());
                row.createCell(1).setCellValue(tWeightList.get(i).getFweight());
                row.createCell(2).setCellValue(sdf.format(tWeightList.get(i).getFdatetime()));
            }
        }
        HSSFRow header2 = sheet.createRow(tWeightList.size()+2);
        double sum = 0;
        for (int i = 0; i < columns2.length; i++) {
            header2.createCell(i).setCellValue(columns2[i]);
        }
        for (int i = 0; i < tWeightSumList.size(); i++) {
            HSSFRow row = sheet.createRow(tWeightList.size() + i + 3);
            for (int j = 0; j < columns2.length; j++) {
                row.createCell(0).setCellValue(tWeightSumList.get(i).get("FMaterial").toString());
                row.createCell(1).setCellValue(tWeightSumList.get(i).get("FWeight").toString());
            }
            sum += Double.parseDouble(tWeightSumList.get(i).get("FWeight").toString());
        }
        HSSFRow row = sheet.createRow(tWeightList.size() + tWeightSumList.size() + 4);
        row.createCell(0).setCellValue("合计");
        row.createCell(1).setCellValue(sum);

        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=summary.xls");
//        response.setContentType("application/msexcel");
        response.setContentType("application/json");
        wb.write(output);
        tWeightList.clear();
        output.close();
        return OK;
    }
}
