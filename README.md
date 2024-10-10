# Final Project 📖📚✍️✅💯🎓

Objectives:

In this project you will:

* Demonstrate your mastery of OOD to solve a challenging problem
* Design a solution with MVC architecture with Swing
* Apply all (or most) of the programming concepts used in this course
* Use design patterns to raise cohesion and lower coupling as well as to avoid re-inventing the wheel
* Meet objectives of prior assignments

## On Groups

This project can be completed individually or with a group of up to three (3) people. If the latter, the work done must be worthy of the additional personnel i.e. if one student could have completed this by themselves then the final grade will be halved (or by two-thirds).

## Part 1: The Idea

Think of three (3) real problems that excite you. Consider for example:

* a problem that you're encountering at work
* an issue that you run into daily in your usual routine
* a CS problem that sparks your curiosity
* a challenge from when you were an undergraduate
* a new unique game you want to develope

This project is a culmination of all of you've learned in this class including Object-Oriented Design, Testing, UML, Design Patterns, and most importantly is a showcase for MVC requiring Swing.

Should you want to do some data visualization (like with NumPy or Pandas in Python) a widely used tool for Java is [JFreeChart](https://www.jfree.org/jfreechart/samples.html) or [Figma](https://www.figma.com/). These tools are not a replacement for Swing but can be used to create mock up of what you want your final visual to look like.

If you're working with a team - each member should come up with three (3) unique ideas.

Share your ideas on a private Piazza post, visible to the Instructors and the other members of your team, *by the date recommended in Canvas*. We will discuss with you the feasiblity and difficulty of implementing each idea, raising the bar if the problem is too simple, or lowering it if too complicated. If an idea you selected already has many examples online, like on StackOverflow, we will veto it. Finally, we'll recommend to the group which idea would make for the best project. If you dislike any requirements that we add, your group can suggest new ideas until all parties are satisfied.

You cannot proceed to Part 2 until Part 1 is approved.

## Part 2: The Design

This is where you will design a UML Class Diagram that your team will submit here on this README.md written in Mermaid. Use every tool to ensure that the system that you design follows proper OOD principles, is well organized, appears highly cohesive, and lously coupled. Your use of Design Patterns needs to be indicated with UML Notes as part of the diagram.

```mermaid
---
title: your diagram title goes here
---
classDiagram

class Person {
    #String name
    #int age
    #double monthlyIncome
    #double monthlyExpenditure
    #double insurance
    #int lifeSpan

    +setName()
    +setAge()
    +setMonthlyIncome()
    +setMonthlyExpenditure()
    +setInsurance()
    +setLifeSpan()
    +getName() String
    +getAge() int
    +getMonthlyIncome() double
    +getMonthlyExpenditure() double
    +getLifeSpan() int
}


class Asset {
    <<Abstract Class>>
    #double amount
    #double interestRate
    #double volatility
    +setAmount()
    +getAmount() double
    calculateReturn() double*
}

class Bond {
    #Time maturity
    #double coupon
    +setCoupon()
    +getCoupon() double

}

class Stock {
    #Double dividend

    +setDividend()
    +getDividend() Double
}

class RealEstate {
    #Double netOperIncome
    
    +setNetOperIncome()
    +getNetOperIncome() Double
}

class Debt{
    #double amount
    #double interestRate
    #boolean isOneTime
    #Time time
    #DebtCategory category

    +setAmount()
    +getAmount():double
    +setTime()
    +getTime() Time
    +setInterestRate()
    +getInterestRate() double
}


class Goal {
    #double amount
    #Time time
    #Boolean isOneTime
    #double acceptedSuccessRate
    #double interestRate

    +calculateAmountEachPeriod() double
    +getInterestRate() Double
    +setInterestRate(rate:double)
    +setAmount(amount:double)
    +getAmount() double

}


class Family{
    #List&lt;Person&gt;: familyMember
    #double TotalExpenditure
    #double TotalIncome
    #String path
    
    +setPath(String: path)
    +getPath() String
    +loadPeopleFromCSV(filePath: String)
    +savePeopleToCSV(filePath: String, people: List&lt;Person&gt;)
    +addFamilyMember(Person p)
    +deleteFamilyMember(Person p)
    +getExpenditure() double
    +getIncome() double
}


class DebtCategory{
    <<enum>>
    Mortgage
    VehicleLoan
    ConsumptionLoan
    Other
}


class PersonFactory {
    +List&lt;Person&gt; : personList
    +String csvPath
    +createPerson()
}

class AssetAndDebtManager {
    +List&lt;Bond&gt; :  bondList
    +List&lt;Stock&gt; :  stockList
    +List&lt;RealEstate&gt;:realEstateList
    +List&lt;Debt&gt;:debtList
    +String path
    
    +saveBondToCSV(filePath: String, bondList List&lt;Bond&gt;)
    +saveStockToCSV(filePath: String, stockList: List&lt;Stock&gt;)
    +saveRealEstateToCSV(filePath: String, stockList: List&lt;Stock&gt;)
    +saveDebtToCSV(filePath:String, DebtList:List&lt;Debt&gt;)
    +getBondToCSV(filePath: String) List&lt;Bond&gt;
    +getStockToCSV(filePath: String) List&lt;Stock&gt;
    +getRealEstateToCSV(filePath: String) stockList: List&lt;Stock&gt;
    +getDebtToCSV(filePath:String) DebtList:List&lt;Debt&gt;
}


class BalanceSheet {
    List&lt;Asset&gt;:assets
    List&lt;Debt&gt;:Debt
    List&lt;Person&gt;:family
    #String path
    updateAssets(filePath: String)
    updateDebt(filePath: String)
    generateReport()
}

class FinancialAdvisor {
    #double riskAversion
    List&lt;Asset&gt;:assets
    List&lt;Debt&gt;:Debt
    List&lt;Person&gt;:family
    +InvestmentAdvise()
    +InsuranceAdvise()
}


Asset <|-- Bond
Asset <|-- Stock
Asset <|-- RealEstate
Debt <-- DebtCategory
PersonFactory  --  Person
AssetAndDebtManager -- Bond
AssetAndDebtManager -- Stock
AssetAndDebtManager -- RealEstate
AssetAndDebtManager -- Debt
Family -- PersonFactory
BalanceSheet -- Family
BalanceSheet -- AssetAndDebtManager
FinancialAdvisor  -- Family
FinancialAdvisor  -- AssetAndDebtManager


```


The second half of this part is a mock-up of what you want your application to look like using a tool like [SceneBuilder](https://gluonhq.com/products/scene-builder/). Add these files to the repo and include screenshots of your renderings below.

![Balance Sheet & Advisor Page](SceneBuilder1.png)

![Save Your Info Page](SceneBuilder2.png)


No actual Java code will be written for this part. Submit everything here on GitHub by the date recommended on Canvas. The teaching teach will provide rapid feedback on your diagram and Views. You can move on to Part 3, just be aware that you may need to change things if the Teaching Team finds issues.

## Part 3: Implementation

A failure to plan is a plan to fail -- but you've done all of the planning, now is the time to build it 😎

There is also a codewalk where you will meet with the Instructors to go over your mostly completed application and get feedback all aspects, including design, Views, missing components, etc., that will need to be addressed before submitting a final result.

## Documentation and Testing

Your project needs to follow the Google style format and reach 70% code coverage from your testing on components not related to the View/Control. You are expected to show a screenshot from your JaCoCo coverage report below, identifying the level of code coverage.


Image Rendering Syntax:
![Jacoco Test Result](Test1.png)


![JaCoCo Test Details](Test2.png)

```markdown
Dear Grader, the current testing level is the best I can do, because I used a few design patterns that are difficult to test
(Singleton Pattern and Composite Pattern), and because mocking does not support Junit5, I cannot test IO operations.
App.java contains the entire GUI logic and cannot be tested.
And because several manager classes not only involve IO operations, but also reference each other,
it is even more difficult to test. Please take this into consideration when grading.
```


## UML & Design Patterns

```mermaid
---
title: Final Financial Advisor UML
---
classDiagram
   class Person {
    -String name
    -int birthYear
    -double monthlyIncome
    -double monthlyExpenditure
    -double insurance
    -int lifeSpan

    +Person()
    +setName(String name)
    +getName() String
    +setBirthYear(int birth) 
    +getBirthYear() int
    +setMonthlyIncome(double monthlyIncome)
    +getMonthlyIncome() double
    +setMonthlyExpenditure(double Expenditure)
    +getMonthlyExpenditure() double
    +setInsurance(double insurance)
    +getInsurance() double
    +setLifeSpan(int life)
    +getLifeSpan() int
   }

   class DebtCategory{
    <<enum>>
    MORTGAGE
    VEHICLE_LOAN
    CONSUMPTION_LOAN
    OTHER
   }

   class PersonFactory{
    -List&lt;Person&gt;  personList

    +createPerson() Person
    +getPersonList() List&lt;Person&gt; 
   }

   class Family {
    -Family instance
    -List&lt;Person&gt; familyMembers
    -String csvFileName
    -PersonFactory factory

    -Family(PersonFactory factory)
    +getInstance(PersonFactory factory)
    +loadPeopleFromCSV()
    +savePeopleToCSV()
    +addFamilyMember()
    +deleteFamilyMember() boolean
    +getFamilyMembers() List&lt;Person&gt;
   }

   class Debt {
    -double amount
    -double interestRate
    -boolean isOneTime
    -int time
    -DebtCategory category
    -String description

    +Debt()
    +getAmount() double
    +getInterestRate() double
    +getTime() int
    +isOneTime() boolean
    +getCategory() DebtCategory
    +getDescription() String
   }


   class Asset {
    -double amount
    -double interestRate
    -double volatility
    -String description

    +Asset()
    +setAmount(double amount)
    +getAmount() double
    +setInterestRate(double interestRate)
    +getInterestRate() double
    +setVolatility(double volatillity)
    +getVolatility() double
    +calculateReturn() double*
    +getDescription() String
   }


   class Bond{
    -int maturity
    -double coupon
    +Bond()
    +setCoupon()
    +getCoupon() double
    +calculateReturn() double
    +getMaturity() int
    +getCoupon() double
   }


   class Stock{
    -double dividend
    -double dividendGrowthRate

    +Stock()
    +setDividend()
    +getDividend() double
    +getDividendGrowthRate() double
    +calculateReturn() double
   }

   class RealEstate{
    -double netOperIncome
    +RealEstate()
    +getNetOperIncome() double
    +calculateReturn() double
   }

   class Goal {
    -double amount
    -int time
    -boolean isOneTime
    -double interestRate
    -String description

    +Goal()
    +getAmount() double
    +setAmount() double
    +getInterestRate() Double
    +setInterestRate(double rate)
    +getTime() int
    +setTime(int time)
    +isOneTime() boolean
    +setOneTime(boolean oneTime)
    +getDescription() String
   }

   class GoalManager{
    -GoalManager instance
    -List&lt;Goal&gt; goalList

    -GoalManager()
    +getInstance() GoalManager
    +saveGoalsToCSV()
    +addGoal(Goal goal)
    +deleteGoal(String description) boolean
    +createGoal()
    +getGoalList() List&lt;Goal&gt;
    +loadGoalsFromCSV()
   }


   class Strategy{
    <<interface>>
    execute()
   }

   class ConservativeStrategy{
    +execute()
   }

   class AggressiveStrategy{
    +execute()
   }


   class AssetAndDebtManager{
    -AssetAndDebtManager instance
    -List&lt;Bond&gt;   bondList
    -List&lt;Stock&gt;   stockList
    -List&lt;RealEstate&gt; realEstateList
    -List&lt;Debt&gt; debtList

    -AssetAndDebtManager()
    +getInstance() AssetAndDebtManager
    +saveBondToCSV()
    +saveStockToCSV()
    +saveRealEstateToCSV()
    +saveDebtToCSV()
    +getBondList() List&lt;Bond&gt; 
    +getStockList() List&lt;Stock&gt;
    +getRealEstateList() List&lt;RealEstate&gt;
    +getDebtList() List&lt;Debt&gt;
    +addBond(Bond b)
    +addStock(Stock s)
    +addRealEstate(RealEstate r)
    +addDebt(Debt d)
    +deleteBond(String description) boolean
    +deleteStock(String description) boolean
    +deleteRealEstate(String description) boolean
    +deleteDebt(String description) boolean
    +loadBondsFromCSV()
    +loadStocksFromCSV()
    +loadRealEstateFromCSV()
    +loadDebtFromCSV()
   }

   class BalanceSheet{
    -Family family
    -AssetAndDebtManager assetManager
    -double appliedInterestRate

    +BalanceSheet()
    +printAssetsAndLiabilities()
    +calculateDebtPV(Debt d)
    +calculateTotalAssets() double
    +calculateTotalLiabilities() double
    +calculateNetPresentValue() Double
    +calculateTotalIncomePV() double
    +calcualteTotalExpenditurePV() Double
    +printIncomeStatement()
    +printExpenditureStatement()
    +printAssetsDetails()
    +printLiabilitiesDetails()
    +printNetPresentValue()
   }


   class FinancialAdvisor {
    -Strategy strategy
    -GoalManager goalManager
    -BalanceSheet balanceSheet

    +FinancialAdvisor(GoalManager g, BalanceSheet bs)
    +evaluateFinancialSituation()
    +advise()
    +setStrategy(Strategy strategy)
    -calculateTotalGoalsValue() double
   }




PersonFactory  -->  Person : Calls
Family o-- PersonFactory
Debt -- DebtCategory
Asset <|-- Bond
Asset <|-- Stock
Asset <|-- RealEstate
Strategy <|.. AggressiveStrategy
Strategy <|.. ConservativeStrategy
BalanceSheet  o-- Family
BalanceSheet  o-- AssetAndDebtManager
FinancialAdvisor o-- GoalManager
FinancialAdvisor o-- BalanceSheet
FinancialAdvisor o-- Strategy
AssetAndDebtManager -- Debt
AssetAndDebtManager -- Bond
AssetAndDebtManager -- Stock
AssetAndDebtManager -- RealEstate
```




Also fill in the table below explaining the design patterns that you used in your application.

| Pattern Name | Class(es) | Justification |
| :--------: | ------- | :------- |
| ExamplePattern1 | `Listing`, `Agent` | This pattern allowed for a seperation of duties between the different types of `Property` so that the `Agent` could list multiple types of `Property` without needing to be concerned with the type or writting special code inside of `Listing` following the guidance of encapsulating tasks. |
| Singleton | `Family`, `AssetAndDebtManager`, `GoalManager` | Throughout the project I ensured that there was only one instance of `Family` class, `AssetAndDebtManager` class and `GoalManager` class. These classes are used to manage lists corresponding to `Person` and `Asset`, and provide functions such as adding, deleting, and saving to CSV. Only one `Family` instance is needed to manage all `Person` objects. |
| FactoryMethod | `PersonFactory` | The `PersonFactory` class uses the factory method pattern to create a `Person` class object and adds it to the Person list that comes with the factory class after creation. |
| Composite | `BalanceSheet` | Composite Pattern is used to represent the part-whole hierarchy of objects. It can help manage and organize complex structures. The `BalanceSheet` class contains a `Family` class and `AssetAndDebtManager` class instances to manage combinations of family members, assets and liabilities. |
| Strategy |  `FinancialAdvisor` | In the `FinancialAdvisor` class, I implement the strategy pattern, which selects `ConservativeStrategy` or `AgresiveStrategy` based on the family's financial situation (whether the current Asset is greater than Debt+Goal) |

## Reflection

Each member of your group needs to provide an answer to *each* question and be sure to clearly identify whose response is whose.

1. Describe one lesson from this course and how it impacted this project.

Answer: The most important knowledge I learned from the entire course is design patterns and design principles (such as Open-Close Principle and Single Responsibility Principle). Before coming into contact with this knowledge, my overall architecture design was often a mess. These standardized designs make my code more concise and robust


2. What part of this assignment did you find the most challenging and why?

Answer: Implementing the GUI interface is the hardest part. Because GUI is only mentioned a little bit at the end of the course, and it is difficult to design a GUI to call the correct classes and methods, taking into account the position of each button and input box, and also taking into account the user's incorrect input.

3. Assume that you are doing this project over again, what element(s) would you change and how?

Answer: If I have more time to improve the design, I will enrich the design of the Debt class (split it into more subclasses to have different features and business logic, instead of just using DebtCategory for simple classes as now distinction). I will also treat Bond as an abstract class. There are many implementation classes below: FixedRateBond, FloatingRateBond, CallableBond and PutableBond. I'd also like to add more features to my overall project, such as taking retirement timing into account and making recommendations for pensions and annuities.

4. How did the process of this assignment, specifically completing Part 2 before starting Part 3, impact your learning?

Answer: I think communicate with the instructor about UML in advance, including guiding me which attributes should be set to private, what parts should be provided by Abstract Class, etc. Then doing the final design makes me write code faster and the design is more complete and effective. However, professor Veliz always responds to messages late at night, when I have already gone to bed, and when I get up the next day to respond to messages, I usually have to wait until late at night.

5. Think back on what you knew before starting this course and what you know now. What advice would you tell your younger self having completed this project?

Answer: I would give the following advice to my younger self: practice more (especially Stream and Lambda expressions), practice more Leetcode algorithms. Learn more Java frameworks and necessary skills (in addition to Gradle, I have learned Spring and Oracle databases, and the next plan is Linux basic operations, JavaScript and Maven framework).
I would say to my younger self: C language lacks cross-platform and many standard libraries and tool support. Be sure to have a deep understanding of Java. You will not regret choosing Java as your major language!




## Accountability

Qinghao Yang completed the entire Project alone
