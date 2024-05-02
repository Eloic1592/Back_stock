package itusolar.historique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryParams {
    HistTemporary[] datas;

    public HistTemporary[] getDatas() {
        return datas;
    }

    public void setDatas(HistTemporary[] datas) {
        this.datas = datas;
    }
}
