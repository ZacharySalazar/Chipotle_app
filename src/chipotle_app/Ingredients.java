package chipotle_app;

import java.util.List;

public class Ingredients {
	String name;
	int calories;
	int fat;
	int protein;
	double extra_cost;
	
	public Ingredients(String name, int calories, int fat, int protein) {
		this.name = name;
		this.calories = calories;
		this.fat = fat;
		this.protein = protein;
	}
	
	//getters
	public String get_name() {
		return this.name;
	}
	public int get_calories() {
		return this.calories;
	}
	public int get_fat() {
		return this.fat;
	}
	public int get_protein() {
		return this.protein;
	}
	
	public void set_extra_cost(double g_cost) {
		this.extra_cost = g_cost;
	}
	
	public int determine_size(String size) {
		if (size.charAt(0) == 's'){
			this.extra_cost = 1.50;
			this.name = "Small Drink";
			return 100;
		}
		else if (size.charAt(0) == 'm') {
			this.extra_cost = 1.75;
			this.name = "Medium Drink";
			return 175;
		}
		else {
			this.extra_cost = 2.00;
			this.name = "Large Drink";
			return 250;
		}
	}
	
}	

