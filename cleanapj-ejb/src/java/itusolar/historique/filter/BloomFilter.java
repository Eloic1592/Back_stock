package itusolar.historique.filter;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MurmurHash3;

import java.util.BitSet;

public class BloomFilter implements BloomFilterSignature {
    private BitSet bloomTable;
    int optimalSize;

    public BloomFilter(int expectedSize, double falsePositives) {
        int optimalSize = this.optimalBitSetSize(expectedSize, falsePositives);
        this.setOptimalSize(optimalSize);
        this.setBloomTable(new BitSet(this.getOptimalSize()));
    }
    private int optimalBitSetSize(int expectedNumItems, double falsePositiveRate) {
        int result = expectedNumItems * 3;
        falsePositiveRate = (double) result * falsePositiveRate;
        return (int) (result + falsePositiveRate);
    }

    @Override
    public void addWhenOk(String data) throws DoublonException {
        BloomResult result = this.test(data);
        if (result.isIn())
            throw new DoublonException();
        for (int index : result.getIndex())
            this.getBloomTable().set(index, true);
    }

    @Override
    public void addWhenOk(BloomData data) throws DoublonException {
        this.addWhenOk(data.getData());
    }

    @Override
    public BloomResult test(String data) {
        int bitSize = this.bitSize();
        int[] index = {
                this.mumur3(data, bitSize),
                this.md5(data, bitSize),
                this.sha1(data, bitSize)
        };
        boolean verif = true;
        for (int ind : index) {
            verif = verif && this.getBloomTable().get(ind);
        }
        //        this.getBloomTable().set(index, true);
        return new BloomResult(verif, index);
    }

    @Override
    public BloomResult test(BloomData data) {
        return this.test(data.getData());
    }

    public int sha1(String data, int bitSize) {
        byte[] hash = DigestUtils.sha1(data);
        return this.prepare(hash, bitSize);
    }

    public int md5(String data, int bitSize) {
        byte[] hash = DigestUtils.md5(data.getBytes());
        return this.prepare(hash, bitSize);
    }

    public int prepare(byte[] data, int bitSize) {
        int result = 0;
        for (byte b : data) {
            result ^= (b & 0xFF);
            result *= 0x01000193; // FNV prime: 16777619
        }
        return this.prepare(result, bitSize);
    }

    public int mumur3(String data, int bitSize) {
        int index = MurmurHash3.hash32(data.getBytes());
        return this.prepare(index, bitSize);
    }

    public int prepare(int index, int bitSize) {
        index %= bitSize;
        index +=  (index < 0)? bitSize : 0;
        return index;
    }

    @Override
    public int bitSize() {
        return this.getOptimalSize();
    }

    public BitSet getBloomTable() {
        return bloomTable;
    }

    public void setBloomTable(BitSet bloomTable) {
        this.bloomTable = bloomTable;
    }

    public int getOptimalSize() {
        return optimalSize;
    }

    public void setOptimalSize(int optimalSize) {
        this.optimalSize = optimalSize;
    }
}
