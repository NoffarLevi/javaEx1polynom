Ex1- Monomial/Polynomial/ComplexFunction/Functions_GUI 

# Monomial Class 
A monomial is an expression in algebra that consists of a coefficient(a), in our case, one 
variable(x) and a non negative integer exponent(b) in the form of ax^b. Ex: 2x^5, -x^3 
Invalid input example a*x^b. 

# Polynomial Class 
A polynomial is an extension of a monomial. It’s an expression consisting of monomials and connecting them through addition and subtraction operations. In the form of a1x^b1+a2x^b2-a3x^b3. Ex: 2x^7+x^3-2x, x^4-5x-6 The polynomial class is represented as a Linkedlist of Monoms 

# Complex Function Class
A Complex Function is made up of two functions and a mathematical Operation (e.g addition, subtraction, max/min, etc.) that computes the two functions. Each function is made up an algebraic expression, a Monomial or Polynomial,  or a Complex Function. A Complex Function must include at least one function Ex: (Operation, Func1, Func2). Operation can either be in a string form or an enum Operation value

# Functions_GUI class
The Functions_GUI class implements the interface functions that represents a collection of functions that we can save to a file or read from a file and represent them in a graphic window. This class uses StdDraw library that implements all the functions we need to be able to open and draw in the graphic window.                                                                                                        We used a LinkedList of type function as a way of linking all the different functions that we want to add to the graphic window, together. 

>>>Imported: 
Gson class from google (used to read from and write to a json file)
