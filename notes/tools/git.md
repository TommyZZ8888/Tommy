# git

### 1.git基本操作流程
```java
1.1、创建本地项目，创建远程仓库，在本地项目路径下 git init
1.2、git remote add "your name" "remote url"  ==> git remote add origin https://github.com/TommyZZ8888/Tommy.git
1.3、git remote -v  ==>
1.4、git pull origin main
1.5、git add .  ==> 添加所有文件
1.6、git commit -m "提交信息"
1.7、git push origin main
```

### 2.git命令
##### git init
```java
    git init （在当前新目录新建一个git代码库）
    git init [project-name] （新建一个目录，将其初始化为First代码库）
```
##### git clone 
```java
    git clone [url] （下载一个项目和它的整个代码史）
```
##### git config
```java
    git config --list  (显示当前的Git配置)
    git config -e [--global] (编辑Git配置文件)
    git config [--global] user.name "[name]"$ git config [--global] user.email "[email address]" (设置提交代码时的用户信息)
```
##### git add/rm
```java
    git add [file1] [file2] ...  (添加指定文件到暂存区)
    git add *.html (添加指定类型文件(使用通配符方式批量提交)到暂存区)
    git add [dir] (添加指定目录到暂存区)
    git add . (添加当前目录下的所有存在更改文件到暂存区)
    git add -u (添加已经被add的文件且存在更改的文件(Git根路径以下所有文件)到暂存区)
    git add --all $ git add -A // 简写 (添加所有变化(Git根路径以下所有文件)到暂存区)
    git add -p  (添加每个变化前，都会要求确认，对于同一个文件的多处变化，可以实现分次提交)
    git rm [file1] [file2] ... (删除工作区文件，并且将这次删除放入暂存区)
    git rm -cached [file] (停止追踪指定文件，但该文件会保留在工作区)
    git mv [file-origin] [file-rename] (改名文件，并且将这个改名放入暂存区)
    git mv -f oldfolder newfoldergit add -u newfolder (改名文件夹，并将此更改上传)
    git rm -r --cached [dir]$ git commit -m '删除了dir'$ git push -u origin master   (删除文件夹，并将此更改上传)
```

##### git commit
```java
    git commit -m [message] (提交暂存区到仓库区)
    git commit [file1] [file2] ... -m [message] (提交暂存区的指定文件到仓库区)
    git commit -a (提交工作区自上次commit之后的变化，直接到仓库区)
    git commit -v (提交时显示所有的diff信息)
    git commit --amend -m [message] (使用一次新的commit，替代上一次提交，如果代码没有任何变化，则用来改写上一次commit的提交信息)
    git commit -amend [file1] [file2]... (重做上一次commit，并包括指定文件的新变化)
```


##### git branch
```java
    git branch （查看本地分支）
    git branch -r （查看远程分支）
    git branch -a （查看所有分支）
    git branch < branchName > （新建一个分支，但依然停留在当前分支）
    git branch -b [branch-name] （新建一个分支，并切换到该分支）
    git branch --set-upstream-to=origin/< branch > feture-test （建立本地分支与远程分支的联系）
    git branch -m old new / git branch -M old new （重命名分支）
    git branch -d branchname / git branch -D branchname （删除本地分支）
    git branch -d -r branchname （删除远程分支）
```











## 问题点
#### 1.master分支和main分支的问题
```java
    远程有两个分支： 默认分支main和提交的分支master，本地git branch -a只能查到master分支
    这是因为以前默认分支为master，现在默认分支为main；git提交后是master分支。
    需要：master to main；删除master分支
    解决:1.重命名当前分支: git branch -m master main
        2.删除本地分支:   git branch -d master
        3.删除远程分支:   git push origin -delete master
        4.拉取远程代码:   git pull main --allow-unrelated-histories;直接git pull origin main会报错
        5.然后推送:      git push origin main
    最好创建远程项目时不要建readme.md文件
```