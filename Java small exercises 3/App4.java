/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

abstract class Animal {
	
	String name;
	static int animals = 0;
	
	Animal(String n) {
		name = n;
		animals++;
	}
	
	abstract String speak();
		static int numberOfAnimals() {
		return animals;
	}

}

class Dog extends Animal {
	String sound = "woof";
	static int dogs = 0;

	Dog(String name){
	    super(name);
		dogs++;
	}
	String speak(){
		System.out.println(name+":"+sound);
		return (name+ ":"+sound);
	}
	static int numberOfDogs(){
		return dogs;
	}

}

class Cat extends Animal {
	String sound = "miaou";
	static int cats = 0;

	Cat(String name){
		super(name);
		cats++;
	}
	String speak(){
	System.out.println(name+":"+sound);
		return (name + ": " + sound);
	}
	static int numberOfCats(){
		return cats;
	}

}

class App4 {

	public static void main(String[] args) {
		Animal[] animal = {	new Cat("stella"), new Cat("ziggy"), new Dog("azor")};
		System.out.println("Cats: "+Cat.numberOfCats());
		System.out.println("Dogs: "+Dog.numberOfDogs());
		System.out.println("Animals: "+Animal.numberOfAnimals());
		for ( int i = 0; i < animal.length; i++ )
			animal[i].speak();
	}
}
