### HTML之禅——语义最重要  


### 嵌套规则  
> 块级元素能嵌套几乎所有，除了：
```html
<p>不能嵌套块元素
<h>系列不能嵌套他自己
```

### 选择器  
|Name|Usage|Memo|
|:--:|:--:|:--|
|Universe|\*{`statements`}|Special Usage for clear|
|Label|Label-name{`statements`}|\\|
|Class|.Class-name{`statements`}|Most used|
|ID|#ID-name{`statements`}|Most accurate|
|&&|Lable+Class Or Class+Class{`statements`}|\\|
|&|ANY,ANY{`statements`}(without \*)|write newline|
|Offspring\_seletor|Parent\_selector+`space`+Offspring\_selector...|selectors can be compound|
|Son\_selector|Parent\_selector+`>`+Son\_selector|As the above|
|brother\_selector\_adjacent|Brother\_selector+`+`+Target|Select the element under the selector|
|brother\_selector\_common|Brother\_selector+`~`+Targets|Select all the elements under the selector|
|Attribution\_selector|[Attribute\_name`OPERATION`VALUE]|Select all the elements possessing the attribution specified.(OPERSIONS[=][^=][$=][*=])|

### 伪类选择器  
|Name|Memo|
|:--|:--|
|link|Only for `<a>`|
|visited|As the above|
|hover||
|active||
|focus|Only for form|







