package cn.vtyc.ehs.controller.wechatGalleryController;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.dao.first.WechatGalleryDao;
import cn.vtyc.ehs.dao.first.WechatInfoDao;
import cn.vtyc.ehs.entity.WechatGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping(value = "/weChatGallery")
public class WechatGalleryController extends BaseController {

    @Autowired
    private WechatGalleryDao wechatGalleryDao;
    @Autowired
    private WechatInfoDao wechatInfoDao;


    @RequestMapping(value = "/weChatGallery")
    public String list(Model model,@RequestParam Integer type){
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
            WechatGallery wechatGallery = new WechatGallery("type"+type+"/"+i+"s.jpg","type"+type+"/"+i+".jpg",type);
            wechatGalleries.add(wechatGallery);
        }
        wechatGalleryDao.insertList(wechatGalleries);
        return OK;
    }

}
