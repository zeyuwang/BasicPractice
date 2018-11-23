class HammingDistance {
    public int hammingDistance(int x, int y) {
        int xorRst = x^y;
        int rst = 0;
        while (xorRst != 0) {
            if ((xorRst & 1) == 1) {
                rst++;
            }
            xorRst >>= 1;
        }
        return rst;
    }
}
