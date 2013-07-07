
Scenario: Checks if the menu for the week by given date will found
Given request to find the menu for the day 2013-07-08
When the week contains all working days from monday to friday
Then expect 15 offers
Then expect 3 offers per day