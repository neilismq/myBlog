package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.model.FileEntity;
import com.bj.zzq.model.TagEntity;
import com.bj.zzq.model.TagEntityExample;
import com.bj.zzq.service.ArticleTagService;
import com.bj.zzq.service.FileService;
import com.bj.zzq.service.TagService;
import com.bj.zzq.utils.CommonResponse;
import com.bj.zzq.utils.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
//@RequestMapping(value = "/admin-admin-admin123456")
public class AdminController {
    @Value(value = "${shiro.loginSuccessUrl}")
    private String adminIndexUrl;

    @Value(value = "${application.img.uploadPath}")
    private String uploadPath;


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private FileService fileService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "admin/index";
    }

    /**
     * 添加文章页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/article/add", method = RequestMethod.GET)
    public String articleAdd(ModelMap map) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tagEntities = tagService.selectByExample(tagEntityExample);
        map.put("tags", tagEntities);
        return "admin/articleManage/add";
    }

    /**
     * 上传图片(支持批量上传)
     *
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/article/upload")
    public CommonResponse uploadImg(HttpServletRequest httpServletRequest) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        String dateStr2 = sdf2.format(new Date());
        StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) httpServletRequest;

        List<MultipartFile> file = request.getFiles("file");
        String baseUrl = (String) request.getAttribute("baseUrl")+"img/";
        String allImgPath = "";
        for (int i = 0; i < file.size(); i++) {
            MultipartFile entity = file.get(i);
            //文件类型
            String contentType = entity.getContentType();
            //文件原名称
            String originalFilename = entity.getOriginalFilename();
            //文件大小,单位-字节
            long size = entity.getSize();
            if (!CommonUtils.isImg(contentType)) {
                return CommonResponse.fail("文件：" + originalFilename + "类型不正确！");
            }

            //如果图片大于30M，禁止上传
            if (size > 30 * 1024 * 1024) {
                return CommonResponse.fail("图片太大，不能上传（最大30M）");
            }
            String newFileName = dateStr + "-" + originalFilename;
            FileEntity fileEntity = new FileEntity();
            fileEntity.setId(CommonUtils.newUUID());
            fileEntity.setName(newFileName);
            fileEntity.setOriginName(originalFilename);
            fileEntity.setSize((int) size);
            String path;
            path = dateStr2 + "/";
            byte[] bytes = entity.getBytes();
            File filePath = new File(uploadPath + path);
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdirs();
            }
            path = path + newFileName;
            File realFile = new File(uploadPath + path);
            fileEntity.setPath(path);
            fileEntity.setCreateTime(new Date());
            fileService.insert(fileEntity);
            if (!realFile.exists()) {
                realFile.createNewFile();
            }
            allImgPath += "," + baseUrl + path;
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(realFile));
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
        if (allImgPath.startsWith(",")) {
            allImgPath = allImgPath.substring(1);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("imgUrl", allImgPath);
        return CommonResponse.success().setBody(map);
    }

    /**
     * 查询文章页面
     *
     * @param map
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/article/query")
    public String articleQuery(Map map, PageInfo pageInfo) {
        if (pageInfo.getPageSize() == 0) {
            pageInfo.setNavigatePages(5);
            pageInfo.setPageSize(10);
            pageInfo.setPageNum(1);
        }
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        //分页合理化
        page.setReasonable(true);

        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        articleEntityExample.setOrderByClause("create_time desc");
        articleDao.selectArticleByExample(articleEntityExample);

        PageInfo info = new PageInfo(page, pageInfo.getNavigatePages());
        BeanUtils.copyProperties(info, pageInfo);
        map.put("pageInfo", pageInfo);
        return "admin/articleManage/query";
    }

    /**
     * 添加标签页面
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/tag/add", method = RequestMethod.GET)
    public String tagAdd(ModelMap mav) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tags = tagService.selectByExample(tagEntityExample);
        mav.put("tags", tags);
        return "admin/tagManage/add";
    }

    /**
     * 添加标签
     *
     * @param tagName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tag/add", method = RequestMethod.POST)
    public CommonResponse addTag(String tagName) {

        if (StringUtils.isBlank(tagName)) {
            return CommonResponse.fail("标签名称不能为空");
        }
        TagEntityExample example = new TagEntityExample();
        example.createCriteria().andNameEqualTo(tagName);
        List<TagEntity> tagEntities = tagService.selectByExample(example);
        if (tagEntities != null && tagEntities.size() > 0) {
            return CommonResponse.fail("标签已存在，请检查");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(CommonUtils.newUUID());
        tagEntity.setName(tagName);
        tagEntity.setCreateTime(new Date());
        tagService.insert(tagEntity);
        return CommonResponse.success();
    }

    /**
     * 修改标签
     *
     * @param tagId
     * @param tagName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tag/update", method = RequestMethod.POST)
    public CommonResponse updateTag(String tagId, String tagName) {

        if (StringUtils.isBlank(tagName)) {
            return CommonResponse.fail("标签名称不能为空");
        }
        TagEntityExample example = new TagEntityExample();
        example.createCriteria().andNameEqualTo(tagName);
        List<TagEntity> tagEntities = tagService.selectByExample(example);
        if (tagEntities != null && tagEntities.size() > 0) {
            return CommonResponse.fail("标签已存在，请检查");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagName);
        tagEntity.setCreateTime(new Date());
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.createCriteria().andIdEqualTo(tagId);
        tagService.update(tagEntity, tagEntityExample);
        return CommonResponse.success();
    }

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public CommonResponse deleteTag(String tagId) {

        if (StringUtils.isBlank(tagId)) {
            return CommonResponse.fail("标签id不能为空");
        }
        //删除中间表数据
        articleTagService.deleteByTagId(tagId);
        //删除tag
        tagService.deleteById(tagId);
        return CommonResponse.success();
    }


}
