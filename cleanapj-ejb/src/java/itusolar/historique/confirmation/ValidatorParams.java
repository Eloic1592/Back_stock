package itusolar.historique.confirmation;

public class ValidatorParams {
    int[] ids;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public String where() {
        if (this.getIds() == null || this.getIds().length == 0)
            return "";
        String where = "and id in (%s)";
        StringBuilder idList = new StringBuilder();
        for (int i = 0; i < this.getIds().length; i++)
            idList.append((i == 0) ? "" : ",").append(this.getIds()[i]);
        return String.format(where, idList);
    }
}
