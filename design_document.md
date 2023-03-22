# Alec's Team Design Document

## *Finance Tracker* Design

## 1. Problem Statement

*This project is trying to solve the ambiguity around finances, in terms of net worth,
spending habits, and monthly or yearly spending averages. The goal is for this project is
to create better financial discipline and awareness so that a person can make better decisions 
and have less stress in their life.

Making the project seamless and easy to use is a top priority so that it does not feel like a chore for 
the user to track their finances.


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1. Could we build an integration with a public stock API to keep track of the worth of a person's stock holdings?
2. How would we design the DynamoDB database tables so that they store information in a way that makes the most sense?
3. What will our API Endpoints be? Will it be simply a post to add a purchase/expenditure? Or will it be more than that?

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

U1. As a Finance Tracker customer, I want to store my expenditure in the Database when I enter it into the frontend

U2. As a Finance Tracker customer, I want to see my expenditure history
    
U3. As a Finance Tracker customer, I want to be able to see my total net worth or how much money I have in liquid cash 
## 4. Project Scope

*We definitely want to have a dashboard for Networth and a way to track spending without writing it down, or
keeping a mental note. 

### 4.1. In Scope

Adding expenditures and a net-worth dashboard are in scope for this project

### 4.2. Out of Scope

Some things we may add on top of this project are financial suggestions or warnings whenever you are over a 
certain spending average. Some kind of alerts to remind a customer to stay within certain spending bounds.
# 5. Proposed Architecture Overview

*This project needs a POJO (Plain old java object) for an expenditure, one of the values in the table will be the
item name, which will serve as the partition key, and then the type of expenditure it is (i.e grocery, gas, medecine)
and also the price will be included in this.

The project will also have GET request that will return the networth of the individual. This should be called
everytime the page is refreshed.


# 6. API

## 6.1. Public Models

//ExpenditureModel
String purchaseName
String category
Ingteger Cost,
String Date

//SpendModel
String TimeFrame
String Category
Bool IncludeStocks

//MoneyModel
Integer Networth
## 6.2. *Create Expenditure Endpoint*

* Accepts `Post` to the `/Expenditures`
* Accepts data to create a new expenditure 
  * invalid characters such as `" ' \` are not accepted, an InvalidCharacterException is thrown if given invalid char



## 6.3 *Get Expenditure(s) Endpoint*
* Accepts `GET` to the /Expenditures Table
* returns the total expenditure for that time frame or that expenditure category 
  * if there are no expenditures for that time frame then it will return 0
  * If the Time frame doesn't exist or is in the future then an InvalidTimeFrameException will be thrown
  * If the category does not exist than an InvalidCategoryException will be thrown

## 6.4 *Update Networth Endpoint*
* Accepts PUT request to the /Networth table
* returns the new total networth for the user


# 7. Tables
# 7.1 `Expenditures`
```
purchaseName // partion key , string 
time // sort key, String 
category // string
cost // Integer


```
# 8. Pages 

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*

!Home page will just be total networth and then there will be a button that
allows you to go to a different page for more detailed spending info. 
There is also a button that allows a person to add income and add expenditure.

!Find spending info page will have several buttons that allows the user to try 
and find more specific information about their spending. One button will allow them to 
search by time frame, and will show them the total spendig for that period.
Another button will allow them to find by Name, and another will allow them to find expenditures
by category.

