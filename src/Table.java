public class Table {
    private int masaNo;
    private boolean doluMu;

    public Table(int masaNo) {
        this.masaNo = masaNo;
        this.doluMu = true;
    }

    public int getTableNumber() {
        return masaNo;
    }

    public boolean isOccupied() {
        return doluMu;
    }

    public void setOccupied(boolean doluMu) {
        this.doluMu = doluMu;
    }
}