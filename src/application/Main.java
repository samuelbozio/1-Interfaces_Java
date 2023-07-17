package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do alugel: ");
        System.out.println("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.println("Data e hora retirada: ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Data de retorno: ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);


        // Em carRental, o construtor pede um tipo Vehicule.
        // Preenchemos o construtor com um new Vehicle, passando o model.
        CarRental cr =  new CarRental(start, finish, new Vehicle(carModel));

        System.out.println("Entre com o preco por hora: ");
        double pricePerHour =  sc.nextDouble();
        System.out.println("Entre o preco por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("Fatura: ");
        System.out.println("Basic Payment: " + cr.getInvoice().getBasicPayment());
        System.out.println("Imposto: " +  cr.getInvoice().getTax());
        System.out.println("Total Payment: " + cr.getInvoice().getTotalPayment());


    }
}