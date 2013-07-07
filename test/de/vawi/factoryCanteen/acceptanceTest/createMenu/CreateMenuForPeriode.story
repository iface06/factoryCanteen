Narrative:
This acceptance test should demonstrate that the creation of a
menu for the given periode comply with all given rules


Scenario: Take care that menu contains enought dishes
Given a menu contains 3 meals per day for the next 5 days
When the periode is 3 weeks
Then expect 15 days with 45 dishes
Then each day with 3 dishes
Then each day contains a vegetarian dish, a dish with meat and a third alternativ
Then each dish is not offered repeatedly in the periode



