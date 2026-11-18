public class Product implements IProduct{

    String[] productNames;
    int[][] sales;

    public Product(String[]productNames, int[][] sales){
        this.productNames = productNames;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[]  months : sales){
            for (int units : months){
                total += units;
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int[] months : sales){
            count += months.length;
        }
        return count == 0 ? 0 : (double) GetTotalProductsSold()/ count;
    }

    @Override
    public String GetMostPopularProduct() {
        int bestIndex = 0;
        int bestTotal = -1;
        for (int p = 0; p < sales.length; p++){
            int productTotal = 0;
            for (int units : sales[p]){
                productTotal += units;
            }
            if (productTotal > bestTotal){
                bestTotal = productTotal;
                bestIndex = p;
            }
        }
        return productNames[bestIndex] + "( "+ bestTotal + ")";
    }
}
