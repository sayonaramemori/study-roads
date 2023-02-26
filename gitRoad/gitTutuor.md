# 配置git 

## 配置用户名和邮箱

```
git config --global user.name YOUNAME
git config --global user.email YOUREMAIL
//--global is to specify the file ~/.gitconfig
//other settings include core.editor, merge.tool
```

## 查看配置信息
```
git config --list
```

## 获得帮助
```
git help <verb>
//for example: git help config
```

## Instructions
```
git init 
git status
git reflog
git log
git add FILES  //*是一个多功能命令，可以用来跟着新文件，或者把已跟踪的文件放入暂存区，还能用于合并冲突时把有冲突的文件标记为已解决状态;运行了git add之后又做了修改的文件，需重新运行git add把最新版本暂存起来
git commit FILES -a //跳过暂存阶段，直接提交
git rm FILES //删除文件同时移除跟踪
git rm --cached FILES //仅移除跟踪
git mv FILES NEW
/*
equal to
mv FILES NEW
git rm FILES
git add NEW
*/

```



