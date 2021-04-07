# My Personal Project

## A Point of Sale (POS) software for a Cafe.

The software I am designing for my personal project is a **point-of-sale** software for a fictional restaurant named 
*"UBC Cafe"*. Whereas in the past businesses only had the option for a simple cash register machine, these days they are using
ever more sophisticated software that are custom-designed for their needs. The very basic functionality such software 
provides is to record sales. However, they usually also archive and store data, provide menu view options, allow 
searching through past transactions, and print out summaries of transactions. Depending upon the unique requirements of 
the client, many more sophisticated features can also be added to them such as customer rewards program management, 
tax reporting features, access control, integration with the cloud etc.

The software I am designing is a basic entry-level software that can accomplish the following:

## User Stories: 
- As a user, for a customer order, I want to be able to place an order for a menu item, enter the amount paid by
 customer and know the change due to the customer. 
- As a user, I want to be able to record this order.
- As a user, I want to be able to view past orders.
- As a user, I want to be able to view the menu.
- As a user, I want to be able to add menu items to the menu.

PHASE-1
- As a user, I want to be able to save a menu.
- As a user, I want to be able to load a saved menu.


PHASE-4:

 - Task 2: I implemented Option 1. I did this by making the Menu class robust 
by having the "addMenuItem" method throw the DuplicateMenuItemException exception 
if a duplicate menu item is created.

 - Task 3: The UML Design Diagram has been added to the root. As you can see, the design 
 of my program is already very simple with high cohesion (class methods focus on a narrowly defined
 task) and low coupling (dependency cycles do not exist except for one necessary one). 
 
 Looking at my UML diagram, one design pattern that I would incorporate is to use the 
 Singleton design pattern  to ensure that GUI and POS are only instantiated once. 
 
 If I had multiple menus, for example, a different menu for each day of the week and 
 within each day's menu I had multiple menus for Breakfast, Lunch, Dinner, Drinks, Dessert 
 etc then I would have liked to implement the composite method with Menu as the composite
 class and MenuItem as the leaf. This would give me greater power to perform 
 operations on my menus like increasing the prices of all Menu Items in all menus 
 by calling a single method that operates on the parent Menu.
