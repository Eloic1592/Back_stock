package itusolar.prepare.schedule;

public class ScheduleList {
    Schedule[] datas;

    public ScheduleList() {
    }

    public ScheduleList(Schedule[] datas) {
        this.setDatas(datas);
    }

    public Schedule[] getDatas() {
        return datas;
    }

    public void setDatas(Schedule[] datas) {
        this.datas = datas;
    }
}
