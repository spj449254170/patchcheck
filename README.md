扫码加微信，备注补丁管理，进群讨论。

![](img/vx.jpg)

补丁管理是一个代码补丁管理平台，采用Vue+SpringBoot开发。  



## 英雄帖

>项目还有许多待改进的地方，欢迎小伙伴提交pr，我会将大家完成的工作展示在README中！

# 技术栈  

## 后端技术栈

后端主要采用了：  

1.SpringBoot   
2.JPA   
3.MySQL  

## 前端技术栈

前端主要采用了：  

1.Vue  
2.axios  
3.ElementUI  
4.vue-router  

还有其他一些琐碎的技术我就不在这里一一列举了。   

# 快速运行  

1.克隆本项目到本地  

```
git@github.com:lenve/VBlog.git
```  

2.找到patchcheck项目中resources目录下的kingdee_patchcheck.sql文件，在MySQL数据库中执行  
3.根据自己本地情况修改数据库配置，数据库配置在SpringBoot项目的application.yml中  
4.在IntelliJ IDEA中运行patchcheck项目  

**OK，至此，服务端就启动成功了，此时我们直接在地址栏输入```http://localhost:9000/patchcheck/index.html#/```即可访问我们的项目，如果要做二次开发，请继续看第五、六步。**  

5.进入到pctchcheck_vue目录中，在命令行依次输入如下命令：  

```
# 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run dev
```  

由于我在patchcheck项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在浏览器中输入```http://localhost:9090/#/```就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到SpringBoot中（注意此时不要关闭SpringBoot项目）。  

6.最后可以用WebStorm等工具打开pctchcheck_vue项目，继续开发，开发完成后，当项目要上线时，依然进入到pctchcheck_vue目录，然后执行如下命令：  

```
npm run build
```  

该命令执行成功之后，pctchcheck_vue目录下生成一个dist文件夹，将该文件夹中的两个文件static和index.html拷贝到SpringBoot项目中resources/static/目录下，然后就可以像第4步那样直接访问了。  


**步骤5中需要大家对NodeJS、NPM等有一定的使用经验，不熟悉的小伙伴可以先自行搜索学习下，推荐[Vue官方教程](https://cn.vuejs.org/v2/guide/)。**  


# 项目依赖  



# License

MIT

**欢迎小伙伴们star、fork。**  

## 其他

 
[base64str]:data:image/jpg;base64,data:image/jpeg;base64,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