# Demo

This is a Demo of my Web Application.
In order to complete this project I used the following technology stack:
Spring Boot, Spring Security, Thymeleaf, Mybatis, and Bootstrap.

First of all, I want to introduce the composition of the project.
The docs directory is for docs in each stage

For the main code area, there are 5 directories,

the config directory is for spring Security Configuration

the controller directory is for dealing with the the request from DispatcherServlet,
encapsulate them into a model then return the model to the target view

the mapper directory is for mybatis mapper interfaces
which provides an easy way to search data in the database

the pojo is a directory for entity classes which include Constructor and setter, getter methods

the service directory is mainly to realize the main functions of the website
and also realize the User Certification Service

The resources including static resources and templates resources
static resources including static page: error pages, success pages and index page and js, css, image files
templates resources including template of Thymeleaf pages

and the application.yaml file is a config file for the project

Next I will show how to use our website
...







