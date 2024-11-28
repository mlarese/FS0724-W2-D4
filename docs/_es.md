
```java
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        // Creazione di dati di esempio
        Customer c1 = new Customer("Mauro", 1);
        Customer c2 = new Customer("Marco", 2);
        Product p1 = new Product("TV 45", "Electronics", 3000.0);
        Product p2 = new Product("TV 65", "Electronics", 4000.0);
        Product p3 = new Product("Laptop", "Computers", 1500.0);
        Product p4 = new Product("Tablet", "Computers", 700.0);
        Product p5 = new Product("Phone", "Mobile", 800.0);

        Order o1 = new Order(c1);
        o1.addProduct(p1);
        o1.addProduct(p2);

        Order o2 = new Order(c2);
        o2.addProduct(p3);
        o2.addProduct(p4);
        o2.addProduct(p5);

        List<Order> orders = Arrays.asList(o1, o2);

        // Salvataggio ordini su disco
        saveOrdersToDisk(orders);
        System.out.println("Ordini salvati su disco.");

        // Lettura ordini da disco
        List<Order> loadedOrders = loadOrdersFromDisk();
        System.out.println("Ordini letti da disco:");
        loadedOrders.forEach(System.out::println);
    }

    public static void saveOrdersToDisk(List<Order> orders) throws IOException {
        String toWrite = "";

        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                toWrite += order.getOrderDate() + "@" + order.getCustomer().getName() + "@" +
                        order.getCustomer().getTier() + "@" + product.getName() + "@" +
                        product.getCategory() + "@" + product.getPrice() + "#";
            }
        }

        File file = new File("orders.txt");
        FileUtils.writeStringToFile(file, toWrite, StandardCharsets.UTF_8);
    }

    public static List<Order> loadOrdersFromDisk() throws IOException {
        File file = new File("orders.txt");
        String fileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        List<String> splitElements = Arrays.asList(fileString.split("#"));

        Map<String, Order> ordersMap = new HashMap<>();

        for (String string : splitElements) {
            String[] orderInfo = string.split("@");

            String orderDate = orderInfo[0];
            String customerName = orderInfo[1];
            int tier = Integer.parseInt(orderInfo[2]);
            String productName = orderInfo[3];
            String productCategory = orderInfo[4];
            double productPrice = Double.parseDouble(orderInfo[5]);

            Customer customer = new Customer(customerName, tier);

            // Usa una chiave unica per cliente e data per evitare duplicati
            String orderKey = orderDate + "_" + customerName;
            Order order = ordersMap.computeIfAbsent(orderKey, k -> new Order(customer));
            Product product = new Product(productName, productCategory, productPrice);
            order.addProduct(product);
        }

        return new ArrayList<>(ordersMap.values());
    }
}
```

### Dettagli delle Modifiche

1. **Formato del File**:
   - Ogni riga segue il formato:
     `dataOrdine@nomeCliente@tier@nomeProdotto@categoriaProdotto@prezzoProdotto#`
   - Non c'è separatore `|`. I campi sono separati solo da `@`, e ogni record termina con `#`.

2. **Funzione `saveOrdersToDisk`**:
   - Per ogni prodotto in ogni ordine, scrive una riga separata nel file.
   - Le righe sono concatenate con il separatore `#`.

3. **Funzione `loadOrdersFromDisk`**:
   - Legge il file `orders.txt` e divide il contenuto per `#`.
   - Usa una `Map<String, Order>` per aggregare i prodotti sotto lo stesso ordine (identificato da `dataOrdine` e `nomeCliente`).
   - I prodotti vengono aggiunti agli ordini già esistenti nella mappa.

### Esempio di File `orders.txt`

Dopo aver eseguito il programma, il file `orders.txt` potrebbe contenere:

```
2024-11-27@Mauro@1@TV 45@Electronics@3000.0#
2024-11-27@Mauro@1@TV 65@Electronics@4000.0#
2024-11-27@Marco@2@Laptop@Computers@1500.0#
2024-11-27@Marco@2@Tablet@Computers@700.0#
2024-11-27@Marco@2@Phone@Mobile@800.0#
```

### Test del Codice

1. Esegui il programma per generare e salvare il file `orders.txt`.
2. Verifica il contenuto del file per confermare il formato.
3. Esegui nuovamente il programma per leggere gli ordini dal file e ricostruire la lista di oggetti `Order`. I dati ricostruiti saranno stampati sulla console.