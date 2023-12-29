use test;
create table myfirst(
    id int primary key auto_increment,
    mg double not null,
    Qg double check (Qg>0),
    ml double, 
    Ql double,
    Re double,
    Eg double
);

insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));
insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));
insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));
insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));
insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));
insert into myfirst(mg,Qg,ml,Ql,Re,Eg) values(ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()),ceil(100*rand()));





