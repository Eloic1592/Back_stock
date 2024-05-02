package itusolar.historique;

import java.sql.Connection;

public class HistTemporary extends HistEnergy {
    public HistTemporary() {
        super();
        this.setNomTable("HISTTEMP");
    }
    public HistEnergy toHistEnergy() {
        return new HistEnergy(this.getSourceid(),
                this.getTensionentre(),
                this.getTensionsortie(),
                this.getIntensiteentree(),
                this.getIntensitesortie(),
                this.getPuissancebatt(),
                this.getTemperature(),
                this.getDatins());
    }
    @Override
    public void check() throws Exception {
        if (this.getTemperature() < 0.) {
            throw new Exception("Temperature must be greater or equal to zero");
        }
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqHISTTEMP");
        this.setId(Integer.parseInt(makePK(c)));
    }


}
