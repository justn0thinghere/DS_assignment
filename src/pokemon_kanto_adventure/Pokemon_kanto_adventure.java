
package pokemon_kanto_adventure;

import java.util.ArrayList;
import java.util.Scanner;


public class Pokemon_kanto_adventure {


    public static void main(String[] args) {
        library.readallfiles();
        Player a = new Player("Jack");
        a.obtainitems("Potion", 2);
        a.obtainitems("Potion", 3);
        a.obtainitems("Poke Ball", 4);
        a.obtainitems("Revive",10);
        a.obtainitems("Super Potion",5);
        a.obtainitems("Hyper Potion", 1);
        a.obtainitems("Max Potion", 3);
        a.addPokemon(new Pokemon("Bulbasaur",5));
        a.addPokemon(new Pokemon("Charmander",6));
        a.addPokemon(new Pokemon("Snorlax",100));
        a.addPokemon(new Pokemon("Ponyta",5));
        a.addPokemon(new Pokemon("Pikachu",5));
        a.addPokemon(new Pokemon("Squirtle",5));
        a.findPoke1().takedmg(100);
        a.findPoke2().takedmg(200);
        a.findPoke3().takedmg(3000);
        a.findPoke4().takedmg(1000);
        a.findPoke5().takedmg(1200);
        a.findPoke6().takedmg(2000);
        a.bag();
    }
    public static void selectionPanel(Player player, String currentCity){
        Scanner scanner = new Scanner(System.in);

        // Display player's current location
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("You are currently in " + currentCity + ":");
        System.out.println("+----------------------------------------------------------------------+");

        // Display options for movement
        System.out.println("[1] Move to:");
        ArrayList<String> neighboringCities = library.kantoMap.getNeighbours(currentCity);
        for (int i = 0; i < neighboringCities.size(); i++) {
            System.out.println((char)('a' + i) + ". " + neighboringCities.get(i));
        }
        System.out.println("[2] Challenge Gym leader [Brock - Rock type]");
        System.out.println("[3] Fight Wild Pokemon [Pidgey, Meowth, Scyther are common]");
        System.out.println("[4] Player Options");
        
        System.out.println("      a.Map            b.Pokemons          c.Bag        d.Badges         e.Save and Exit");
        System.out.println("+----------------------------------------------------------------------+");

        // Get player's choice
        System.out.print("Your choice: ");
        String choice = scanner.next();
        
        // Handle player's choice
        if (choice.equals("1a") || choice.equals("1b") || choice.equals("1c")) {
        int cityIndex = choice.charAt(1) - 'a';
        String nextCity = neighboringCities.get(cityIndex);
        player.movetoCity(nextCity);
         // Return the next city
        }else if (choice.equals("2")) {
            System.out.println("You are challenging Gym leader Brock - Rock type!");
            // Implement Gym Leader battle logic
        } else if (choice.equals("3")) {
            System.out.println("You are fighting Wild Pokemon [Pidgey, Meowth, Scyther are common]");
            // Implement wild Pokémon battle logic
        } else if (choice.equals("4a")) {
            // Display map of Kanto
            displayMap();
        } else {
            System.out.println("Invalid choice! Please choose again.");
        }
        
    }
    
    public static void displayMap() {
        System.out.println("Map of Kanto:");
        System.out.println("[**Pewter City**]-------------------[Cerulean City]-----------------|");
        System.out.println("       |                                    |                       |");
        System.out.println("       |                                    |                       |");
        System.out.println("       |                                    |                       |");
        System.out.println("       |                                    |                       |");
        System.out.println("       |            [Celadon City]----[Saffron City]-----[Lavender Town]");
        System.out.println("       |                   |                |                       |");
        System.out.println("[Viridian City]            |                |                       |");
        System.out.println("       |                   |                |                       |");
        System.out.println("       |                   |                |                       |");
        System.out.println("       |                   |        [Vermillion City]---------------|");
        System.out.println("       |                   |                                        |");
        System.out.println("[Pallet Town]              |                                        |");
        System.out.println("       |                   |                                        |");
        System.out.println("       |             [Fuchsia City]---------------------------------|");
        System.out.println("       |                   |");
        System.out.println("       |                   |");
        System.out.println("[Cinnabar Island]----------|");
        System.out.println("+----------------------------------------------------------------------+");
    }
}

    

