### DDL-database
```mysql
SHOW DATABASES;
SELECT DATABASE();
CREATE DATABASE IF NOT EXISTS [NAME];
DROP DATABASE [NAME];
USE [NAME];
```
### DDL-sheet
```
SHOW TABLES;
//创建表
CREATE TABLE [NAME])(
    key type comment 'content',
    ....
    )comment 'content';
//查看创建表时的语法
SHOW CREATE TABLE [NAME];
//修改表名
ALTER TABLE [NAME] RENAME TO [NEW-NAME];
//删除表
DROP TABLE [NAME];
//截断表
TRUNCATE TABLE [NAME];
//查看表结构
DESC [TABLE-NAME];

//添加字段类型
ALTER TABLE [NAME] ADD [FIELD-NAME] TYPE COMMENT 'CON';
//修改字段类型
ALTER TABLE [NAME] MODIFY [FIELD-NAME] TYPE;
//修改字段名和字段类型
ALTER TABLE [NAME] CHANGE [OLD-NAME] [NEW-NAME] TYPE;
//删除字段
ALTER TABLE [NAME] DROP [FIELD-NAME];

```

### sheet-data-type
> Number type  

|Type|Size(byte)|
|:---|:---:|
|TINYINT|1|
|SMALLINT|2|
|MEDIUMINT|3|
|INT|4|
|BIGINT|8|
|FLOAT|4|
|DOUBLE|8|

------
> Text type  

|Type|Size(byte)|Description|
|:--|:--|:--|
|CHAR|0-255|Constant length|
|VARCHAR|0-65535|Mutable length|
|TINYBLOB|0-255|


### DML
```
//添加数据
INSERT INTO [NAME] (FILED_1,..) VALUES(VAL_1,..);
INSERT INTO [NAME] VALUES(VAL_1,..);
//修改数据,如果不带where，则更新整张表的字段值
UPDATE [NAME] SET [FIELD-NAME]=[VAL],.. WHERE [CONDITION];
//删除数据--删除记录,即一行,如果不带where则删除所有记录
DELETE FROM [NAME] WHERE [CONDITION];
```

### DQL
```
SELECT 字段1,字段2... FROM [NAME];
SELECT DISTINCT [FIELD-NAME] FROM [NAME];
