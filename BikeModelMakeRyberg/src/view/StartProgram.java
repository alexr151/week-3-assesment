package view;

import java.util.List;
import java.util.Scanner;

import controller.ListBikeHelper;
import model.ListBike;

public class StartProgram {
	
	static Scanner in = new Scanner(System.in);
	static ListBikeHelper lbh = new ListBikeHelper();
	
	private static void addABike() {
		System.out.print("Enter a make: ");
		String make = in.nextLine();
		System.out.print("Enter a model: ");
		String model = in.nextLine();
		ListBike toAdd = new ListBike(make, model);
		lbh.insertItem(toAdd);
	}
	
	private static void deleteABike() {
		System.out.print("Enter the make to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the model to delete: ");
		String model = in.nextLine();
		
		ListBike toDelete = new ListBike(make, model);
		lbh.deleteBike(toDelete);
	}
	
	private static void editABike() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Make");
		System.out.println("2 : Search by Model");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListBike> foundBike;
		if (searchBy == 1) {
			System.out.println("Enter make name: ");
			String makeName = in.nextLine();
			foundBike = lbh.searchForBikeByMake(makeName);
		} else {
			System.out.println("Enter the model: ");
			String modelName = in.nextLine();
			foundBike = lbh.searchForBikeByModel(modelName);
		}
	
	
	if (!foundBike.isEmpty()) {
		System.out.println("Found Results.");
		for (ListBike b : foundBike) {
			System.out.println(b.getId() + " : " + b.toString());
		}
		System.out.println("Which ID to edit: ");
		int idToEdit = in.nextInt();
		
		ListBike toEdit = lbh.searchForBikeById(idToEdit);
		System.out.println("Retrieved " + toEdit.getModel() + " from " + toEdit.getMake());
		System.out.println("1 : Update Make");
		System.out.println("2 : Update Model");
		int update = in.nextInt();
		in.nextLine();
		
		if (update == 1) {
			System.out.println("New Make: ");
			String newMake = in.nextLine();
			toEdit.setMake(newMake);
		} else if (update == 2) {
			System.out.println("New Model: ");
			String newModel = in.nextLine();
			toEdit.setModel(newModel);
		}
		
		lbh.updateItem(toEdit);
		
	} else {
		System.out.println("---- No results found.");
	}
	
}	
	public static void main(String [] args) {
		runMenu();
	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the bicycle database console program! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addABike();
			} else if (selection == 2) {
				editABike();
			} else if (selection == 3) {
				deleteABike();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lbh.cleanUp();
				System.out.println("     Goodbye!     ");
				goAgain = false;
			}
		}
	}
	
	private static void viewTheList() {
		List<ListBike>allBikes = lbh.showAllItems();
		for (ListBike singleBike : allBikes) {
			System.out.println(singleBike.returnBikeDetails());
		}
	}

}
