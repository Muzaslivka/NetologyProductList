import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void mainMenu() {
        System.out.println("Выберите номер пункта меню или введите end:");
        System.out.println("1. Добавить продукт в список");
        System.out.println("2. Показать список продуктов");
        System.out.println("3. Удалить продукт из списка");
        System.out.println("4. Поиск по названию");
    }

    public static void showListProduct(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public static void main(String[] args) {

        List<String> listProducts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            mainMenu();
            String input = sc.nextLine();
            if (input.equals("end")) {
                break;
            } else {
                int numberMenu;
                try {
                    numberMenu = Integer.parseInt(input);
                } catch (NumberFormatException exception) {
                    System.out.println("Вы ввели не число. Попробуйте еще раз");
                    System.out.println();
                    continue;
                }
                switch (numberMenu) {
                    case 1:
                        addProduct(listProducts, sc);
                        System.out.println();
                        break;
                    case 2:
                        if (listProducts.isEmpty()) {
                            System.out.println("Список продуктов не заполнен!");
                        } else {
                            System.out.println("Список продуктов:");
                            showListProduct(listProducts);
                        }
                        System.out.println();
                        break;
                    case 3:
                        if (listProducts.isEmpty()) {
                            System.out.println("Список продуктов не заполнен!");
                        } else {
                            while (true) {
                                if (deleteProduct(listProducts, sc)) break;
                            }
                        }
                        System.out.println();
                        break;
                    case 4:
                        if (listProducts.isEmpty()) {
                            System.out.println("Список продуктов не заполнен!");
                        } else {
                            searchProduct(listProducts, sc);
                        }
                        System.out.println();
                        break;
                    default:
                        System.out.println("Я не знаю такой команды. Попробуй еще раз");
                        System.out.println();
                }
            }
        }
    }

    private static void searchProduct(List<String> listProducts, Scanner sc) {
        System.out.println("Введите текст для поиска:");
        String search = sc.nextLine();
        String lowerSearch = search.toLowerCase();
        int countSearch = 0;
        for (int i = 0; i < listProducts.size(); i++) {
            String lowerProduct = listProducts.get(i).toLowerCase();
            if (lowerProduct.contains(lowerSearch)) {
                System.out.println((i + 1) + ". " + listProducts.get(i));
                countSearch++;
            }
        }
        if (countSearch == 0) {
            System.out.println("Такого продукта нет в списке");
        }
    }

    private static boolean deleteProduct(List<String> listProducts, Scanner sc) {
        System.out.println("Какой продукт хотите удалить? Введите номер продукта или его название: ");
        showListProduct(listProducts);
        String inputDelete = sc.nextLine();
        int numberDeleteProduct;
        try {
            numberDeleteProduct = Integer.parseInt(inputDelete);
            if (numberDeleteProduct > 0 && numberDeleteProduct <= listProducts.size()) {
                System.out.println("Продукт " + listProducts.get(numberDeleteProduct - 1) + " удален. Список продуктов: ");
                listProducts.remove(numberDeleteProduct - 1);
                showListProduct(listProducts);
                return true;
            } else {
                System.out.println("Продукта с таким номером нет в списке. Попробуйте еще раз");
            }
        } catch (NumberFormatException exception) {
            if (listProducts.remove(inputDelete)) {
                System.out.println("Продукт " + inputDelete + " удален. Список продуктов: ");
                showListProduct(listProducts);
                return true;
            } else {
                System.out.println("Продукт не найдет. Попробуйте еще раз");
            }
        }
        return false;
    }

    private static void addProduct(List<String> listProducts, Scanner sc) {
        System.out.println("Введите названия продукта, который хотите добавить: ");
        String newProduct = sc.nextLine();
        if (listProducts.contains(newProduct)) {
            System.out.println("Такой продукт уже есть в списке");
        } else {
            listProducts.add(newProduct);
            System.out.println("Продукт добавлен с список. Итого продуктов в с списке: " + listProducts.size());
        }
    }
}