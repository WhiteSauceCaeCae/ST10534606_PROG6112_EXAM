//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);

    String[] names = {"Cozy Nights Monarch mattress", "Restonic Barbados mattress", "Sealy Phuket mattress"};
    int months = 3;
    int [][] sales = new int[names.length][months];

    for (int p = 0; p < names.length; p++){
        for (int m = 0; m <  months; m++){
            System.out.print("Enter number of sales for month" + (m + 1) + "for" + names[p] + ":");
            sales[p][m] = scanner.nextInt();
        }
    }

    IProduct product = new Product(names, sales);
    new PrintProduct(product).printReport(2026);



}
