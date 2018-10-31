package View;

import java.util.List;
import java.util.Scanner;

import Controller.ListVinylHelper;
import Model.ListVinyl;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListVinylHelper lvh = new ListVinylHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter an artist: ");
			String artist = in.nextLine();
			System.out.print("Enter an album: ");
			String album = in.nextLine();
			System.out.print("Enter the genre: ");
			String genre = in.nextLine();
			System.out.print("Enter the rating: ");
			String rating = in.nextLine();
			
			ListVinyl toAdd = new ListVinyl(artist, album, genre, rating);
			lvh.insertVinyl(toAdd);

		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the artist to delete: ");
			String artist = in.nextLine();
			System.out.print("Enter the album to delete: ");
			String album = in.nextLine();
			System.out.print("Enter the genre to delete: ");
			String genre = in.nextLine();
			System.out.print("Enter the rating to delete: ");
			String rating = in.nextLine();
			
			ListVinyl toDelete = new ListVinyl(artist, album, genre, rating);
			lvh.deleteVinyl(toDelete);

		}

		private static void editAnItem() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Artist");
			System.out.println("2 : Search by Album");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListVinyl> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the artist name: ");
				String artistName = in.nextLine();
				foundItems = lvh.searchForItemByArtist(artistName);
			} else if (searchBy == 2){
				System.out.print("Enter the album: ");
				String albumName = in.nextLine();
				foundItems = lvh.searchForItemByAlbum(albumName);
			}
			else {
				System.out.print("Enter the genre: ");
				String genreName = in.nextLine();
				foundItems = lvh.searchForItemByGenre(genreName);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListVinyl l : foundItems) {
					System.out.println("ID: " + l.getId() + " - " + l.returnVinylDetails());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListVinyl toEdit = lvh.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getAlbum() + "::" + toEdit.getArtist() + "::" + toEdit.getGenre());
				System.out.println("Select what field to update");
				System.out.println("1 : Update Artist");
				System.out.println("2 : Update Album");
				System.out.println("3 : Update Genre");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Artist: ");
					String newArtist = in.nextLine();
					toEdit.setArtist(newArtist);
				} else if (update == 2) {
					System.out.print("New Album: ");
					String newAlbum = in.nextLine();
					toEdit.setAlbum(newAlbum);
				} else if (update == 3) {
					System.out.print("New Genre: ");
					String newGenre = in.nextLine();
					toEdit.setGenre(newGenre);
				}

				lvh.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

}

		public static void main(String[] args) {
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the vinyl list! ---");
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
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lvh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<ListVinyl> allVinyl = lvh.showAllItems();
			for(ListVinyl l : allVinyl) {
				System.out.println(l.returnVinylDetails());
			}

		}

	}
