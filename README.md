Car Database Practice
====================

### This is not a required assignment.

In this practice assignment, you will practice using the following:

- Interfaces
- Inheritance
- Arrays

### Overview

For this assignment, you will build a database using the following CSV file: [cars.csv](cars.csv). You will then implement a driver class that invokes methods to retrieve required information from the database.

If this were a graded assignment, your grade will depend heavily on the *design* of your solution. Make sure that all of the requirements are followed and that the remaining components are well-designed and efficient.

### Design Requirements

Your solution **must** use the following design elements.

**You may not use `ArrayList` for *any* portion of this program.**

**You may not use `ArrayList` for *any* portion of this program (REALLY!).**

#### `Car` 

You must implement a base class `Car`. The `Car` class cannot be instantiated. A `Car` must have at least the following properties: 

- `model` (column Model)
- `vehicleClass` (column Veh Class)
- `pollutionScore` (column Air Pollution Score)

`Car` must also be `Comparable`. `Car` objects are sorted first by pollution score. If two `Car` objects have the same pollution score then they will be sorted by model.

#### `GasCar`

`GasCar` is a subclass of `Car`. 

A `GasCar` is any car that does not have a Fuel of Hydrogen or Electricity. Hybrid cars (e.g., Fuel Gasoline/Electricity) are considered type `GasCar`. `GasCar` must have at least the following properties: 

- `numberCylinders` (column Cyl) 
- `mpg` (column Cmb MPG) 

For cars with two values for Cmb MPG the value used should be the first value (to the left of the "/").

#### `GreenCar`

`GreenCar` is a subclass of `Car`.

A `GreenCar` is a car with Fuel either Hydrogen or Electricity. `GreenCar` will have one property:
- `fuelType` (column Fuel). This will either be "Hydrogen" or "Electricity".

#### `CarList`

`CarList` maintains a *sorted array* of `Car` objects. Use the `compareTo` method to sort `Car` objects. You may not use `Arrays.sort` in this method.

`CarList` will have, at minimum, the following methods. You may add additional helper methods as appropriate.

| Method | Description |
| ------ | ---------- |
| `addCar` | Takes as input a `Car` and inserts it into the array in sorted order. If the array is full it will be resized to accommodate a new item. If two `Car` objects are equal according to `compareTo` they will be sorted in the list in the **reverse** order in which they appear in the original data file. |
| `toString` | Returns a `String` representation of the entire database. The representation of a `GasCar` will look as follows: `Model: VOLVO XC 90 Class: standard SUV Pollution Score: 9 MPG: 25 Cylinders: 4` The representation of a `GreenCar` will look as follows: `Model: FIAT 500e Class: small car Pollution Score: 10 Fuel Type: Electricity`|
|`toStringGreenCars` | Returns a `String` representation of the `GreenCar` objects in the list where the representation for each `Car` is of the following format: `Model: BMW i3 BEV Fuel Type: Electricity` Note, the format for this method is different than that of the `toString` method. |
| `avgMpg` | Takes no parameters and returns the average MPG across all `GasCar` objects.|
| `avgMpgByPartialModel` | Takes as input a `String` with a partial model (for example, "Subaru") and returns the average MPG for all cars with a model *containing* the partial model query `String`. You may use the `String` `contains` method in your solution for this method.
| `findClassesByCylinders`| Takes as input an `int` specifying number of cylinders and returns a `String[]` containing the vehicle classes with models that have the specified number of cylinders. For full credit, the `String[]` will have no repeated elements (each class will only appear once) and the length must be large enough to accommodate only the number of valid elements. The resulting array will be sorted and *you may use `Arrays.sort` for this purpose*. |
|`findModelsByClassAndMpg` | Takes as input a target vehicle class and a minimum MPG and returns a `String[]` containing the models of all vehicles of the specified class that have *at least* the specified combined MPG. The `String[]` may have repeated elements if the same model appears twice in the dataset, but the the length must be large enough to accommodate only the number of valid elements. The resulting array will be sorted and *you may use `Arrays.sort` for this purpose*.|

### `CarDBDriver`

The `main` method will be in a class called `CarDBDriver`. 

The main method will expect four command line parameters. The first will be the `-in` flag. The second will be the relative path to the csv file to be processed. The third will be the `-out` flag. The fourth will be the name of the file where the output described below will be saved. 

```
	 * Expected usage:
	 * 	java practice.cardb.CarDBDriver -in <relative path to cars.csv> -out <output file name>
	 * 
	 * Example:
	 *  java practice.cardb.CarDBDriver -in cars.csv -out actual.txt
```



The suggested algorithm for this method is as follows:

  - Build a `CarList` object from the file [cars.csv](cars.csv).
  - Output "All Cars:" followed by a newline.
  - Output the result of calling `toString` on the `CarList`.
  - Output "Green Cars - Fuel Type:" followed by a newline.
  - Output the result of calling `toStringGreenCars` on the `CarList`.
  - Output "Average MPG All:" followed by the result of calling `avgMpg` on the `CarList` followed by a newline. The result should be displayed with exactly two digits after the decimal point.
  - Output "Average MPG Subaru:" followed by the result of calling `avgMpgByPartialModel("SUBARU")` on the `CarList` followed by a newline. The result should be displayed with exactly two digits after the decimal point.
  - Output "Average MPG Toyota:" followed by the result of calling `avgMpgByPartialModel("TOYOTA")` on the `CarList` followed by a newline. The result should be displayed with exactly two digits after the decimal point.
  - Output "Average MPG Ferrari:" followed by the result of calling `avgMpgByPartialModel("FERRARI")` on the `CarList` followed by a newline. The result should be displayed with exactly two digits after the decimal point.
  - Output "Vehicle Classes with 4-Cylinder Cars:" followed by a newline followed by the result of calling `findClassesByCylinders(4)` on the `CarList`. For each item in the list returned, output a tab followed by the item followed by a newline.
  - Output "Vehicle Classes with 6-Cylinder Cars:" followed by a newline followed by the result of calling `findClassesByCylinders(6)` on the `CarList`. For each item in the list returned, output a tab followed by the item followed by a newline.
  - Output "Small SUVs with MPG > 22:" followed by a newline followed by the result of calling `findModelsByClassAndMpg("small SUV", 22)` on the `CarList`. For each item in the list returned, output a tab followed by the item followed by a newline.
  - Output "Small Cars with MPG > 35:" followed by a newline followed by the result of calling `findModelsByClassAndMpg("small car", 35)` on the `CarList`. For each item in the list returned, output a tab followed by the item followed by a newline.

### Additional Requirements and Hints
 
Your output must **exactly** match the following: [expected.txt](expected.txt) All spacing, indentation, and ordering must exactly match the result. The `CarDBTest` case will not pass unless your output matches exactly.

You *may* implement classes in addition to those specified.

It is strongly advised that you **do not** open the `cars.csv` file using another program like Excel. It can change the formatting when resaving causing problems with your solution. Instead, create a copy of `cars.csv` and open the copy as necessary.

Refer to the guide for [setting up your development environment](https://github.com/CS601-F21/notes/blob/master/admin/devenvironment.md) as well as the initial [practice assignment](https://github.com/CS601-F21/practice) as necessary.

### Submission Requirements

1. Use the following link to create your private github repository for this assignment. The repository will be seeded with the skeleton code, test cases, and input files. [CarDB Practice](https://classroom.github.com/a/Ez8b3UF-)
2. For full credit, make sure to follow all [Style Guidelines](https://github.com/CS601-F21/notes/blob/master/admin/style.md). Points will be deducted for each violation.

### Grading Rubric

This is an ungraded assignment. For most assignments, the rubric will specify how the assignment will be graded.

In some cases, partial credit may be awarded for partial functionality and/or partially correct design or style elements.

### Academic Dishonesty

Any work you submit is expected to be your own original work. If you use any web resources in developing your code you are required to cite those resources. The only exception to this rule is code that is posted on the class website. The URL of the resource you used in a comment in your code is fine. If I google even a single line of uncited code and find it on the internet you may get a 0 on the assignment or an F in the class. You may also get a 0 on the assignment or an F in the class if your solution is at all similar to that of any other student.