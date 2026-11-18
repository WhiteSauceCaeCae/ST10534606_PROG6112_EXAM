public class PrintProduct {

    private final IProduct product;

    public PrintProduct(IProduct product){
        this.product = product;
    }

    public void printTotalProductSales(){
        System.out.println("TOTAL PRODUCT SALES: "+ product.GetTotalProductsSold());

    }

    public void printAverageProductSales(){
        System.out.println("AVERAGE PRODUCT SALES: " + (int) Math.round(product.GetAverageProductSold()));
    }

    public void printMostPopularProduct(){
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void printReport(int year){
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - " + year);
        System.out.println("*****************************************");
        printTotalProductSales();
        printAverageProductSales();
        printMostPopularProduct();
    }
}
