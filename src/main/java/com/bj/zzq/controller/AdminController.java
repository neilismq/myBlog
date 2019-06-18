package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.CommentUserResp;
import com.bj.zzq.service.ArticleTagService;
import com.bj.zzq.service.CommentService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
//@RequestMapping(value = "/admin-admin-admin123456")
public class AdminController {
    @Value(value = "${shiro.loginSuccessUrl}")
    private String adminIndexUrl;

    @Value(value = "${application.img.uploadPath}")
    private String uploadPath;

    @Value(value = "${application.secret}")
    private String salt;


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CommentService commentService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap map) {
        map.put("salt", salt);
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
    public String addArticlePage(ModelMap map) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tagEntities = tagService.selectByExample(tagEntityExample);
        map.put("tags", tagEntities);
        return "admin/article/add";
    }

    /**
     * 请求单个文章内容
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/article/query", method = RequestMethod.POST)
    public CommonResponse queryArticle(String articleId) {
        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        articleEntityExample.createCriteria().andIdEqualTo(articleId);
        List<ArticleEntity> articleEntities = articleDao.selectArticleByExample(articleEntityExample);
        if (articleEntities == null || articleEntities.size() == 0) {
            return CommonResponse.fail("查询文章不存在");
        }
        ArticleEntity articleEntity = articleEntities.get(0);
        List<TagEntity> tagEntities = articleDao.selectTagsByArticleId(articleId);
        articleEntity.setTags(tagEntities);
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tagEntities1 = tagService.selectByExample(tagEntityExample);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("article", articleEntity);
        map.put("allTags", tagEntities1);
        return CommonResponse.success().setBody(map);
    }

    /**
     * 标签下拉框内容
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/article/dropdown/query", method = RequestMethod.POST)
    public CommonResponse queryArticleSelect(String articleId) {

        List<TagEntity> tagEntities = articleDao.selectTagsByArticleId(articleId);
        HashSet<String> selectIds = new HashSet<>();
        for (int i = 0; i < tagEntities.size(); i++) {
            selectIds.add(tagEntities.get(i).getId());
        }
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tagEntities1 = tagService.selectByExample(tagEntityExample);
        ArrayList<Map> maps = new ArrayList<>();
        for (int i = 0; i < tagEntities1.size(); i++) {
            TagEntity tagEntity = tagEntities1.get(i);
            HashMap<Object, Object> item = new HashMap<>();
            item.put("id", tagEntity.getId());
            item.put("text", tagEntity.getName());
            if (selectIds.contains(tagEntity.getId())) {
                item.put("selected", true);
            }
            maps.add(item);
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("results", maps);
        return CommonResponse.success().setBody(map);
    }

    /**
     * 管理文章页面（无参数时用）
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/article/manage", method = RequestMethod.GET)
    public String manageArticlePage(ModelMap map) {
        return manageArticlePageByPageNum(map, 1, null);
    }

    /**
     * 管理文章页面（无参数时用）
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/article/manage/search", method = RequestMethod.GET)
    public String searchArticlePage(ModelMap map, String selectKey) {
        return manageArticlePageByPageNum(map, 1, selectKey);
    }


    /**
     * 管理文章页面(分页用)
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/article/manage/{pageNum}", method = RequestMethod.GET)
    public String manageArticlePageByPageNum(ModelMap map, @PathVariable Integer pageNum, String selectKey) {
        //默认每页显示10条数据
        int pageSize = 10;
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        HashMap<String, String> mapParam = new HashMap<>();
        if (StringUtils.isNotBlank(selectKey)) {
            mapParam.put("selectKey", "%" + selectKey + "%");
        }
        List<ArticleEntity> articleEntities = articleDao.selectArticleWithCommentCount(mapParam);
        for (int i = 0; i < articleEntities.size(); i++) {
            ArticleEntity articleEntity = articleEntities.get(i);
            List<TagEntity> tagEntities = articleDao.selectTagsByArticleId(articleEntity.getId());
            articleEntity.setTags(tagEntities);
        }
        PageInfo<ArticleEntity> pageInfo = new PageInfo<>(articleEntities, 5);
        map.put("pageInfo", pageInfo);
        if (StringUtils.isNotBlank(selectKey)) {
            map.put("selectKey", selectKey);
        }
        return "admin/article/manage";
    }


    /**
     * 保存文章页面
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/article/save", method = RequestMethod.POST)
    public CommonResponse saveArticle(ModelMap map, String articleId, String title, String[] tagIds, String content, String isDraft) {

        ArticleEntity articleEntity = new ArticleEntity();
        if (StringUtils.isBlank(articleId)) {
            articleEntity.setId(CommonUtils.newUUID());
            articleEntity.setCreateTime(new Date());
        } else {
            articleEntity.setId(articleId);
            articleEntity.setUpdateTime(new Date());
        }
        if (StringUtils.isNotBlank(content)) {
            articleEntity.setContent(content);
        }
        if (StringUtils.isNotBlank(title)) {
            articleEntity.setTitle(title);
        }
        articleEntity.setAuthor("赵志强");
        articleEntity.setIsDraft(isDraft);
        if (StringUtils.isBlank(articleId)) {
            articleDao.insertArticle(articleEntity);
        } else {
            ArticleEntityExample articleEntityExample = new ArticleEntityExample();
            articleEntityExample.createCriteria().andIdEqualTo(articleId);
            articleDao.updateArticle(articleEntity, articleEntityExample);
        }
        if (StringUtils.isNotBlank(articleId)) {
            //先删除所有标签和文章的映射
            articleTagService.deleteByArticleId(articleId);
        }
        if (tagIds != null && tagIds.length > 0) {
            for (int i = 0; i < tagIds.length; i++) {
                String tagId = tagIds[i];
                ArticleTagEntity articleTagEntity = new ArticleTagEntity();
                articleTagEntity.setId(CommonUtils.newUUID());
                articleTagEntity.setTagId(tagId);
                articleTagEntity.setArticleId(articleEntity.getId());
                articleTagService.insert(articleTagEntity);
            }
        }
        return CommonResponse.success();
    }


    /**
     * 删除文章
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/article/delete", method = RequestMethod.POST)
    public CommonResponse deleteArticle(String articleId) {
        articleDao.deleteArticleById(articleId);
        return CommonResponse.success();
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
        String baseUrl = (String) request.getAttribute("baseUrl") + "img/";
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
        return "admin/article/query";
    }

    /**
     * 标签管理页面
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/tag/manage/{pageNum}", method = RequestMethod.GET)
    public String tagAdd(ModelMap mav, @PathVariable Integer pageNum, String selectKey) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        TagEntityExample tagEntityExample = new TagEntityExample();
        TagEntityExample.Criteria criteria = tagEntityExample.createCriteria();
        if (StringUtils.isNotBlank(selectKey)) {
            criteria.andNameLike("%" + selectKey + "%");
        }
        tagEntityExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, 10);
        List<TagEntity> tags = tagService.selectByExample(tagEntityExample);
        PageInfo<TagEntity> pageInfo = new PageInfo<>(tags);
        mav.put("pageInfo", pageInfo);
        if (selectKey != null) {
            mav.put("selectKey", selectKey);
        }
        return "admin/tag/manage";
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

    /**
     * 评论管理页面
     *
     * @param mav
     * @param selectKey
     * @return
     */
    @RequestMapping(value = "/comment/manage/{pageNum}", method = RequestMethod.GET)
    public String commentManage(ModelMap mav, String selectKey, @PathVariable Integer pageNum) {

        HashMap<String, String> map = new HashMap<>();
        if (StringUtils.isNotBlank(selectKey)) {
            map.put("selectKey", "%" + selectKey + "%");
            mav.put("selectKey", selectKey);
        }
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 7);
        List<CommentUserResp> commentUserResps = commentService.selectAllCommentResp(map);
        mav.put("pageInfo", new PageInfo<>(commentUserResps));
        return "admin/comment/manage";
    }

    /**
     * 删除评论
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment/manage/delete", method = RequestMethod.POST)
    public CommonResponse commentManage(String commentId) {
        commentService.deleteById(commentId);
        return CommonResponse.success();
    }

}
