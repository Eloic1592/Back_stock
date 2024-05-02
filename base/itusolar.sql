-- Suivie d'energie

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
--
-- create table utilisateur (
--     id integer primary key not null,
--     nom varchar2(40),
--     prenom varchar2(40),
--     niveau integer default 0 not null,
--     email varchar2(50),
--     mdp varchar2(32) not null
-- );
-- create sequence s_utilisateur increment by 1;


create table Tarifjirama (
    id integer primary key not null,
    datins date,
    utilisateurid integer,
    compteur number(12,2),
    valuer number(8,2)
);
alter table Tarifjirama add foreign key (utilisateurid) references utilisateur(REFUSER);
create sequence s_Tarifjirama;
create FUNCTION GETSeqTarifjirama
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_Tarifjirama.nextval INTO retour FROM dual;
    return retour;
END;
/

create table TaxeJirama (
    id integer primary key not null,
    titre varchar2(30),
    pourcentage number(5,2)
);
create sequence s_taxeJirama;
create FUNCTION GETSeqTaxeJirama
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_taxeJirama.nextval INTO retour FROM dual;
    return retour;
END;
/
-- Suivie d'energie

-- Simulation
create table appareil (
    id integer primary key not null,
    titre varchar2(50),
    consommation number(10,2) default 0 not null
);
create sequence s_appareil;
create FUNCTION GETSeqAppareil
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_appareil.nextval INTO retour FROM dual;
    return retour;
END;
/

create table materialcomposition (
    id integer primary key not null,
    appareilid integer not null,
    membreid integer not null,
    nombre number(12,2) default 0 not null
);
create sequence s_materialcomposition;
create FUNCTION GETSeqMaterialComposition
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_materialcomposition.nextval INTO retour FROM dual;
    return retour;
END;
/
alter table materialcomposition add foreign key (appareilid) references appareil(id);

-- Fin Simulation

-- Rapport
-- etat : 0 => gain (optimisation calcul ammortissement)
--        50 => depense (calcul cout d'installation)

-- ajout temperature histenergie
alter table HistEnergie add temperature number(6,2) default 0 not null;
-- Fin Rapport


-- Auth
create table token (
    id integer primary key,
    utilisateurid number,
    token varchar2(32),
    dateins timestamp default CURRENT_TIMESTAMP not null ,
    datefin timestamp
);
alter table token add foreign key (utilisateurid) references UTILISATEUR(REFUSER);

create sequence s_token;
create FUNCTION GETSeqToken
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_token.nextval INTO retour FROM dual;
    return retour;
END;
/
create table tokenexpires (
    id integer primary key,
    duree number default 0 not null
);
-- Fin Auth

-- Modulation batteries
create table batterie(
    id integer primary key,
    titre varchar2(30),
    capacite number(12,2) default 0 not null,
    dateins date default current_date not null
);
create sequence s_batterie;
create FUNCTION GETSeqBatterie
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_batterie.nextval INTO retour FROM dual;
    return retour;
END;
/

create table section (
    id integer primary key ,
    titre varchar2(30)
);
create sequence s_section;
create FUNCTION GETSeqSection
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_section.nextval INTO retour FROM dual;
    return retour;
END;
/

create table batteryCapacity (
    id integer primary key,
    energysourceid integer,
    sectionid integer,
    batteryid integer
);
alter table batteryCapacity add foreign key (energysourceid) references energysource(id);
alter table batteryCapacity add foreign key (sectionid) references section(id);
alter table batteryCapacity add foreign key (batteryid) references batterie(id);
alter table batteryCapacity add nombre number(8,2) default 0 not null;
create sequence s_batteryCapacity;
create FUNCTION GETSeqBatteryCapacity
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_batteryCapacity.nextval INTO retour FROM dual;
    return retour;
END;
/


create view v_sectionCapacity as
    select e.sectionid as id,
           e.sectionid,
           sum(b.capacite) as capacite
    from batteryCapacity bc
    left join batterie b
    on bc.batteryid = b.id
    left join energysource e
    on e.id = bc.energysourceid
    group by e.sectionid;

create view v_sectionCapacityFull as
    select s.id,
           s.id sectionid,
           nvl(sc.capacite, 0) capacite,
           s.titre as sectiontitle
    from section s
         left join v_sectionCapacity sc
                   on sc.sectionid = s.id;

create view v_inverterCapacity as
    select bc.energysourceid,
           sum(b.capacite*bc.nombre) as capacite
    from batteryCapacity bc
             left join batterie b
                       on bc.batteryid = b.id
    group by bc.energysourceid;

create view inverterCapacity as
    select e.id,
           e.titre,
           e.typeid,
           e.sectionid,
           cast(nvl(ic.capacite, 0) as number(12,2)) as capacite
    from energysource e
        left join v_inverterCapacity ic
        on e.id = ic.energysourceid;

-- Fin sectionnage de source

-- Lieu
create table lieu (
    id integer primary key ,
    titre varchar2(30),
    sectionid integer
);
alter table lieu add foreign key (sectionid) references section(id);
create sequence s_lieu;
create FUNCTION GETSeqLieu
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_lieu.nextval INTO retour FROM dual;
    return retour;
END;
/
alter table appareil add lieuid integer;
alter table appareil add foreign key (lieuid) references lieu(id);

alter table energysource add sectionid integer;
alter table energysource add foreign key (sectionid) references section(id);
-- View
create view v_histenergie(id,sourceid,tensionentre,tensionsortie,intensiteentree,intensitesortie,puissancebatt,datins,
                          sc,mn,hr,dy,mt,yr,puissanceentree,puissancesortie,temperature,section
    ) as
select h.id,
       h.sourceid,
       h.tensionentre,
       h.tensionsortie,
       h.intensiteentree,
       h.intensitesortie,
       h.puissancebatt,
       h.datins,
       extract(second from h.datins) sc,
       extract(minute from h.datins) mn,
       extract(hour from h.datins) hr,
       extract(day from h.datins) dy,
       extract(month from h.datins) mt,
       extract(year from h.datins) yr,
       cast(h.tensionentre*h.intensiteentree as number(12,2)) puissanceentree,
       cast(h.tensionsortie*h.intensitesortie as number(12,2)) puissancesortie,
       h.temperature,
       es.sectionid section
from HISTENERGIE h
left join energysource es
on h.sourceid = es.id
order by sourceid,datins;


create view v_histenergietemp(id,sourceid,tensionentre,tensionsortie,intensiteentree,intensitesortie,puissancebatt,datins,
                          sc,mn,hr,dy,mt,yr,puissanceentree,puissancesortie,temperature,section
    ) as
select h.id,
       h.sourceid,
       h.tensionentre,
       h.tensionsortie,
       h.intensiteentree,
       h.intensitesortie,
       h.puissancebatt,
       h.datins,
       extract(second from h.datins) sc,
       extract(minute from h.datins) mn,
       extract(hour from h.datins) hr,
       extract(day from h.datins) dy,
       extract(month from h.datins) mt,
       extract(year from h.datins) yr,
       cast(h.tensionentre*h.intensiteentree as number(12,2)) puissanceentree,
       cast(h.tensionsortie*h.intensitesortie as number(12,2)) puissancesortie,
       h.temperature,
       es.sectionid section
from HISTTEMP h
         left join energysource es
                   on h.sourceid = es.id
order by sourceid,datins;

-- Data
insert into type (id, titre)
values (s_type.nextval,'onduleur');

-- insert into source (id, titre, typeid)
-- values (s_source.nextval,'ond_1',2);
insert into HistEnergie values ('0','1','300.0','280.0','200.0','210.0','1234.0','2023-07-25 00:06:29.0')
-- Fin data

create view vsource as (
    select s.*,
           t.titre type,
           sc.titre
        from ENERGYSOURCE s
        left join TYPE t
        on s.TYPEID = t.id
        left join section sc
        on sc.id = s.sectionid
);

create view histpage as
select h.id,
       h.sourceid,
       h.tensionentre,
       h.tensionsortie,
       h.intensiteentree,
       h.intensitesortie,
       h.PUISSANCEBATT,
       h.datins,
       h.sc,
       h.mn,
       h.hr,
       h.dy,
       h.mt,
       h.yr,
       cast(h.puissanceentree as number(12,2)) as puissanceentree,
       cast(h.puissancesortie as number(12,2)) as puissancesortie,
       cast(h.temperature as number(12,2)) as temperature,
       h.section,
       s.titre,
       s.type,
       s.typeid
from v_histenergie h
left join vsource s
on h.sourceid = s.id;

create view histpagetemp as
select h.id,
       h.sourceid,
       h.tensionentre,
       h.tensionsortie,
       h.intensiteentree,
       h.intensitesortie,
       h.PUISSANCEBATT,
       h.datins,
       h.sc,
       h.mn,
       h.hr,
       h.dy,
       h.mt,
       h.yr,
       cast(h.puissanceentree as number(12,2)) as puissanceentree,
       cast(h.puissancesortie as number(12,2)) as puissancesortie,
       cast(h.temperature as number(12,2)) as temperature,
       h.section,
       s.titre,
       s.type,
       s.typeid
from v_histenergietemp h
         left join vsource s
                   on h.sourceid = s.id;


create table schedule (
    id integer primary key,
    startdate timestamp,
    enddate timestamp,
    titre varchar2(100),
    userid integer,
    interval number(12,2) default 0 not null
);
alter table schedule add foreign key (userid) references UTILISATEUR(REFUSER);
create sequence s_schedule;
create FUNCTION GETSeqSchedule
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_schedule.nextval INTO retour FROM dual;
    return retour;
END;
/

create table scheduledetails (
    id integer primary key,
    nombre number(14,2) default 0 not null ,
    materialid integer,
    debut timestamp,
    fin timestamp,
    source integer,
    lieuid integer,
    scheduleid integer
);
alter table scheduledetails add foreign key (materialid) references appareil(id);
alter table scheduledetails add foreign key (lieuid) references lieu(id);
alter table scheduledetails add foreign key (scheduleid) references schedule(id);
create sequence s_scheduledetails;
create FUNCTION GETSeqScheduleDetails
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_scheduledetails.nextval INTO retour FROM dual;
    return retour;
END;
/

create view possibleComposition as
    select a1.id owners,
           a2.id memberid
    from appareil a1 cross join appareil a2
    where a1.id != a2.id;

create view vmatComposition as
    select ROWNUM as id,
           pc.owners as appareilid,
           pc.memberid as membreid,
           cast(nvl(mc.nombre, 0) as number(12,2)) as nombre,
           DECODE(mc.nombre, null, 0, 1) as checked,
           nvl(mc.id, -1) as mcid
    from possibleComposition pc
             left join materialcomposition mc
                       on pc.OWNERS = mc.appareilid
                           and pc.MEMBERID = mc.membreid;

alter table appareil drop column lieuid;

create view lieuList as
    select l.id,
           l.titre,
           l.sectionid,
           s.titre section
    from lieu l
        left join section s
            on l.sectionid = s.id;

alter table batterie drop column dateins;

create view histmax as
select
    SECTION,
    SOURCEID,
    max(DATINS) dateins
from V_HISTENERGIE
group by SECTION, SOURCEID;

create view histsection as
select s.id,
       s.titre,
       cast(nvl(vh.puissanceentree, 0) as number(12,2)) as puissanceentree,
       cast(nvl(vh.puissancesortie, 0) as number(12,2)) as puissancesortie,
       cast(nvl(vh.puissancebatt, 0) as number(12,2)) as puissancebatt,
       s.sectionid as sectionid,
       s.titre as section,
       cast(nvl(ic.capacite, 0) as number(12,2)) as capacite
from energysource s
left join histmax h
on s.sectionid = h.SECTION
and s.id = h.SOURCEID
left join v_histenergie vh
on vh.section = h.SECTION
and vh.SOURCEID = h.SOURCEID
and vh.datins = h.dateins
left join inverterCapacity iC
    on s.id = iC.id;

create view lastHist as
select h.SECTION,
       h.SOURCEID,
       cast(h.PUISSANCEENTREE as number(12,2)) as PUISSANCEENTREE,
       cast(h.PUISSANCESORTIE as number(12,2)) as PUISSANCESORTIE,
       cast(h.PUISSANCEBATT as number(12,2)) as PUISSANCEBATT
from HISTMAX hm
         left join V_HISTENERGIE h
                   on hm.SECTION = h.SECTION
                       and hm.SOURCEID = h.SOURCEID
                       and hm.DATEINS = h.DATINS;

create view v_statesection as
select l.SECTION,
       cast(sum(l.PUISSANCEBATT) as number(12,2)) as PUISSANCEBATT,
       cast(sum(l.PUISSANCESORTIE) as number(12,2)) as PUISSANCESORTIE,
       cast(sum(l.PUISSANCEENTREE) as number(12,2)) as PUISSANCEENTREE
from LASTHIST l
group by l.SECTION;

create view sectionState as
select ROWNUM as id,
       s.id as sectionid,
       cast(nvl(ss.PUISSANCEENTREE, 0) as number(12,2)) as PUISSANCEENTREE,
       cast(nvl(ss.PUISSANCESORTIE,0) as number(12,2)) as PUISSANCESORTIE,
       cast(nvl(ss.PUISSANCEBATT,0) as number(12,2)) as PUISSANCEBATT,
       s.titre as section,
       cast(nvl(sc.capacite,0) as number(12,2)) as capacite
from section s
         left join V_STATESECTION  ss
                   on s.id = ss.SECTION
        left join v_sectionCapacity sc
        on s.id = sc.sectionid;

create table electricSensor(
    id integer primary key,
    title varchar(30),
    ipaddress varchar2(50) not null
);

create sequence s_electricSensor;
create FUNCTION GETSeqElectricSensor
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_electricSensor.nextval INTO retour FROM dual;
    return retour;
END;
/
alter table electricSensor add macAddress varchar2(50);

alter table schedule add state integer default 0 not null;


create view completedScheduleDetails as
select sd.id,
       sd.nombre,
       sd.materialid,
       sd.debut,
       sd.fin,
       sd.source,
       sd.lieuid,
       sd.scheduleid,
       s.startdate,
       s.enddate,
       s.interval,
       s.titre,
       s.userid
from SCHEDULEDETAILS sd
         left join schedule s
                   on s.id = sd.scheduleid
        where s.state > 0;


create table room (
    id integer primary key,
    title varchar2(50)
);
create sequence s_room;
create FUNCTION GETSeqRoom
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_room.nextval INTO retour FROM dual;
    return retour;
END;
/
create table line (
    id integer primary key,
    title varchar2(50)
);

alter table electricSensor add roomId integer;
alter table electricSensor add foreign key (roomId) references room(id);
alter table electricSensor add lineId integer;
alter table electricSensor add foreign key (lineId) references line(id);
alter table electricSensor add position integer default 0 not null;

create table mailAddress (
    id integer primary key,
    address varchar2(70),
    keyMail varchar2(100),
    accreditation integer
);
create sequence s_mailAddress;
create FUNCTION GETSeqMailAddress
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_mailAddress.nextval INTO retour FROM dual;
    return retour;
END;
/

create table beeper(
   id integer primary key,
   title varchar(30),
   ipaddress varchar2(50) not null
);

create sequence s_beeper;
create FUNCTION GETSeqBeeper
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_beeper.nextval INTO retour FROM dual;
    return retour;
END;
/


CREATE TABLE HISTTEMP (
                                ID                   INTEGER   NOT NULL,
                                SOURCEID             INTEGER   ,
                                TENSIONENTRE         NUMBER(12,2)  DEFAULT 0 NOT NULL,
                                TENSIONSORTIE        NUMBER(12,2)  DEFAULT 0 NOT NULL,
                                INTENSITEENTREE      NUMBER(12,2)  DEFAULT 0 NOT NULL,
                                INTENSITESORTIE      NUMBER(12,2)  DEFAULT 0 NOT NULL,
                                PUISSANCEBATT        NUMBER(12,2)  DEFAULT 0 NOT NULL,
                                DATINS               TIMESTAMP(6)  DEFAULT current_timestamp NOT NULL,
                                TEMPERATURE          NUMBER(6,2)  DEFAULT 0 NOT NULL,
                                CONSTRAINT SYS_C0010990_0 PRIMARY KEY ( ID )
);
ALTER TABLE HISTTEMP ADD CONSTRAINT SYS_C0010991_0 FOREIGN KEY ( SOURCEID ) REFERENCES ENERGYSOURCE( ID );

create sequence s_histTemp;
create FUNCTION GETSeqHISTTEMP
    RETURN NUMBER IS
    retour NUMBER;
BEGIN
    SELECT s_histTemp.nextval INTO retour FROM dual;
    return retour;
END;
/
