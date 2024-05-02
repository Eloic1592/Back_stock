package itusolar.historique.filter;

public interface BloomFilterSignature {
    public void addWhenOk(String data) throws DoublonException;
    public void addWhenOk(BloomData data) throws DoublonException;
    public BloomResult test(String data);
    public BloomResult test(BloomData data);
    public int bitSize();
}
