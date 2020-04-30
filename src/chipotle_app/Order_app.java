package chipotle_app;
import java.util.*;
import java.lang.Character;

public class Order_app {
	double price;
	boolean veggies_selected = false;
	
	Scanner input = new Scanner(System.in);
	String order_type = "";
	Dictionary order_choices = new Hashtable();
	Dictionary ingredient_choices = new Hashtable();
	List<Ingredients> ingredients_added = new ArrayList<Ingredients>();
	List<Ingredients>side_order = new ArrayList<Ingredients>();
	List<String>side_display_list = new ArrayList<String>();
	
	Ingredients tortilla = new Ingredients("Tortilla", 300, 16, 9);
	Ingredients white_rice = new Ingredients("White Rice", 250, 3, 5);
	Ingredients brow_rice = new Ingredients("Brown Rice", 250, 3, 5);
	Ingredients black_beans = new Ingredients("Black Beans", 160, 3, 9);
	Ingredients pinto_beans = new Ingredients("Pinto Beans", 185, 4, 9);
	
	//Ingredients beans = new Ingredients("Beans", 150, 3, 8);
	Ingredients chicken = new Ingredients("Chicken", 100, 5, 15);
	Ingredients beef = new Ingredients("Beef", 120, 11, 20);
	Ingredients steak = new Ingredients("Steak", 190, 18, 22);
	Ingredients extra_chicken = new Ingredients("Extra Chicken", 200, 10, 30);
	Ingredients extra_beef = new Ingredients("Extra Beef", 240, 22, 40);
	Ingredients extra_steak = new Ingredients("Extra Steak", 380, 36, 44);
	//extra_chicken.set_extra_cost(2.00);
	
	
	Ingredients mild_sauce = new Ingredients("Mild Sauce", 20, 0, 1);
	Ingredients hot_sauce = new Ingredients("Hot Sauce", 35, 2, 1);
	//Ingredients hot_sauce = new Ingredients("Red_Sauce", 10, 0, 0);
	Ingredients corn = new Ingredients("Corn", 60, 0, 2);
	Ingredients guacamole = new Ingredients("Guacamole", 110, 12, 5);
	Ingredients lettuce = new Ingredients("Lettuce", 20, 0, 1);
	
	//sides
	Ingredients chips = new Ingredients("Chips", 160, 7, 4);
	Ingredients soft_drink = new Ingredients("Soft Drink", 100, 0, 0);
	
	Object current_choice;
	List<Ingredients> rice_menu = new ArrayList<Ingredients>();
	List<Ingredients> bean_menu = new ArrayList<Ingredients>();
	List<Ingredients> meat_menu = new ArrayList<Ingredients>();
	List<Ingredients> salsa_menu = new ArrayList<Ingredients>();
	List<Ingredients> veggie_menu = new ArrayList<Ingredients>();
	List<Ingredients> ingredients_menu = new ArrayList<Ingredients>();
	List<String> display_list = new ArrayList<String>();
			
	public Order_app() {
		
		this.order_choices.put(1, "Burrito");
		this.order_choices.put(2, "Bowl");
		
		this.rice_menu.add(white_rice);
		this.rice_menu.add(brow_rice);
		
		this.bean_menu.add(black_beans);
		this.bean_menu.add(pinto_beans);
		
		this.meat_menu.add(chicken);
		this.meat_menu.add(beef);
		this.meat_menu.add(steak);
		
		this.meat_menu.add(extra_chicken);
		extra_chicken.set_extra_cost(2.00);
		this.meat_menu.add(extra_beef);
		extra_beef.set_extra_cost(2.00);
		this.meat_menu.add(extra_steak);
		extra_steak.set_extra_cost(3.00);
		
		this.salsa_menu.add(mild_sauce);
		this.salsa_menu.add(hot_sauce);
		
		this.veggie_menu.add(corn);
		this.veggie_menu.add(lettuce);
		this.veggie_menu.add(guacamole);
		guacamole.set_extra_cost(2.50);
		
		
		this.ingredients_menu.add(white_rice);
		//this.ingredients_menu.add(beans);
		this.ingredients_menu.add(chicken);
		this.ingredients_menu.add(beef);
		this.ingredients_menu.add(steak);
		this.ingredients_menu.add(hot_sauce);
		this.ingredients_menu.add(corn);
		this.ingredients_menu.add(lettuce);
		this.ingredients_menu.add(guacamole);
	}
	public void report_nutrition() {
		int total_cal = 0;
		int total_fat = 0;
		int total_protein = 0;
		for (Ingredients ingre: this.ingredients_added) {
			System.out.println("\n" + ingre.name + "\ncalories: " + ingre.calories + " fat: " + ingre.fat + " protein: " + ingre.protein);
			total_cal += ingre.calories;
			total_fat += ingre.fat;
			total_protein += ingre.protein;
		}
		for (Ingredients ingre: this.side_order) {
			System.out.println("\n" + ingre.name + "\ncalories: " + ingre.calories + " fat: " + ingre.fat + " protein: " + ingre.protein);
			total_cal += ingre.calories;
			total_fat += ingre.fat;
			total_protein += ingre.protein;
		}
		System.out.println("\nTotal Calories: " + total_cal + " Total Fat: " + total_fat + " Total Protein: " + total_protein);
	}
	public String check_ingredient(String str_num, Ingredients ingredient) {
		if (this.ingredients_added.contains(ingredient)) {
			String return_string = ingredient.name + "(Already Added)\n";
			return return_string;
		}
		else {
			if (ingredient.name == "Guacamole") {
				String return_string = str_num + ": " + ingredient.name + "(2.50)\n";
				return return_string;
			} 
			else {
			String return_string = str_num + ": " + ingredient.name + "\n";
			return return_string;
			}
		}
	}
	public void ask_order() {
		System.out.printf("1: Burrito ($8.00)\n2: Bowl (7.50)\n");
		
		int order = input.nextInt();
		this.order_type = (String) this.order_choices.get(order);
		
		this.create_order();
	}
	
	public void create_order() {
		if (this.order_type == "Burrito") {
			this.ingredients_added.add(this.tortilla);
			this.price = 8.00;
		}
		else if (this.order_type == "Bowl") {
			Scanner new_scan = new Scanner(System.in);
			System.out.println("Would you like a tortilla on the side of your bowl? (Free)");
			String answer = new_scan.nextLine();
			
			if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y'){
				this.ingredients_added.add(this.tortilla);
			}
		}
		ask_this("Rice", "1: White Rice\n2: Brown Rice", rice_menu);
		ask_this("Beans", "1: Black Beans\n2: Pinto Beans", bean_menu);
		ask_this("Meat", "1: Chicken\n2: Beef\n3: Steak\n4: Extra Chicken ($2.00)\n"
				+ "5: Extra Beef ($2.00)\n6: Extra Steak($3.00)", meat_menu);
		ask_this("Salsa", "1: Green(Mild)\n2: Red(Hot)", salsa_menu);
		
	
		while (!this.veggies_selected)
		{
			String corn_text = this.check_ingredient("1", corn);
			String lettuce_text = this.check_ingredient("2", lettuce);
			String guacamole_text = this.check_ingredient("3", guacamole);
			String no_more_text = "4: No More Veggies (Continue)";
			ask_this("Veggies", corn_text + lettuce_text + guacamole_text + no_more_text, veggie_menu);
		}
		System.out.println("Would you like to add chips to your order for $1.50 (yes/no)");
		String chips_answer = input.next();
		if (chips_answer.charAt(0) == 'y' || chips_answer.charAt(0) == 'Y'){
			this.price += 1.50;
			this.side_display_list.add(chips.name);
			this.side_order.add(chips);
			process_customer_order();
		}
		System.out.println("Would you like to add a soft drink to your order? (yes/no)");
		String drink_answer = input.next();
		if (drink_answer.charAt(0) == 'y' || drink_answer.charAt(0) == 'Y'){
			System.out.println("Would you like to add a small($1.50)/ medium($1.75)/ large($2.00) soft drink to your order?");
			String size_answer = input.next();
			soft_drink.calories = soft_drink.determine_size(size_answer);
			this.price += soft_drink.extra_cost;                                                 //determines price based off size
			this.side_display_list.add(soft_drink.name);
			this.side_order.add(soft_drink);
		}
		System.out.println("\n----This is your Final Order----");
		process_customer_order();
		report_nutrition();
		
				
	}
	public void ask_this(String title_phrase, String info_phrase, List<Ingredients> menu) {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose Your " + title_phrase + ".\n" + info_phrase);
		int ingre_num = input.nextInt() - 1;
		if (title_phrase == "Veggies" && ingre_num == 3){
			this.veggies_selected = true;
		}
		
		else {
			Ingredients current_choice = menu.get(ingre_num);
			if (current_choice.extra_cost > 0) {
				if (!this.ingredients_added.contains(current_choice)){
					this.price += current_choice.extra_cost;
				}
			}
		this.ingredients_added.add(current_choice);
		process_customer_order();
		}
	}
	public void process_customer_order() {
		for (Object added_ingre : this.ingredients_added) {
			if (!this.display_list.contains(((Ingredients)added_ingre).name)) {
				this.display_list.add(((Ingredients)added_ingre).name);
			}
		}
		System.out.println("\nYour order: " + this.display_list);
		if (!this.side_display_list.isEmpty()) {
			System.out.println("Your side-order: " + this.side_display_list);
		}
		String price_string = String.format("%.02f", this.price);
		System.out.println("Total Price(" + price_string + ")\n");
		//add_ingredient();
	}
}	
