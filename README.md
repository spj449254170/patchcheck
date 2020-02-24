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
5.运行FTPServer下的 FTPServer.exeFTP服务器，用户名和密码设置成root

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

该命令执行成功之后，pctchcheck_vue目录下生成一个dist文件夹，将该文件夹中的两个文件static和index.html拷贝到SpringBoot项目中resources/static/目录下，然后修改index.html里的指引路径，然后就可以像第4步那样直接访问了。  


**步骤5中需要大家对NodeJS、NPM等有一定的使用经验，不熟悉的小伙伴可以先自行搜索学习下，推荐[Vue官方教程](https://cn.vuejs.org/v2/guide/)。**  


# 项目依赖  



# License

MIT

**欢迎小伙伴们star、fork。**  

## 其他
## 更新记录
2020-02-24
##### 功能增加：
1.增加日志管理系统，可以查看对补丁的操作情况
#####系统优化：
1.增加点击LOG可以返回到主页
2.上传轻量级ftp服务器
 
