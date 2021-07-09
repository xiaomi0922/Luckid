#### 1、git使用
+ 配置邮箱账号：
git config --global user.name "name"
git config --global user.email "email"
+ 生成ssh秘钥，省去每次https传输输入密码的问题
生成秘钥对：ssh-keygen -t rsa -C "email"
成功后会生成.ssh文件夹，id_rsa.pub，复制里面的key，到github-头像-右侧settings添加key
![](https://upload-images.jianshu.io/upload_images/18416757-1b328880b24065ca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)
+ 常用命令
git conle 地址
git add * 
git commit -m "提交的注释"
git push -u origin master
跟多参见：[https://www.bootcss.com/p/git-guide/](https://www.bootcss.com/p/git-guide/)