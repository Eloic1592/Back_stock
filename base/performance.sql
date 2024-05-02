
create table type (
                      id integer primary key not null,
                      titre varchar2(20) not null
);
create sequence s_type increment by 1;

create FUNCTION GETSEQType
    RETURN NUMBER IS
           retour NUMBER;
BEGIN
SELECT s_type.nextval INTO retour FROM dual;
return retour;
END;
/

create table energysource (
                              id integer primary key not null,
                              titre varchar2(30) not null,
                              typeid integer
);
create sequence s_source increment by 1;
alter table energysource add foreign key (typeid) references type(id);

create FUNCTION GETSeqEnergySource
    RETURN NUMBER IS
           retour NUMBER;
BEGIN
SELECT s_source.nextval INTO retour FROM dual;
return retour;
END;
/


create table HistEnergie(
                            id integer primary key not null,
                            sourceid integer,
                            tensionentre number(12,2) DEFAULT 0 not null ,
                            tensionsortie number(12,2) DEFAULT 0 not null ,
                            intensiteentree number(12,2) DEFAULT 0 not null ,
                            intensitesortie number(12,2) DEFAULT 0 not null ,
                            puissancebatt number(12,2) DEFAULT 0 not null ,
                            datins timestamp default current_timestamp not null
);
create sequence s_HistEnergie increment by 1;
alter table HistEnergie add foreign key (sourceid) references energysource(id);
create FUNCTION GETSeqHistEnergie
    RETURN NUMBER IS
           retour NUMBER;
BEGIN
SELECT s_HistEnergie.nextval INTO retour FROM dual;
return retour;
END;
/
alter table HistEnergie add temperature number(6,2) default 0 not null;