package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }



    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Boston");
        owner1.setAddress("Brivibas iela 1");
        owner1.setCity("Riga");
        owner1.setTelephone("+371 29000000");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);


        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Robert");
        owner2.setLastName("Chicago");
        owner2.setAddress("Brivibas iela 2");
        owner2.setCity("Riga");
        owner2.setTelephone("+371 29000001");

        Pet bobsCat = new Pet();
        bobsCat.setName("Sniedzite");
        bobsCat.setOwner(owner2);
        bobsCat.setBirthDate(LocalDate.now());
        bobsCat.setPetType(savedCatPetType);
        owner2.getPets().add(bobsCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();

        vet1.setFirstName("Graham");
        vet1.setLastName("Atlanta");

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Sarah");
        vet2.setLastName("Michigan");

        vetService.save(vet2);
        System.out.println("Loaded Vets...");
    }
}
