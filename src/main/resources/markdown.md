[TOC]
## markdown
　　加你范德萨范德萨房贷首付大师傅大师傅大师傅但是范德萨范德萨范德萨发达是
## 4543543543
### 453534543
#### 543534543543

1. 111
	1. fdsfds
		*. fdfds
	2. dfds

反倒

- fds
	- 工发得
	- aaa
		- gdgd
		- fdsfds
		
#一级标题 
## 二级标题
### 三级标题
#### 四级标题
#####五级标题
######六级标题
1111111


Markdown[^1]



===
2222
--
>hello world!
>>hello world!
>>>hello world!

`hello world!111111111111111111111111111111111111111111111111111111111111111111` 
dfdsfdsgfdgd

 标记之外 ` 
< div>   
    < div></div>
    < div></div>
    < div></div>
< /div>
`标记之外 

```
fdsfdsfds

fdfdsfd
```

dfdsfds	fdf	

	fdsfd
		sfdsf
	dfdfds

    dsfdsfsd
    dfdsfdsf
    
    fdsfds

```

<div style="background-color:green">   
    <div style="color:red">fdsfds</div>
    <div>fsfs</div>
    <div>fdsfsd</div>
</div>
```

[](http://www.baidu.com) 


[百度2][a]
[a]:http://www.baidu.com/


![](C:/Users/18511/Pictures/b.png "3432")

![name][01]
[01]: C:/Users/18511/Pictures/aa.png '描述'

[![](C:/Users/18511/Pictures/b.png "3432")](http://www.baidu.com)

[![](C:/Users/18511/Pictures/aa.png "3432")][a]

<!-- fdsfd fdfdsfds-->
[comment]: <> (哈哈我是注释，不会在浏览器中显示。)
[//]:bbbb
[32432432]:fdsfdsfds
[fdsjfkhjds]: fdsfdsfds
[#$%^&*]:fdfdsfds


1. fdsfds
2. dfsdfs
3. fdsfdsfds
4. fdgfdgdf

<br/>

* aaaa
* bbbb

###需要清除有序和无序列表的方法
1. 334
	1. 反对犯得上犯得上

2. fdsfdsfds
	- 大师傅但是
	- 反倒是

范德萨范德萨

- 反对犯得上

fdsfds

| 一个普通标题 | 一个普通标题 | 一个普通标题 |
| :------: | :------ | ------: |
| 短文本 | 中等文本 | 稍微长一点的文本 |
| 稍微长一点的文本 | 短文本 | 中等文本 |


a  | b | c  
:-:|:- |-:
    居中    |     左对齐      |   右对齐    
============|=================|=============

*2321*
_aaa3242_ **dfdsfds**

## 代码 

<pre>
function del() { 
			if (!$("#id").val()) {
				ui_error("请选择要删除的数据");
				return false;
			} 
			ui_confirm('确定要删除吗?', function() {
				$("#opmode").val("del");
				sendForm("form_data", "", "__SELF__");
			});
		}
</pre>


```java

	/**
     * 分页
     */
    @Around(value = "@annotation(com.kingxunlian.caslab.config.PagingTag)")
    public Object paging(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        PageInfo pageInfo = null;
        //找到pageInfo
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof PageInfo) {
                pageInfo = (PageInfo) arg;
            }
        }
        //如果参数中无pageInfo，说明在controller层已经开始分页，直接从ThreadLocal中获取,生成pageInfo
        if (pageInfo == null) {
            Page<Object> localPage = PageHelper.getLocalPage();
            if (localPage == null) {
                throw new IllegalArgumentException("请先开始分页，或者传入分页参数");
            }
            pageInfo = new PageInfo<>();
            pageInfo.setPageNum(localPage.getPageNum());
            pageInfo.setPageSize(localPage.getPageSize());
            pageInfo.setNavigatePages(5);
        }
        int pageSize = pageInfo.getPageSize();
        int pageNum = pageInfo.getPageNum();
        int navigatePages = pageInfo.getNavigatePages();
        //从菜单点进来，设置默认参数
        if (navigatePages == 0 && pageNum == 0 && pageSize == 0) {
            pageInfo.setNavigatePages(5);
            pageInfo.setPageSize(20);
            pageInfo.setPageNum(1);
        }
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        //分页合理化
        page.setReasonable(true);
        Object proceed = pjp.proceed();
        //返回包装page,getNavigatePages获取导航页码数
        PageInfo info = new PageInfo<>(page, pageInfo.getNavigatePages());
        if (info.getPageSize() == 0 && info.getPageNum() == 0 && info.getPages() == 0) {
            info.setPageNum(1);
        }
        BeanUtils.copyProperties(info, pageInfo);
        return proceed;
    }

```



---
***
___

[^1]: Markdown是一种纯文本标记语言

[走你](#代码)
 











