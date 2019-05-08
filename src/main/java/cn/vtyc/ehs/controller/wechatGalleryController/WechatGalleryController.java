package cn.vtyc.ehs.controller.wechatGalleryController;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.JSONResult;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.dao.*;
import cn.vtyc.ehs.dto.EhsDto;
import cn.vtyc.ehs.entity.Ehs;
import cn.vtyc.ehs.entity.Image;
import cn.vtyc.ehs.entity.WechatGallery;
import cn.vtyc.ehs.service.DeptService;
import cn.vtyc.ehs.util.MyFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping(value = "/weChatGallery")
public class WechatGalleryController extends BaseController {

    @Autowired
    private WechatGalleryDao wechatGalleryDao;
    @Autowired
    private WechatInfoDao wechatInfoDao;


    @RequestMapping(value = "/weChatGallery")
    public String list(Model model,@RequestParam Integer type){
//        System.out.println(wechatInfoDao.selectWechatInfoByType(type));
        model.addAttribute("info",wechatInfoDao.selectWechatInfoByType(type) );
        model.addAttribute("images",wechatGalleryDao.selectWechatGalleryByType(type) );
        model.addAttribute("carousels",wechatInfoDao.selectWechatInfoByType(type).getCarousel().split("\\|"));
        return "/weChatGallery/gallery";
    }

    /**
     * http://127.0.0.1:8080/addPictures?nums=?&type=?
     * @param nums
     * @param type
     * @return
     */
    @RequestMapping(value = "/addPictures")
    public Result list(@RequestParam Integer nums,@RequestParam Integer type){
        List<WechatGallery> wechatGalleries = new ArrayList<>();
        for(int i=1;i<=nums;i++){
            WechatGallery wechatGallery = new WechatGallery("type"+type+"/"+i+"s.JPG","type"+type+"/"+i+".JPG",type);
            wechatGalleries.add(wechatGallery);
        }
        wechatGalleryDao.insertList(wechatGalleries);
        return OK;
    }

}
