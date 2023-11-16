# git configuration

## 配置用户名和邮箱

```
git config --global user.name [YOUNAME]
git config --global user.email [YOUREMAIL]
//--global is to specify the file ~/.gitconfig
//other settings include core.editor, merge.tool
```

## 查看配置信息
```
git config --list
git config --global alias.[your-instruction-name] [instruction-name] //给指令取短名
//example: git config --global alias.unstage 'reset HEAD' 
```

## 获得帮助
```
git help <verb>
//for example: git help config
```

## Fundamental Instructions
```
git init   //初始化一个git仓库
git status  //最常用
git reflog
git log  // --pretty=PARA  multiple parameters is available, such as oneline, full. Using double TAB to get more information.
git add FILES  //*是一个多功能命令，可以用来跟着新文件，或者把已跟踪的文件放入暂存区，还能用于合并冲突时把有冲突的文件标记为已解决状态;运行了git add之后又做了修改的文件，需重新运行git add把最新版本暂存起来
git commit FILES -a -m [comment] //跳过暂存阶段，直接提交
git reset --hard [hash-number]  //历史版本穿梭

```

## 删除命令
```
git rm FILES //删除文件同时移除跟踪
git rm --cached FILES //仅移除跟踪

git mv FILES NEW
/*
The Instruct above is equal to
mv FILES NEW
git rm FILES
git add NEW
*/
```

## 修改最后一次提交
```
git commit --amend
/*example:
git commit -m "initial commit"
ait add forgotten_file
git commit --amend
*/
//上面三条指令最终只是产生一个提交，第二个提交修正了第一个提交的内容
```

## unstage
```
git reset HEAD FILES
```

## 远程库
```
git remote -v //查看远程库和对应地址
git remote //查看远程库名
git remote add NAME [URL] //添加一个远程库，并命名为NAME
git remote show [remote-name]
git remote rename [old-name] [new-name] //修改远程仓库的本地名
git remote rm [remote-name] //删除远程库，即本地不再保存相关远程库的信息

```
## 远程库操作
```
git fetch [remote-name] //将远程库的数据拉取到本地，但不自动合并到当前的工作分支
git push [remote-name] [branch-name] //只有在所克隆的服务器上有写权限，这条指令才会如期执行
git pull [remote-name] [branch-name] //拉取远程库数据并合并分支
```

## 分支操作
```
git branch //查看分支
git branch -v
git branch [branch-name] //在已有基础上创建新的分支
git checkout [branch-name] //切换分支
git branch -b [branch-name] //创建并切换到新的分支
git merge [branch-name] //在当前分支合并指定的分支
git branch -d [branch-name] //删除分支

```

## 上传大文件
> 使用[lfs](https://github.com/git-lfs/git-lfs)

## 使用不同的ssh-key读写仓库  
```
git remote add [name] git@[alias]:sayonaramemori/study-roads.git
cd ~/.ssh
vim config

Host [alias]
    Hostname github.com
    IdentityFile="secret key absolute path"
```
