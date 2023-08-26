import entities.CarRental;
import entities.Invoice;
import entities.Vehicle;
import services.BrazilTaxSerice;
import services.RentalService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programado por Juliano Martins de Souza");

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("Digite os dados do veículo");
        System.out.print("\nModelo do carro: ");
        String carModelo = sc.nextLine();

        System.out.print("\nRetirada: [dd/mm/yyyy hh:mm]: ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno: [dd/mm/yyyy hh:mm]: ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental car = new CarRental(start, finish, new Vehicle(carModelo));

        System.out.print("\nDigite o preço por hora: ");
        double pricePerHour = sc.nextDouble();

        System.out.print("Digite o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxSerice());

        rentalService.processInvoice(car);

        System.out.println("\nFatura:");

        System.out.printf("Pagamento básico: R$ %.2f%n", car.getInvoice().getBasicPayment());
        System.out.printf("Imposto: R$ %.2f%n", car.getInvoice().getTax());
        System.out.printf("Pagamento total R$ %.2f%n ", car.getInvoice().getTotalPayment());


        sc.close();
    }
}