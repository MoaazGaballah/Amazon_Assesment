import java.util.*;

public class Question2 {


//    Code Question 2

    /*
        Economy Mart is a very popular e-commerce platform because
        they display the cheapest items first. Economy Mart has
        decided to migrate its database to Amazon's cloud platform.
        The product listings in the old database are being migrated
        into the Amazon database. Customers that go onto Amazon.com
        will be viewing items from the new database.

        Economy Mart has an unusual way of displaying items,

        * If a customer views the first item, they will be shown the
          cheapest item in the database.
        * If a customer is currently viewing the k cheapest item,
          viewing the next item will display the k+1 cheapest item.
        * If multiple items have the same price, they are ordered
          alphabetically ascending.

        There are 2 types of entries:
        * The first element in the row is "INSERT" followed by the name
          of the item(String) and its price(String denoting an Integer).
          This describes an item being added to the database.
        * The first element in the row is the word "VIEW". This is when
          the customer views an item. The other two elements in this
          rows contains "-" that can be ignored. It is guaranteed that
          at least one item is added to the database when a customer
          views an item.

          Example:

          Sample Input:
          entries=[["INSERT", "milk", "4"], ["INSERT", "coffee", "3"],
                    ["VIEW", "-", "-"], ["INSERT", "pizza", "5"],
                    ["INSERT", "gum", "1"], ["VIEW", "-", "-"]]

          In this case milk and coffee are added to the database at
          costs of 4 and 3, respectively. When the customer logs onto
          the site, the cheapest item in the database is shown: coffee

          While the customer is browsing, pizza and gum are added to
          the database. Pizza is worth 5 and gum is worth 1. When the
          customer views the next cheapest item, the items in the
          database in sorted order are gum(1), coffe(3), milk(4),
          and pizza(5). Since the customer is viewing for the second
          time, the second cheapest item, coffee will be shown again.

          Sample Output:
          Return ["coffee", "coffee"]
     */

    public static List<String> getItems(List<List<String>> entries) {
        // Write your code here
        List<String> result = new ArrayList<>();

        List<List<String>> sortedEntries = new ArrayList<>();

        int lookCount = 0;

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).get(0).equals("VIEW")) {
                result.add(sortedEntries.get(lookCount).get(1));
                lookCount++;
                continue;
            }
            sortedInsertion(sortedEntries, entries.get(i));
        }
        return result;
    }

    public static void sortedInsertion(List<List<String>> sortedEntries, List<String> entry) {
        for (int i = 0; i < sortedEntries.size(); i++) {
            if (Integer.valueOf(sortedEntries.get(i).get(2)) > Integer.valueOf(entry.get(2))) { // sorted by price
                insertElement(sortedEntries, i, entry);
                return;
            } else if (Integer.valueOf(sortedEntries.get(i).get(2)) == Integer.valueOf(entry.get(2))){
                if (sortedEntries.get(i).get(1).compareTo(entry.get(1)) > 0){ // sort alphabetically
                    insertElement(sortedEntries, i, entry);
                    return;
                }
            }
        }
        sortedEntries.add(entry);
    }

    public static void insertElement(List<List<String>> sortedEntries, int index, List<String> element){
        List<List<String>> temp = new ArrayList<>(sortedEntries);
        sortedEntries.set(index, element);
        sortedEntries.subList(index + 1, sortedEntries.size()).clear();
        sortedEntries.addAll(index + 1, temp);
    }

    public static void main(String[] args) {
        List<List<String>> entries = new ArrayList<>();

        entries.add(new ArrayList<>(Arrays.asList("INSERT", "milk", "4")));
        entries.add(new ArrayList<>(Arrays.asList("INSERT", "coffee", "3")));
        entries.add(new ArrayList<>(Arrays.asList("VIEW", "-", "-")));
        entries.add(new ArrayList<>(Arrays.asList("INSERT", "pizza", "5")));
        entries.add(new ArrayList<>(Arrays.asList("INSERT", "gum", "1")));
        entries.add(new ArrayList<>(Arrays.asList("VIEW", "-", "-")));

        System.out.println(getItems(entries));
//        ["coffee", "coffee"]
    }
}
