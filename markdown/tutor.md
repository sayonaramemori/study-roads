---
# 一级标题
## 二级标题
###### 六级标题

---

#### 粗体
```
使用两个*/_
```
**THIS IS BOLD**

#### 斜体
*THIS IS INCLINE*

---

#### 段落与换行
```
1.如果行与行之间没有空行，则视为同一段落。  
2.如果想段内换行，则需要在结尾输入两个及以上的空格然后按回车
```


---

#### 有序列表
`数字序号+英文句号+空格+列表内容`
1. content one
2. content two
3. content three

---
#### 无序列表
```
*/+/- + space + content
```
- content one
- content two
- content three

#### 嵌套列表
```
列表一
TAB+列表二
```
1. list one content one
	- list two content two
2. list one content two
	1. list three content one
		1. list four content one
	2. list three content two

---

#### 分割线
```
使用三个以上的-/_/* 来标记，中间可以有空格，但不能有其他字符
```
----------
*********
__________

#### 图片
```
![图片替代文字，可以为空](图片地址,可以是网络图片，也可以是本地图片）
```
![EXAMPLE](./img/temp.jpg)


#### 链接
```
方式一： [链接文字](链接地址)
方式二： 在文末定义好引用——[链接文字]:链接地址，然后直接用[链接文字]引用即可
```
[Github](https://github.com/) is a good site.  
[github-by-reference] is a good site.

[github-by-reference]:
https://github.com/

----

#### 网址链接
```
<https...>
```
Github is <https://github.com/>

----

#### 行内代码和代码块
This is a command `gcc C_FILE -o OUTPUT` to compile a C FILE.
```shell
$ echo "test"
test
```

#### 引用
`> + 引用内容`
> QUOTE
>> quote  

> *incline quote*


#### 表格
|colume1|colume2|colume3|
|-: |:---|:---:|
|content1|conten2|content3|
> 表格对齐格式如下  
1. :--- 左对齐（默认）
2. ---： 右对齐
3. :---: 居中

#### 任务列表
```
- [ ] 未勾选(注意空格）
- [x] 已勾选
```
- [ ] 学习
- [x] 摆烂

#### 锚点
```
[锚点描述](#锚点名）
> 锚点名建议用字母和数字,大写字母改成小写，空格用`-`替代
```

