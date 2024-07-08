# Project demonstrating Clean Architecture and modularization in an Android App

This project was developed by using JetPack Compose. Could be used as template project. 

## Short description
To implement this project was used a multi modules architecture based on the "Clean Architecture" principles.
As sources of data used the https://dummyjson.com to getting dummy/fake JSON data to use as placeholder in prototype this template

## Integrated plugins:
1. "ObjectBox" to local storing of details viewing history
2. "Retrofit" to access external data server
3. "Hilt" used as Dependency Management System
4. "Glide" to download and view images
5. "Kotlin serialization" to converting data to/from History DAO

## Description of the modules
The application includes the following modules and packages:
1. App module
   1. Initialization of App Theme, Colors, Fonts 
   2. Dependency Injection initialization
   3. Database core initialization
   4. Main Application Navigation and routing logic
2. Core-UI Module
   1. Theme package (common settings colors, fonts, themes, functional extensions of Colors object)
   2. Common use widgets package (e.g "Alert widget")
3. Domain Module
   1. Internal data entities
   2. Data interfaces
   3. Business logic interfaces
4. Data Module
   1. Local storage на базе ObjectBox
      1. DAO model definitions
      2. CRUD implementation
      3. mapping between DAO models and internal data presentation
   2. Remote storage based on Retrofit
      1. DTO model definition
      2. Defining and implementing an API to access the Content Management System
      3. mapping between DTO models and internal data presentation
5. Feature Modules:
   1. Product List Screen
      1. Main list of products with support for Page Fill
      2. Product list element widget with product summary
      3. Product Details History Viewer Screen
   2. Screen of view a product details


## Notes and approaches
Choosing to implement a multi modules based on the "Clean architecture" architecture allows a team members to independently develop new application functions simultaneously and independently. 
It also allows them to easily change the logic of data processing and change the libraries used to access local and remote storage.

Using "HILT" as a dependency management system allows you to significantly lower the threshold of entry of new team members into the project while retaining the power and capabilities of the "Dagger" dependency management system of the currently recommended Android developers documentation and If necessary, both dependency management systems can be used for more flexible and complex interactions between system components

The "MVVM" pattern was used to separate the business logic from the logic of presentation by means of which it is easy to change the presentation of data or business logic of its processing.
